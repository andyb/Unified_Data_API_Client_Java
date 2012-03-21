package ss.udapi.sdk.model;

public class RestLink {
	/*Using Uppercase so that deserialization from json works*/
	private String Relation;
	private String Href;
	private String[] Verbs;
	
	public void setRelation(String relation) {
		this.Relation = relation;
	}
	public String getRelation() {
		return Relation;
	}
	public void setHref(String href) {
		this.Href = href;
	}
	public String getHref() {
		return Href;
	}
	public void setVerbs(String[] verbs) {
		this.Verbs = verbs;
	}
	public String[] getVerbs() {
		return Verbs;
	}
}
