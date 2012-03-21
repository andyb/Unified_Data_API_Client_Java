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
