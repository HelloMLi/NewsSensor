package lj.com.Fragment;

import java.util.ArrayList;
import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import lj.com.Adapter.FocusAdapter;
import lj.com.main.BrowseActivity;
import lj.com.main.NoteActivity;
import lj.com.main.R;
import lj.com.model.Focus;
import lj.com.model.Topic;
import lj.com.service.FocusService;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;



public class MyTab extends Fragment {

	private FocusService focuservice;
	private ArrayList<Topic> list;
	private FocusAdapter adapter;
	//private ListView gv;
	private PullToRefreshGridView gv;
	private Button note;
	private View rootView;
	private LinearLayout myfocusbg;
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        focuservice=new FocusService(getActivity());
        
    }
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//if(rootView==null){
		rootView=inflater.inflate(R.layout.myfocus, container, false);
		this.onActivityCreated(savedInstanceState);
		return rootView;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i("1", "执行onActivitycreate");
		 super.onActivityCreated(savedInstanceState);
		
		 Log.i("myf", "2");
		 myfocusbg=(LinearLayout)rootView.findViewById(R.id.myfocusbg);
		 note=(Button)rootView.findViewById(R.id.focusnote);
		 Log.i("myf", "3");
		
		 note.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),NoteActivity.class);
				startActivity(intent);
			}
		});
		 Log.i("myf", "4");
		 
		  gv=(PullToRefreshGridView)rootView.findViewById(R.id.pull_to_refresh_gridview_myfocus);
		 
		 gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					Topic topic=list.get(arg2);
					Intent intent=new Intent(getActivity(),BrowseActivity.class);
				    intent.putExtra("name", topic.getName());
				  	startActivityForResult(intent,3);	
				}
			});
		 
		// list=new ArrayList<Topic>();
		 init();
		 
		 adapter=new FocusAdapter(getActivity(),list);
		 //gv.setRefreshing(true);
		gv.setOnRefreshListener(new OnRefreshListener<GridView>(){  
			   
				public void onRefresh(PullToRefreshBase<GridView> refreshView) {
					// TODO Auto-generated method stub
//					 int count = mListItems.size();  
//		                mListItems.add("Item " + Integer.toString(++count));  
	           				String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),  
	                                DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);  
	           			 refreshView.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
	                     refreshView.getLoadingLayoutProxy().setPullLabel("下拉刷新");
	                     refreshView.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
	                     refreshView.getLoadingLayoutProxy().setLastUpdatedLabel("最后更新时间:" + label);
	                      	
		                  new FinishHotTopicRefresh().execute(); 
	                      
				}
	        }); 
	       gv.setAdapter(adapter);  
	      // gv.autoautoRefresh();
	      //设置自动刷新
//	       if(note.getText().equals("已取消关注的话题"))
//	    	   gv.setRefreshing(true);
	          
		
	}
	
	 class FinishHotTopicRefresh extends AsyncTask<Void,Void,List<Topic>>{

	    	

			@Override
			protected List<Topic> doInBackground(Void... params) {
				// TODO Auto-generated method stub
				    String s="";
					if(focuservice.getfocuscount()!=0)
					{
						 Log.i("myf", "5");
						 List<Focus> fs=focuservice.getfocus(0,focuservice.getfocuscount());
						 ArrayList<Topic> mlist=new ArrayList<Topic>();
					
					    for(int i=0;i<focuservice.getfocuscount();i++)
					    	mlist.add(new Topic(fs.get(i).getName(),"0"));
					    return mlist;
					} 
					else
					{
						s="还未添加关注";
						 Log.i("myf", "6");
						//Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
						return null;
						
					}
						
			}

			@Override
			protected void onPostExecute(List<Topic> result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				if(result!=null){
					list.clear();
			    	for(int i=0;i<result.size();i++)
					 list.add(result.get(i));
			    	//myfocusbg.setBackgroundResource(R.drawable.bg);
				}
				else
				{
				//	myfocusbg.setBackgroundResource(R.drawable.white_bac);
				}
//				
			   adapter.notifyDataSetChanged();  
	           gv.onRefreshComplete(); 
			}
	}
		 
		
			//getActivity().finish();
		
			
	
		
	private void init() {
		// TODO Auto-generated method stub
		 list = new ArrayList<Topic>();  
			
		 if(focuservice.getfocuscount()>0){
			 List<Focus> fs=focuservice.getfocus(0,focuservice.getfocuscount());
		    for(int i=0;i<focuservice.getfocuscount();i++)
		    	list.add(new Topic(fs.get(i).getName(),"0"));
		    
	        }
	}
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
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
				
		        adapter=new FocusAdapter(getActivity(),list);
		        gv.setAdapter(adapter);
		        adapter.notifyDataSetChanged();
	       }
	    	else 
	    	{
	    		
               adapter=new FocusAdapter(getActivity(),list);
		      
		        
		        gv.setAdapter(adapter);
		        
		        adapter.notifyDataSetChanged();
	    		Toast.makeText(getActivity(), "没有进行关注", Toast.LENGTH_LONG).show();
	    	}
	    		
	    }
	    
	    
	}
	

}
