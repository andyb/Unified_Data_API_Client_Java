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

public class Summary {
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
	
	public List<Participant> getParticipants(){
		return Participants;
	}
	
	public void setParticipnts(List<Participant> participants){
		Participants = participants;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}
	
	public List<Tag> getTags(){
		return Tags;
	}
	
	public void setTags(List<Tag> tags){
		Tags = tags;
	}

	public String getDefinitionId() {
		return DefinitionId;
	}

	public void setDefinitionId(String definitionId) {
		DefinitionId = definitionId;
	}

	public String getDefinitionName() {
		return DefinitionName;
	}

	public void setDefinitionName(String definitionName) {
		DefinitionName = definitionName;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	private String Id;
     
	private String Description;
	
	private List<Participant> Participants;

	private String Date;
	
	private List<Tag> Tags;
      
	private String DefinitionId;
 
	private String DefinitionName;

	private String Type;
}
