package lj.com.topFragment;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

import lj.com.Adapter.HotTopicAdapter;
import lj.com.main.BrowseActivity;
import lj.com.main.R;
import lj.com.model.Topic;
import lj.com.service.HotTopicService;
import lj.com.utils.CheckNetState;
import lj.com.utils.ReadStreamCommon;
import lj.com.utils.ShowNetUnuse;
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
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class NetTopicTab1 extends Fragment implements OnItemClickListener{

	private View rootView; 
	private ArrayList<Topic> mListItems;
    private Toast imagetoast;
	private PullToRefreshListView  mListView;
	private HotTopicAdapter hottopicAdapter;
    private static final String Gettopiccluster_url = "http://websensor.playbigdata.com/fss3/service.svc/GetHotTopicClusters";
	private static final String URL = "http://websensor.playbigdata.com/fss3/service.svc/RetrieveHotTopicData?url=search.aspx%3Fq%3D*%26icount%3D10%26mindate%3D2015-10-12%26maxdate%3D2015-10-19&days=7&getText=false&relative=true&facet=true";
	private HotTopicService p;
		 @Override
		    public void onAttach(Activity activity) {
		        super.onAttach(activity);
		        p=new HotTopicService(this.getActivity());	
		    }
		 @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView=inflater.inflate(R.layout.tab01nettopic, container, false);
		return rootView;
	//	return inflater.inflate(R.layout.tab01, container, false);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 super.onActivityCreated(savedInstanceState);
		 
		 mListView=(PullToRefreshListView)rootView.findViewById(R.id.pull_to_refresh_listview_nettopic);
		 mListView.setOnItemClickListener(this);  
		 mListItems = new ArrayList<Topic>(); 
		// p=new HotTopicService(this.getActivity());		   
		 ShowNetUnuse.showpic(imagetoast,getActivity());
	        
	        initHotTopic();
//	        for (int i = 1; i <= 10; i++) {  
//	            mListItems.add(new Topic(i,"一带一路","相关词","关键字"));  
//	        }  
	        
	       hottopicAdapter=new HotTopicAdapter(getActivity(),mListItems);
	      //  mAdapter = new SampleListAdapter(mListItems,this);  
	      //  mListView.setAdapter(hottopicAdapter);  
	        // 设置PullToRefresh  
	    
	        mListView.setMode(com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.PULL_FROM_START);  
	        mListView.setOnRefreshListener(new OnRefreshListener<ListView>(){  
	   
				public void onRefresh(PullToRefreshBase<ListView> refreshView) {
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
	       mListView.setAdapter(hottopicAdapter);  
	      //设置自动刷新
	       if( p.getcount()==0)
	           mListView.setRefreshing(true);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	private List<Topic> getHotTopicData(String url) {
			// TODO Auto-generated method stub
	     List<Topic> list=new ArrayList<Topic>();
		 try {		
			String jsonData=ReadStreamCommon.readStream(new URL(url).openStream());
		    JSONObject data=new JSONObject(jsonData);
		    JSONArray d=data.getJSONArray("d");
			JSONObject o1=d.getJSONObject(0);
			
			JSONArray clusters=o1.getJSONArray("clusters");
			for(int i=0;i<clusters.length();i++)
			{
				JSONObject clustersobject=clusters.getJSONObject(i);
				String kws="",ali="",ztopic="";
				JSONArray alias=clustersobject.getJSONArray("alias");
				for(int j=0;j<alias.length();j++)
				{
					JSONObject item=alias.getJSONObject(j);
					String title=item.getString("title");
					if(j==0)
					    ztopic=title;
					ali=ali+title+"  ";
				}
				JSONArray keywords=clustersobject.getJSONArray("keywords");
				for(int j=0;j<keywords.length();j++)
				{
					JSONObject item=keywords.getJSONObject(j);
					String title=item.getString("title");
					kws=kws+title+"  ";
				}
				
			       // lv.setAdapter(adapter);  
			    Log.i("yyy",ztopic+ali+kws );   
			    Topic topic=new Topic(i,ztopic,ali,kws);
			  if(!p.findor(i))    p.save(topic);
			  else    p.update(topic);
			    
			    list.add(topic);
			}
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			
			
			return list;
		}
	 class FinishHotTopicRefresh extends AsyncTask<Void,Void,List<Topic>>{

	    	

			@Override
			protected List<Topic> doInBackground(Void... params) {
				// TODO Auto-generated method stub
				 if(!CheckNetState.checkNetwork(getActivity()))
	             {
	                 //显示Toast
					// imagetoast.show();
					 Toast.makeText(getActivity(), "网络连接不可用", Toast.LENGTH_SHORT).show();
	                 return null;
	             }
//				boolean b=ShowNetUnuse.showpic(imagetoast, getActivity());
//				if(b==true)  return null;
				 else return getHotTopicData(Gettopiccluster_url);
			}

			@Override
			protected void onPostExecute(List<Topic> result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				if(result!=null){
					mListItems.clear();
			    	for(int i=0;i<result.size();i++)
					 mListItems.add(result.get(i));
				}
				
				 hottopicAdapter.notifyDataSetChanged();  
	            mListView.onRefreshComplete(); 
			}
	}
	 private void initHotTopic() {
			// TODO Auto-generated method stub
			 mListItems = new ArrayList<Topic>();  
			
			 if(p.getcount()>0)
		        for (int i = 0; i <p.getcount(); i++) {  
		            mListItems.add(p.find(i));  
		        }
			 else
			 {
				 if(CheckNetState.checkNetwork(getActivity()))
				  mListItems.add(new Topic(0,"稍等...正在加载","",""));
				 else
					 mListItems.add(new Topic(0,"加载失败，检查网络连接。","",""));
				  
			 }
//				 mListView.setRefreshing(true);
				// mListView.autoRefresh();
				   
		}
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Topic topic=mListItems.get(arg2-1);
		Log.i("click", "1w");
		Intent intent=new Intent(getActivity(),BrowseActivity.class);
//	     	
//	     	 Drawable hottopicbg = hottopic.getBackground();
// 			ColorDrawable hottopiccd = (ColorDrawable) hottopicbg;
// 			int hottopiccolor = hottopiccd.getColor();
//             if(hottopiccolor==Color.BLUE)  
//             {
             		String relative="";
	     	String []rels=topic.getRelativetopic().split("  ");
	     	for(int i=0;i<rels.length;i++)
	     	{
	     		if(i==0) relative+="("+rels[i]+")";
	     		else relative+="OR"+"("+rels[i]+")";
	     	}
	     	intent.putExtra("relative", relative);
	     	intent.putExtra("name",topic.getName() ); 
           //  }
//             else
//            	 intent.putExtra("name",topic.getName() );
	     
	     
	     	Log.i("click", "5w");
	  	startActivity(intent);
	}

	

}
