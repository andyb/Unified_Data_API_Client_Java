package ss.udapi.sdk;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import ss.udapi.sdk.clients.RestHelper;
import ss.udapi.sdk.extensions.JsonHelper;
import ss.udapi.sdk.model.RestItem;
import ss.udapi.sdk.model.RestLink;

public abstract class Endpoint {

	private static Logger logger = Logger.getAnonymousLogger();
	
	protected Map<String,String> headers;
	protected RestItem state;
	
	Endpoint(){
		
	}
	
	Endpoint(Map<String,String> headers, RestItem restItem){
		this.headers = headers;
		this.state = restItem;
	}
	
	List<RestItem> FindRelationAndFollow(String relation){
		List<RestItem> result = new ArrayList<RestItem>();
		if(state != null){
			for(RestLink restLink:state.getLinks()){
				if(restLink.getRelation().equals(relation)){
					URL theURL = null;
					try{
						theURL = new URL(restLink.getHref());
					}catch(MalformedURLException ex){
						logger.log(Level.WARNING, "Malformed Login URL", ex);
					}
					
					String rawJson = RestHelper.getResponse(theURL, null, "GET", "application/json", 60000, headers, false);
					result =  JsonHelper.toRestItems(rawJson);
					break;
				}
			}
		}
		return result;
	}
}
