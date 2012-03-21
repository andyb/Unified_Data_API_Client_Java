//Copyright 2012 Spin Services Limited

//Licensed under the Apache License, Version 2.0 (the "License");
//you may not use this file except in compliance with the License.
//You may obtain a copy of the License at

//    http://www.apache.org/licenses/LICENSE-2.0

//Unless required by applicable law or agreed to in writing, software
//distributed under the License is distributed on an "AS IS" BASIS,
//WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//See the License for the specific language governing permissions and
//limitations under the License.

package ss.udapi.sdk.streaming;

import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

public class MessageListener extends Thread {
	
	private static Logger logger = Logger.getAnonymousLogger();
	
	private URI amqpUri;
	private LinkedBlockingQueue<String> queue;
	private List<Event> events = null;
	private Boolean isStreaming;
	private ExecutorService actionExecuter = Executors.newSingleThreadExecutor();
	private final Object monitor = new Object();
	private volatile boolean pause = false;
	
	public MessageListener(URI amqpUri, LinkedBlockingQueue<String> queue, List<Event> events, Boolean isStreaming){
		this.amqpUri = amqpUri;
		this.queue = queue;
		this.events = events;
		this.isStreaming = isStreaming;
	}
	
	public void run(){
		Connection conn = null;
		Channel channel = null;
		
		try{
			ConnectionFactory connectionFactory = new ConnectionFactory();
			String host = amqpUri.getHost();
			
			if (host != null) {
	        	connectionFactory.setHost(host);
	        }

	        int port = amqpUri.getPort();
	        if (port != -1) {
	        	connectionFactory.setPort(port);
	        }
	        
	        String userInfo = amqpUri.getRawUserInfo();
	        if (userInfo != null) {
	            String userPass[] = userInfo.split(":");
	            if (userPass.length > 2) {
	                throw new IllegalArgumentException("Bad user info in AMQP " +
	                                                   "URI: " + userInfo);
	            }
	            connectionFactory.setUsername(uriDecode(userPass[0]));

	            if (userPass.length == 2) {
	            	connectionFactory.setPassword(uriDecode(userPass[1]));
	            }
	        }
	        String queueName = "";
	        String path = amqpUri.getRawPath();
	        if (path != null && path.length() > 0) {
	        	queueName = path.substring(path.indexOf('/',1)+1);

	            connectionFactory.setVirtualHost("/" + uriDecode(amqpUri.getPath().substring(1,path.indexOf('/',1))));
	        }
	        
	        conn = connectionFactory.newConnection();
	        
	        channel = conn.createChannel();
	        
	        actionExecuter.execute(new ConnectedAction(events));
	        
	        QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.basicConsume(queueName,true,consumer);
			channel.basicQos(0, 10, false);
			
			while(isStreaming){

				synchronized(monitor){
					while(pause==true){
						monitor.wait();
					}
				}
				
				Delivery del =consumer.nextDelivery();
				String message = new String(del.getBody());
				
				queue.put(message);

			}
			channel.close();
			conn.close();
			actionExecuter.execute(new DisconnectedAction(events));
			actionExecuter.shutdown();
			
		}catch(IOException ioe){
			logger.log(Level.WARNING, "Error", ioe);
		}
		catch(InterruptedException e){
			try{
				channel.close();
				conn.close();
				actionExecuter.execute(new DisconnectedAction(events));
				actionExecuter.shutdown();
			}catch(IOException ex){
				logger.log(Level.WARNING, "Error", ex);
			}
		}
	}
	
	public void pause(){
		pause = true;
	}
	
	public void unPause(){
		synchronized(monitor){
			pause = false;
			monitor.notifyAll();
		}
	}
	
	private String uriDecode(String s) {
        try {
            // URLDecode decodes '+' to a space, as for
            // form encoding.  So protect plus signs.
            return URLDecoder.decode(s.replace("+", "%2B"), "US-ASCII");
        }
        catch (java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
