package ss.udapi.sdk.streaming;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StreamAction extends Action {
	
	private static Logger logger = Logger.getAnonymousLogger();
	
	public StreamAction(List<Event> events) {
		super(events, StreamEvent.class);
	}
	
	@Override
	public void run() {
		try
		{
			execute("Streaming message event");
		}
		catch(Exception ex)
		{
			logger.log(Level.WARNING, "Error", ex);
		}
	}
}
