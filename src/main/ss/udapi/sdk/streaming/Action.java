package ss.udapi.sdk.streaming;

import java.util.ArrayList;
import java.util.List;


public abstract class Action implements Runnable {
	
	// Only the events that match the subtype Action i.e.
	// a ConnectedAction will only have ConnectEvents in the
	// matchedEvent list
	private List<Event> matchedEvents;
	
	public Action(List<Event> events, Class<?> eventClass) {
		// copying prevents possible downstream concurrent mod issues
		// and filters the list for on the directly related events
		matchedEvents = new ArrayList<Event>();
		if (eventClass != null && events != null) {
			for (Event event : events ) {
				if ( eventClass.isAssignableFrom( event.getClass() ) ) {
					matchedEvents.add(event);
				}
			}
		}
	}

	public void execute(String message) throws Exception {
			for (Event event : matchedEvents ) {
					event.onEvent(message);
			}
	}
		
}
