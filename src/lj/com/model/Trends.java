package lj.com.model;

public class Trends {
	private String date;
	private int all;
	private int Neg;
	private int pos;
	private int Neu;
	public Trends(String date, int all, int neg, int pos, int neu) {
		// TODO Auto-generated constructor stub
		this.all=all;
		this.date=date;
		this.Neg=neg;
		this.Neu=neu;
		this.pos=pos;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getAll() {
		return all;
	}
	public void setAll(int all) {
		this.all = all;
	}
	public int getNeg() {
		return Neg;
	}
	public void setNeg(int neg) {
		Neg = neg;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public int getNeu() {
		return Neu;
	}
	public void setNeu(int neu) {
		Neu = neu;
	}
	@Override
	public String toString() {
		return "Trends [date=" + date + ", all=" + all + ", Neg=" + Neg
				+ ", pos=" + pos + ", Neu=" + Neu + "]";
	}
	
}
