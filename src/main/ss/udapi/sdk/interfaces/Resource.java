package ss.udapi.sdk.interfaces;

import java.util.List;

import ss.udapi.sdk.model.Summary;
import ss.udapi.sdk.streaming.Event;


public interface Resource {
	public String getId();
	public String getName();
	public Summary getContent();
	
	public String getSnapshot();
	public void startStreaming(List<Event> streamingEvents);
	public void stopStreaming();
	public void pauseStreaming();	
	public void unpauseStreaming();

}
