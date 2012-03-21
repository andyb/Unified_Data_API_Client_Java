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
