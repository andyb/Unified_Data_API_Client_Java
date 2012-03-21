package ss.udapi.sdk.interfaces;

import java.util.List;

public interface Service {
	public String getName();
	
	public List<Feature> getFeatures();
	public Feature getFeature(String featureName);
}
