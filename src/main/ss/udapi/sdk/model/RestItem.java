package ss.udapi.sdk.model;

import java.util.List;

public class RestItem {
	/*Using Upper case so that deserialization from json works*/
	private String Name;
	private Summary Content;
	private List<RestLink> Links;

	public String getName(){
		return Name;
	}
	
	public void setName(String name){
		this.Name = name;
	}
	
	public Summary getContent(){
		return Content;
	}
	
	public void setContent(Summary content){
		this.Content = content;
	}

	public void setLinks(List<RestLink> links) {
		this.Links = links;
	}

	public List<RestLink> getLinks() {
		return Links;
	}
}
