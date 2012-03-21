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
