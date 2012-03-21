package ss.udapi.sdk.streaming;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DisconnectedAction extends Action{
	
	private static Logger logger = Logger.getAnonymousLogger();
	
	public DisconnectedAction(List<Event> events) {
		super(events, DisconnectedEvent.class);
	}

	@Override
	public void run() {
		try
		{
			execute("Disconnected Action");
		}
		catch(Exception ex)
		{
			logger.log(Level.WARNING, "Error", ex);
		}
	}
}
