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

package ss.udapi.sdk;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import ss.udapi.sdk.clients.RestHelper;
import ss.udapi.sdk.interfaces.Resource;
import ss.udapi.sdk.model.RestItem;
import ss.udapi.sdk.model.RestLink;
import ss.udapi.sdk.model.Summary;
import ss.udapi.sdk.streaming.Event;
import ss.udapi.sdk.streaming.MessageDispatcher;
import ss.udapi.sdk.streaming.MessageListener;

public class ResourceImpl extends Endpoint implements Resource {

	private static Logger logger = Logger.getAnonymousLogger();
	
	private LinkedBlockingQueue<String> queue;
	private MessageListener listener;
	private MessageDispatcher dispatcher;
	private Boolean isStreaming;
	
	ResourceImpl(Map<String,String> headers, RestItem restItem){
		super(headers,restItem);
	}
	
	public String getId() {
		return state.getContent().getId();
	}

	public String getName() {
		return state.getName();
	}

	public Summary getContent() {
		return state.getContent();
	}

	public String getSnapshot() {
		if(state != null){
			for(RestLink link:state.getLinks()){
				if(link.getRelation().equals("http://api.sportingsolutions.com/rels/snapshot")){
					URL theURL = null;
					try{
						theURL = new URL(link.getHref());
					}catch(MalformedURLException ex){
						logger.log(Level.WARNING, "Malformed Login URL", ex);
					}
					return RestHelper.getResponse(theURL, null, "GET", "application/json", 60000, headers, false);
				}
			}
		}
		return "";
	}
	
	public void startStreaming(List<Event> streamingEvents){
		List<RestItem> restItems = FindRelationAndFollow("http://api.sportingsolutions.com/rels/stream/amqp");
		if(restItems != null){
			for(RestItem restItem:restItems){
				for(RestLink link:restItem.getLinks()){
					if(link.getRelation().equals("amqp")){
						URI amqpAddress = null;
						try {
							amqpAddress = new URI(link.getHref());
						} catch (URISyntaxException ex) {
							logger.log(Level.WARNING, "Malformed AMQP URL", ex);
						}
						if(amqpAddress != null){
							queue = new LinkedBlockingQueue<String>(10);
							isStreaming = true;
							listener = new MessageListener(amqpAddress, queue, streamingEvents, isStreaming);
							dispatcher = new MessageDispatcher(queue, streamingEvents, isStreaming);
							
							listener.start();
							dispatcher.start();
						}
					}
				}
			}
		}
	}
	
	public void stopStreaming(){
		isStreaming = false;
		if(listener != null){
			listener.interrupt();
		}
		if(dispatcher != null){
			dispatcher.interrupt();
		}
	}
	
	public void pauseStreaming(){
		listener.pause();
	}
	
	public void unpauseStreaming(){
		listener.unPause();
	}

}
