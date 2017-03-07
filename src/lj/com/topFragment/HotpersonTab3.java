package lj.com.topFragment;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

import lj.com.Adapter.SampleListAdapter;
import lj.com.Adapter.SampleListAdapter1;
import lj.com.main.BrowseActivity;
import lj.com.main.R;
import lj.com.model.Stopic;
import lj.com.model.Topic;
import lj.com.service.PersonService;
import lj.com.utils.CheckNetState;
import lj.com.utils.DateUtil;
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



public class HotpersonTab3 extends Fragment implements OnItemClickListener{

	private View rootView; 
	//private ArrayList<Topic> ListItems;
	private LinkedHashMap<String, List<Integer>> ListItems;
	private Toast imagetoast;
	private PullToRefreshListView  mListView;
	private SampleListAdapter1 mAdapter;
//    private static final String Gettopiccluster_url = "http://websensor.playbigdata.com/fss3/service.svc/GetHotTopicClusters";
//		private static final String URL = "http://websensor.playbigdata.com/fss3/service.svc/RetrieveHotTopicData?url=search.aspx%3Fq%3D*%26icount%3D10%26mindate%3D2015-10-12%26maxdate%3D2015-10-19&days=7&getText=false&relative=true&facet=true";
//	
	
	private PersonService ps;//=new PersonService(NewsSensorActivity.this);
	 @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        ps=new PersonService(this.getActivity());	
	    }
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView=inflater.inflate(R.layout.tab03hotperson, container, false);
		return rootView;
	}
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 super.onActivityCreated(savedInstanceState);		 
		 mListView=(PullToRefreshListView)rootView.findViewById(R.id.pull_to_refresh_listview_hotperson);
		 mListView.setOnItemClickListener(this);  
		 ListItems =new LinkedHashMap<String, List<Integer>>(); 
		 mAdapter=new SampleListAdapter1(getActivity(),ListItems);
		// p=new HotTopicService(this.getActivity());		   
		 ShowNetUnuse.showpic(imagetoast,getActivity());
	     initPerson();
	        mAdapter = new SampleListAdapter1(getActivity(),ListItems);  
	        // 设置PullToRefresh  
	    
	        mListView.setMode(com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.PULL_FROM_START);  
	        mListView.setOnRefreshListener(new OnRefreshListener<ListView>(){  
	   
				public void onRefresh(PullToRefreshBase<ListView> refreshView) {
					// TODO Auto-generated method stub
	           				String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),  
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
	       if( ps.getcount()==0)
	           mListView.setRefreshing(true);
		
	}

	
	
	
	
	
	
	
	private void initPerson() {
			// TODO Auto-generated method stub
			 ListItems = new LinkedHashMap<String, List<Integer>>();  
			 ArrayList<Integer> list=new ArrayList<Integer>();
			 list.add(0);
			 if(ps.getcount()>0)
		        for (int i = 0; i <ps.getcount(); i++) {  
		      //      ListItems.add(ps.find(i).getName(),[0]);  
		        	String tags=ps.find(i).getTags();
		        	 ArrayList<Integer> ls=new ArrayList<Integer>();
		        	 String t[]=tags.split("#");
		        	 for (String string : t) {
		        		 ls.add(Integer.parseInt(string));
					}
					
		           DateUtil.add(ListItems, ps.find(i).getName(), ls);
		        }
			 else
			 {
				 if(CheckNetState.checkNetwork(getActivity()))
				 {
					//  ListItems.add(new Topic(0,"稍等...正在加载","",""));
					
					  DateUtil.add(ListItems, "稍等...正在加载", list);
				 }
				 
				 else
					// ListItems.add(new Topic(0,"加载失败，检查网络连接。","",""));
					 DateUtil.add(ListItems, "加载失败，检查网络连接", list);
				  
			 }
				   
		}
	 private  LinkedHashMap<String,List<Integer>> getPersonData() throws ParseException {
			// TODO Auto-generated method stub
		 String url[]=new String[3];
		 url[0]=DateUtil.getRetrieveHotTopicDataUrl(-1);
		 url[1]=DateUtil.getRetrieveHotTopicDataUrl(-7);
		 url[2]=DateUtil.getRetrieveHotTopicDataUrl(-365);
		 int tag[]={1,7,365};
//	    List<Topic> list=new ArrayList<Topic>();
	    LinkedHashMap<String,List<Integer>>  map=new LinkedHashMap<String,List<Integer>>();
	    for(int n=0;n<3;n++)
	     {
	    	 try {
	 			String jsonString=ReadStreamCommon.readStream(new URL(url[n]).openStream());
	 			    JSONObject data=new JSONObject(jsonString);
	 				JSONObject d=data.getJSONObject("d");
	 				JSONArray facets=d.getJSONArray("Facets");
	 				JSONObject topics=facets.getJSONObject(2);
	 				//String k=topics.getString("Key");
	 				JSONObject Value=topics.getJSONObject("Value");
	 				JSONArray Items=Value.getJSONArray("Items");
	 				for(int i=0;i<Items.length();i++)
	 				{
	 					JSONObject itemsobject=Items.getJSONObject(i);
	 					String key=itemsobject.getString("key");
	 					String value=itemsobject.getString("value");					
	 					
	 				/*	Topic topic=new Topic(i,key,value);
	 					if(!ps.findor(i))    ps.save(topic);
	 				    else    ps.update(topic);*/
	 					
	// 					list.add(topic);
	 					 List<Integer>   tags=new ArrayList<Integer>();
		 				 tags.add(tag[n]);
	   					DateUtil.add(map, key, tags);
	   					
	 				   /* List<Integer>   tags=new ArrayList<Integer>();
	 				    tags.add(tag[n]);
	 					map.put(tags,topic);*/
	 				}
	 		 } catch (Exception e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}  
	     }
		   System.out.println(map);
		   Set<String> set=map.keySet();
		   for(int i=0;i<map.size();i++)
		   {
			   String topicname=(String)set.toArray()[i];
			   String tags="";
			   List<Integer> list=map.get(topicname);
			   for (Integer integer : list) {
				tags+=integer+"#";
			    }
			   Stopic stopic=new Stopic(i,topicname,tags);
			   System.out.println(stopic);
				if(!ps.findor(i))    ps.save(stopic);
				    else    ps.update(stopic);
		   }
			return map;
		}
	 class FinishRefresh extends AsyncTask<Void,Void, LinkedHashMap<String,List<Integer>>>{

			


			@Override
			protected  LinkedHashMap<String,List<Integer>> doInBackground(Void... params) {
				// TODO Auto-generated method stub
				 LinkedHashMap<String,List<Integer>>  personlist = null;
				if(!CheckNetState.checkNetwork(getActivity()))
	             {
	                 //显示Toast
					 Toast.makeText(getActivity(), "网络连接不可用", Toast.LENGTH_SHORT).show();
	                 return null;
	            }
				
				 else 
				{
					 try {
						  personlist=getPersonData();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 return personlist;
				 }
			}
			@Override
			protected void onPostExecute( LinkedHashMap<String,List<Integer>> result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
	            if(result!=null){
	            	Set<String> set=result.keySet();
					ListItems.clear();
				    for(int i=0;i<result.size();i++)
					// ListItems.add(result.get(i));
				    	DateUtil.add(ListItems,(String)set.toArray()[i] ,result.get((String)set.toArray()[i]));
				}
				
	            mAdapter.notifyDataSetChanged();  
	            mListView.onRefreshComplete(); 
			}
	}
	    
	 
	 
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Set<String> set=ListItems.keySet();
		String topicname=(String)set.toArray()[arg2-1];
		Log.i("click", "1w");
		Intent intent=new Intent(getActivity(),BrowseActivity.class);
		intent.putExtra("name",topicname );
	     
	     
	   	Log.i("click", "5w");
	  	startActivity(intent);
	}


}
