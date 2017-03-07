package lj.com.model;

import java.util.ArrayList;


public class Stopic {
	@Override
	public String toString() {
		return "Stopic [id=" + id + ", name=" + name + ", tags=" + tags + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Stopic(int id, String name, String tags) {
		super();
		this.id = id;
		this.name = name;
		this.tags = tags;
	}
	

	private int id;
	private String name;
    private String tags;
    
    
    
}
