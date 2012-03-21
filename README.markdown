This is the master repository for the Sporting Solutions Unified Data API Client for Java.
The SDK provides an easy to use interface into the Sporting Solutions Unified Data API.  

Usage of this SDK requires a GTP username and password (available on request), it’s usage is authorised only for current or prospective clients.

Any bug reports, comments, feedback or enhancements requests are gratefully received.

Dependencies
----------------------
You will need [Oracle JDK 1.5](http://www.oracle.com/technetwork/java/javasebusiness/downloads/java-archive-downloads-javase5-419410.html) to compile and use the library

Licence
----------------------
Sporting Solutions Unified Data API Client for Java is licenced under the terms of the Apache Licence Version 2.0, please see the included Licence.txt file

Getting Started
----------------------
```java	
Credentials credentials = new CredentialsImpl ("jim@bookies","password" );
URL theURL = new URL("http://api.sportingsolutions.com");
Session theSession = SessionFactory.createSession(theURL, credentials);

//Get the Unified Data API Service
Service theService = theSession.getService("UnifiedDataAPI");

//Sports are features, so lets get Tennis
Feature theFeature = theService.getFeature("Tennis");

//Events are resources, lets get all the events for Tennis
List<Resource> theResources = theFeature.getResources();

//Grab the first event, this is only an example after all
Resource theEvent = theResources.get(0);

String theSnapshot = theEvent.getSnapshot();
System.out.println(theSnapshot);

//Set up the Stream Event handlers
List<Event> streamingEvents = new ArrayList<Event>();
streamingEvents.add(new ConnectedEvent() {
	public void onEvent(String message) {
		System.out.println("Stream Connected");
	}
});
	   
streamingEvents.add(new StreamEvent() {
	public void onEvent(String message) {
		System.out.println("Message Arrived :" + message);
	}
});

streamingEvents.add(new DisconnectedEvent() {
	public void onEvent(String message){
		System.out.println("Stream Disconnected");
	}
});
		
theEvent.startStreaming(streamingEvents);
```