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
