package ss.udapi.sdk.streaming;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectedAction extends Action {

	private static Logger logger = Logger.getAnonymousLogger();
	
	public ConnectedAction(List<Event> events) {
		super(events, ConnectedEvent.class);
	}

	@Override
	public void run() {
		try
		{
			execute("Connected Action");
		}
		catch(Exception ex)
		{
			logger.log(Level.WARNING, "Error", ex);
		}
	}

}
