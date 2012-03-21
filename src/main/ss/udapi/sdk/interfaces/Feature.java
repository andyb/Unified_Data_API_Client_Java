package ss.udapi.sdk.interfaces;

import java.util.List;

public interface Feature {
	public String getName();
	
	public Resource getResource(String resourceName);
	public List<Resource> getResources();
}
