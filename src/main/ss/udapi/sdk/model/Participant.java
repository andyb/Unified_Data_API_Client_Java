package ss.udapi.sdk.model;

public class Participant {
	
	public void setName(String name) {
		this.Name = name;
	}
	public String getName() {
		return Name;
	}
	
	public void setId(Integer id) {
		this.Id = id;
	}
	public Integer getId() {
		return Id;
	}
	
	private String Name;
	private Integer Id;
}
