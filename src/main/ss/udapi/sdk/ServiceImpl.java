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
