package ss.udapi.sdk;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ss.udapi.sdk.interfaces.Feature;
import ss.udapi.sdk.interfaces.Resource;
import ss.udapi.sdk.model.RestItem;

public class FeatureImpl extends Endpoint implements Feature{
	
	FeatureImpl(Map<String,String> headers, RestItem restItem){
		super(headers,restItem);
	}
	
	public String getName() {
		return state.getName();
	}

	public Resource getResource(String resourceName) {
		List<RestItem> restItems = FindRelationAndFollow("http://api.sportingsolutions.com/rels/resources/list");
		for(RestItem restItem:restItems){
			if(restItem.getName().equals(resourceName)){
				return new ResourceImpl(headers, restItem);
			}
		}
		return null;
	}

	public List<Resource> getResources() {
		List<Resource> result = new ArrayList<Resource>();
		List<RestItem> restItems = FindRelationAndFollow("http://api.sportingsolutions.com/rels/resources/list");
		for(RestItem restItem:restItems){
			result.add(new ResourceImpl(headers, restItem));
		}
		return result;
	}

}
