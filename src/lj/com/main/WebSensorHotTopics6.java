package lj.com.main;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import lj.com.Adapter.SampleListAdapter;
import lj.com.model.Topic;
import lj.com.service.WebSensorHotTopicService;
import lj.com.utils.CheckNetState;
import lj.com.utils.ReadStreamCommon;
import lj.com.utils.ShowNetUnuse;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class WebSensorHotTopics6 extends Activity implements OnItemClickListener{

	private ArrayList<Topic> ListItems;
	private Toast imagetoast;
	private PullToRefreshListView  mListView;
	private SampleListAdapter mAdapter;
	private static final String URL = "http://websensor.playbigdata.com/fss3/service.svc/GetWebSensorHotTopics";
	private WebSensorHotTopicService wsht;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab08wshottopic);
		mListView=(PullToRefreshListView)findViewById(R.id.pull_to_refresh_listview_wshottopic);
		wsht=new WebSensorHotTopicService(this);
		 mListView.setOnItemClickListener(this);  
		 ListItems = new ArrayList<Topic>(); 
		 mAdapter=new SampleListAdapter(this,ListItems);
		// p=new HotTopicService(this.getActivity());		   
		 ShowNetUnuse.showpic(imagetoast,this);
	     initWSHotTopic();
	     mAdapter=new SampleListAdapter(this,ListItems);
	    
	        // 设置PullToRefresh  	    
	        mListView.setMode(com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.PULL_FROM_START);  
	        mListView.setOnRefreshListener(new OnRefreshListener<ListView>(){  
	   
				public void onRefresh(PullToRefreshBase<ListView> refreshView) {
					// TODO Auto-generated method stub
	           				String label = DateUtils.formatDateTime(WebSensorHotTopics6.this, System.currentTimeMillis(),  
	                                DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);  
	           			 refreshView.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
	                     refreshView.getLoadingLayoutProxy().setPullLabel("下拉刷新");
	                     refreshView.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
	                     refreshView.getLoadingLayoutProxy().setLastUpdatedLabel("最后更新时间:" + label); 	
		                 new FinishRefresh().execute(); 
				}
	        });  
	       mListView.setAdapter(mAdapter);  
	      //设置自动刷新
	       if(wsht.getcount()==0)
	           mListView.setRefreshing(true);
	}
	 private void initWSHotTopic() {
			// TODO Auto-generated method stub
			 ListItems = new ArrayList<Topic>();  
			 if(wsht.getcount()>0)
		        for (int i = 0; i <wsht.getcount(); i++) {  
		            ListItems.add(wsht.find(i));  
		        }
			 else
			 {
				 if(CheckNetState.checkNetwork(this))
				 {
					  ListItems.add(new Topic(0,"稍等...正在加载"));
					  System.out.println("稍等...正在加载");
				 }
				 
				 else
				 { ListItems.add(new Topic(0,"加载失败，检查网络连接。"));
				    System.out.println("加载失败，检查网络连接。");
				 }
				 
			 }
				   
		}
	 private List<Topic> getWSHotTopicData(String url) {
			// TODO Auto-generated method stub
	     List<Topic> list=new ArrayList<Topic>();
		 try {
			String jsonString=ReadStreamCommon.readStream(new URL(url).openStream());
			    JSONObject data=new JSONObject(jsonString);
				JSONArray d=data.getJSONArray("d");
				for(int i=0;i<d.length();i++)
				{
					String ditem=d.getString(i);
					Topic topic=new Topic(i,ditem);
					  if(!wsht.findor(i))    wsht.save(topic);
					  else    wsht.update(topic);
					System.out.println(ditem);
					list.add(topic);
				}
				
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			return list;
		}
	 class FinishRefresh extends AsyncTask<Void,Void,List<Topic>>{
			@Override
			protected List<Topic> doInBackground(Void... params) {
				// TODO Auto-generated method stub
				 if(!CheckNetState.checkNetwork(WebSensorHotTopics6.this))
	             {
	                 return null;
	             }
				 else return getWSHotTopicData(URL);
			}

			@Override
			protected void onPostExecute(List<Topic> result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
	            if(result!=null){
					ListItems.clear();
				    for(int i=0;i<result.size();i++)
					 ListItems.add(result.get(i));
				}
				
	            mAdapter.notifyDataSetChanged();  
	            mListView.onRefreshComplete(); 
			}
	}
	    
	 
	 
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Topic topic=ListItems.get(arg2-1);
		Intent intent=new Intent(this,BrowseActivity.class);
		intent.putExtra("name",topic.getName() );
	  	startActivity(intent);
	}


	


}
