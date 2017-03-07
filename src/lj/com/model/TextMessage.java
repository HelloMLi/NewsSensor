package lj.com.model;

public class TextMessage {
   String date;
   String docid;
   String entity;
   String nugget;
   String text;
   String opinion;
public TextMessage(String date, String docid, String entity, String nugget,
		String text, String opinion) {
	super();
	this.date = date;
	this.docid = docid;
	this.entity = entity;
	this.nugget = nugget;
	this.text = text;
	this.opinion = opinion;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getDocid() {
	return docid;
}
public void setDocid(String docid) {
	this.docid = docid;
}
public String getEntity() {
	return entity;
}
public void setEntity(String entity) {
	this.entity = entity;
}
public String getNugget() {
	return nugget;
}
public void setNugget(String nugget) {
	this.nugget = nugget;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}
public String getOpinion() {
	if(opinion.equals("1"))   return "负面";
	else if(opinion.equals("2")) return "中性";
	else if(opinion.equals("3")) return "正面";
	else return null;
}
public void setOpinion(String opinion) {
	this.opinion = opinion;
}
@Override
public String toString() {
	
	String s="";
	s=s+text;
	if(opinion.equals("1")) s=s+"\n"+"负面";
	else if(opinion.equals("2")) s=s+"\n"+"中性";
	else if(opinion.equals("3")) s=s+"\n"+"正面";
	
	if(!entity.equals(""))  s=s+"\n"+entity;
	
	if(!nugget.equals("")) s=s+"\n"+nugget;
	
	return s;
}
   
   
   
}
