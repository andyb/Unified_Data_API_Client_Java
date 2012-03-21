package ss.udapi.sdk.streaming;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageDispatcher extends Thread  {
	
	private static Logger logger = Logger.getAnonymousLogger();
	
	private LinkedBlockingQueue<String> queue;
	private List<Event> events = null;
	
	private Boolean isStreaming;
	
	public MessageDispatcher(LinkedBlockingQueue<String> queue, List<Event> events, Boolean isStreaming){
		this.queue = queue;
		this.events = events;
		this.isStreaming = isStreaming;
	}
	
	public void run() {
		StreamAction streamAction = new StreamAction(events);
		while(isStreaming){
			try {
				String message = queue.poll(500, TimeUnit.MILLISECONDS);
				if(message != null){
					streamAction.execute(message);
				}
			} catch (InterruptedException ex) {
				logger.log(Level.WARNING, "Interrupted Exception", ex);
			}
			catch(Exception ex){
	        	logger.log(Level.WARNING, "Error", ex);
	        }
		}
	}
}
