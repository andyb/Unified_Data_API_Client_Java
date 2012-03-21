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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import ss.udapi.sdk.CredentialsImpl;
import ss.udapi.sdk.SessionFactory;
import ss.udapi.sdk.interfaces.Credentials;
import ss.udapi.sdk.interfaces.Feature;
import ss.udapi.sdk.interfaces.Resource;
import ss.udapi.sdk.interfaces.Service;
import ss.udapi.sdk.interfaces.Session;
import ss.udapi.sdk.streaming.ConnectedEvent;
import ss.udapi.sdk.streaming.DisconnectedEvent;
import ss.udapi.sdk.streaming.Event;
import ss.udapi.sdk.streaming.StreamEvent;

public class Integration {
	
	private static Logger logger = Logger.getAnonymousLogger();
	
	@Test
	public void doTest() throws Exception{
		
		URL theURL = null;
		try{
			theURL = new URL("http://api.sportingsolutions.com");
		}catch(MalformedURLException ex){
			logger.log(Level.WARNING, "Malformed Login URL", ex);
		}
		Credentials theCredentials = new CredentialsImpl("jim@bookies", "password");
		Session theSession = SessionFactory.createSession(theURL, theCredentials);
		Service theService = theSession.getService("UnifiedDataAPI");
		Feature theFeature = theService.getFeature("Tennis");
		List<Resource> theResources = theFeature.getResources();
		Resource theResource = theResources.get(0);
		
		String theSnapshot = theResource.getSnapshot();
		
		logger.log(Level.INFO, theSnapshot);
		
		List<Event> streamingEvents = new ArrayList<Event>();
		streamingEvents.add(new ConnectedEvent() {
			public void onEvent(String message) {
				logger.log(Level.INFO,"Stream Connected");
			}
		});
			   
		streamingEvents.add(new StreamEvent() {
			public void onEvent(String message) {
				logger.log(Level.INFO,"Message Arrived :" + message);
		    }
		});
		
		streamingEvents.add(new DisconnectedEvent() {
			public void onEvent(String message){
				logger.log(Level.INFO,"Stream Disconnected");
			}
		});
		
		theResource.startStreaming(streamingEvents);
		Thread.sleep(10000);
		theResource.pauseStreaming();
		logger.log(Level.INFO, "Pausing");
		Thread.sleep(10000);
		theResource.unpauseStreaming();
		logger.log(Level.INFO, "Un-Pausing");
		Thread.sleep(10000);
		theResource.stopStreaming();
	}
}
