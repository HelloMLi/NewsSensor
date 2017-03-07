package lj.com.model;

public class Topic {

	private int id;
	private String name;
	private String Value;
	
	private String relativetopic;
	private String keywords;
	
	public Topic()
	{}
	public Topic(String name, String value) {
		super();
		this.name = name;
		this.Value = value;
	}

	


	public Topic(int id,String name, String relativetopic, String keywords) {
		super();
		this.id=id;
		this.name = name;
		this.relativetopic = relativetopic;
		this.keywords = keywords;
	}
	public Topic(int id, String name, String value) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.name = name;
		this.Value = value;
	}
	public Topic(int id, String name) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return Value;
	}
	public void setValue(String value) {
		Value = value;
	}
	public String getRelativetopic() {
		return relativetopic;
	}
	public void setRelativetopic(String relativetopic) {
		this.relativetopic = relativetopic;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
