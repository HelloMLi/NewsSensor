package lj.com.main;

import java.text.SimpleDateFormat;
import java.util.List;

import lj.com.model.Focus;
import lj.com.service.FocusService;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class NoteActivity extends Activity {

	private TextView qxfocused;
	private FocusService focuservice;
	private List<Focus> qxfocustopic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.note);
		 String content="";
		 focuservice=new FocusService(this);
		 qxfocused=(TextView)this.findViewById(R.id.qxfocused);
		 qxfocustopic=focuservice.getqxfocus(0,focuservice.getqxfocuscount());
		 for(int i=0;i<qxfocustopic.size();i++)
		 {
			 SimpleDateFormat format=new  SimpleDateFormat("yyyy年MM月dd日  HH时mm分ss秒");
			// content+="    ("+(i+1)+")"+"您于"+format.format(qxfocustopic.get(i).getDate())+"取消了对话题："+qxfocustopic.get(i).getName()+" 的关注"+"\n";
			 content+="    ("+(i+1)+")"+"您于"+qxfocustopic.get(i).getDate()+"取消了对话题："+qxfocustopic.get(i).getName()+" 的关注"+"\n";
			 
				
		 }
		qxfocused.setText(content);	 
		 
	}
   
}