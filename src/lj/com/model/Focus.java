package lj.com.model;

public class Focus {

	private String name;
	private String date;
	private int tag;//tag=1时表示关注，tag=0时表示取消关注
//	public Focus(String name, String date) {
//		// TODO Auto-generated constructor stub
//		this.name=name;
//		this.date=date;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Focus(String name, String date, int tag) {
		super();
		this.name = name;
		this.date = date;
		this.tag = tag;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	@Override
	public String toString() {
		return "Focus [name=" + name + ", date=" + date + ", tag=" + tag + "]";
	}
	
}
