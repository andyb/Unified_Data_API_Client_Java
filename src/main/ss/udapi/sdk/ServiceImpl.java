package ss.udapi.sdk;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ss.udapi.sdk.interfaces.Feature;
import ss.udapi.sdk.interfaces.Service;
import ss.udapi.sdk.model.RestItem;

public class ServiceImpl extends Endpoint implements Service {

	ServiceImpl(Map<String,String> headers, RestItem restItem){
		super(headers,restItem);
	}
	
	public String getName() {
		return state.getName();
	}

	public List<Feature> getFeatures() {
		List<Feature> result = new ArrayList<Feature>();
		List<RestItem> restItems = FindRelationAndFollow("http://api.sportingsolutions.com/rels/features/list");
		for(RestItem restItem:restItems){
			result.add(new FeatureImpl(headers, restItem));
		}
		return result;
	}

	public Feature getFeature(String featureName) {
		List<RestItem> restItems = FindRelationAndFollow("http://api.sportingsolutions.com/rels/features/list");
		for(RestItem restItem:restItems){
			if(restItem.getName().equals(featureName)){
				return new FeatureImpl(headers, restItem);
			}
		}
		return null;
	}

}
