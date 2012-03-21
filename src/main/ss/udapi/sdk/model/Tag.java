package ss.udapi.sdk.model;

public class Tag {
	
	public void setValue(String value) {
		this.Value = value;
	}
	public String getValue() {
		return Value;
	}
	
	public void setKey(String key) {
		this.Key = key;
	}
	public String getKey() {
		return Key;
	}
	
	public void setId(Integer id) {
		this.Id = id;
	}
	public Integer getId() {
		return Id;
	}
	
	private String Value;
	private String Key;
	private Integer Id;
}
