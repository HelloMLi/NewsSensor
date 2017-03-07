package lj.com.main;

import java.util.ArrayList;
import java.util.List;

import lj.com.Adapter.FocusAdapter;
import lj.com.model.Focus;
import lj.com.model.Topic;
import lj.com.service.FocusService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class MyFocusActivity extends Activity {

	private FocusService focuservice;
	private ArrayList<Topic> list;
	private FocusAdapter adapter;
	//private ListView gv;
	private GridView gv;
	private Button note;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.myfocus);
		 Log.i("myf", "1");
		// gv=(ListView)this.findViewById(R.id.myfclv);
		 gv=(GridView)this.findViewById(R.id.gridview);
		 Log.i("myf", "2");
		 note=(Button)this.findViewById(R.id.focusnote);
		 Log.i("myf", "3");
		 focuservice=new FocusService(this);
		 note.setOnClickListener(new noteListener());
		 Log.i("myf", "4");
		 String s="";
			if(focuservice.getfocuscount()!=0)
			{
				 Log.i("myf", "5");
				List<Focus> fs=focuservice.getfocus(0,focuservice.getfocuscount());
				 list=new ArrayList<Topic>();
				
			    for(int i=0;i<focuservice.getfocuscount();i++)
			    	list.add(new Topic(fs.get(i).getName(),"0"));
		        adapter=new FocusAdapter(this,list);
		      
		        
		        gv.setAdapter(adapter);
		        
		        adapter.notifyDataSetChanged();
		        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						Topic topic=list.get(arg2);
						Intent intent=new Intent(MyFocusActivity.this,BrowseActivity.class);
					    intent.putExtra("name", topic.getName());
					  	startActivityForResult(intent,3);	
					}
				});
			   // s+= fs.get(i).getName()+""+ fs.get(i).getDate()+"\n";   
			} 
			else
				s="还未添加关注";
			 Log.i("myf", "6");
			Toast.makeText(this, s, Toast.LENGTH_LONG).show();
			
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, intent);
		Bundle data=intent.getExtras();
	    String result=data.getString("backfocus");
	    if(result.equals("1"))
	    {
	    	List<Focus> fs=focuservice.getfocus(0,focuservice.getfocuscount());
				 list=new ArrayList<Topic>();
				
			    for(int i=0;i<focuservice.getfocuscount();i++)
			    	list.add(new Topic(fs.get(i).getName(),"0"));
	    	if(focuservice.getfocuscount()!=0)
			{
				 Log.i("myf", "5");
				
		        adapter=new FocusAdapter(this,list);
		      
		        
		        gv.setAdapter(adapter);
		        
		        adapter.notifyDataSetChanged();
	       }
	    	else 
	    	{
	    		
               adapter=new FocusAdapter(this,list);
		      
		        
		        gv.setAdapter(adapter);
		        
		        adapter.notifyDataSetChanged();
	    		Toast.makeText(this, "没有进行关注", Toast.LENGTH_LONG).show();
	    	}
	    		
	    }
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
	    	   if(keyCode==KeyEvent.KEYCODE_BACK)
	    	   {
	    		   Intent intent=getIntent();
				    intent.putExtra("back", "1");
				   MyFocusActivity.this.setResult(1,intent);
	    		   MyFocusActivity.this.finish();
	    	   }
		return super.onKeyDown(keyCode, event);
		
	}
	class noteListener implements View.OnClickListener
	{

		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MyFocusActivity.this,NoteActivity.class);
			startActivity(intent);
		}		
	}
	

}
