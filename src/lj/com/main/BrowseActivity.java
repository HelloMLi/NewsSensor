package lj.com.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import lj.com.Adapter.DeleteAdapter;
import lj.com.Adapter.DetailAdapter;
import lj.com.model.TextMessage;
import lj.com.service.FocusService;
import lj.com.view.SlidingMenu;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class BrowseActivity extends Activity  {
	private SlidingMenu mLeftMenu;
	private Button trendsreport;
	private Button subtopic;
	private Button Entity_Orgnization;
	private Button Entity_Person;
	private Button province;
	private Button city;
	private Button countryside;
	private Button Entity_Location;
	private Button pos_neg;
	private Button pos;
	private Button neg;
	private String name;
	private ListView detlv;
	private int num=0;
	private TextView tv;
	private TextView totalreport;
	private TextView posreport;
	private TextView negreport;
	private String relative;
	private String s;
	private Button fc;
	private FocusService focuservice;
	private String t;
	//private TableLayout li;
	private Toast imageToast;
	//private LinearLayout lsv;
	private TextView fitname;
	private Button showornot;
	//private LinearLayout filter;
	private LinearLayout filter1;
	private View contentView;
	private LinearLayout layout;
	private TextView haosou;
	private Button bestmatch;
	private Button timeasc;
	private Button timedesc;
	
	private List<String> FilterStrings=new ArrayList<String>(); 
	private String copys;
	private ListView lv;
//	private TextView te;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i("haosoutimes", "a");
		
		
		Log.i("haosoutimes", "b");
		
		focuservice=new FocusService(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.browsemain);
       // lsv=(LinearLayout)this.findViewById(R.id.lsv);
        layout=(LinearLayout)this.findViewById(R.id.layout);
       // lsv.setBackgroundResource(R.drawable.load);
        layout.setBackgroundResource(R.drawable.load);
        mLeftMenu=(SlidingMenu) this.findViewById(R.id.id_menu);
        trendsreport=(Button)this.findViewById(R.id.trendsreport);
        subtopic=(Button)this.findViewById(R.id.subtopic);
        Entity_Orgnization=(Button)this.findViewById(R.id.Entity_Orgnization);
        Entity_Person=(Button)this.findViewById(R.id.Entity_Person);
        province=(Button)this.findViewById(R.id.province);
        city=(Button)this.findViewById(R.id.city);
        countryside=(Button)this.findViewById(R.id.countryside);
        Entity_Location=(Button)this.findViewById(R.id.Entity_Location);
        pos_neg=(Button)this.findViewById(R.id.pos_neg);
        pos=(Button)this.findViewById(R.id.pos);
        neg=(Button)this.findViewById(R.id.neg);
        name=this.getIntent().getStringExtra("name");
        relative=this.getIntent().getStringExtra("relative");
        Log.i("relative", "relative="+relative);
        Log.i("relative", "name="+name);
        detlv=(ListView)BrowseActivity.this.findViewById(R.id.detlv);
        fc=(Button)this.findViewById(R.id.foc);
        tv=(TextView)this.findViewById(R.id.tv);
       // List<TextMessage> list;
        
        bestmatch=(Button)this.findViewById(R.id.bestmatch);
        timeasc=(Button)this.findViewById(R.id.timeasc);
        timedesc=(Button)this.findViewById(R.id.timedesc);
        
      
        timedesc.setTextColor(0xff1E90FF);
		//timedesc.setBackgroundColor(Color.WHITE);
        timedesc.setBackgroundColor(R.drawable.shape2);
		bestmatch.setTextColor(Color.WHITE);
		bestmatch.setBackgroundColor(R.drawable.shape1);
		timeasc.setTextColor(Color.WHITE);
		timeasc.setBackgroundColor(R.drawable.shape1);
        tv.setText(name);
        if(relative==null) 
        { 
        	t=name;
        	s="("+t+")";
        	
        }
         else 
        	 {
        	 //tv.setText(name);
        	 t=relative;
        	 s=t;
        	 }
       //t=tv.getText().toString();
       copys=s;    
        if( focuservice.findfocus(name))
			fc.setText("取消关注");
	   else
		    fc.setText("加关注");
        fc.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(fc.getText().toString().equals("加关注"))
					{
						Toast.makeText(BrowseActivity.this, name+"加关注成功", Toast.LENGTH_SHORT).show();
						Log.i("relative","加关注成功");
						fc.setText("取消关注");
						focuservice.save(name);
						 Log.i("relative","添加至数据库成功");
					} 
					else
					{
						Toast.makeText(BrowseActivity.this, name+"取消关注成功", Toast.LENGTH_SHORT).show();
						Log.i("relative","取消关注成功");
						fc.setText("加关注");
					    focuservice.delete(name);
					    Log.i("relative","从数据库删除成功");
					} 
			}
		});
        
        
        trendsreport.setOnClickListener(new TrendsReport());  
        subtopic.setOnClickListener(new Click());
        Entity_Orgnization.setOnClickListener(new Click());
        Entity_Person.setOnClickListener(new Click());
        province.setOnClickListener(new Click());
        city.setOnClickListener(new Click());
        countryside.setOnClickListener(new Click());
        Entity_Location.setOnClickListener(new Click());
        pos_neg.setOnClickListener(new Click());
        pos.setOnClickListener(new Click());
        neg.setOnClickListener(new Click()); 
       // filter=(LinearLayout)this.findViewById(R.id.filter);
        fitname=(TextView)this.findViewById(R.id.fitname);
        showornot=(Button)this.findViewById(R.id.showornot);
        fitname.setText("未选择过滤词；");
        showornot.setVisibility(View.INVISIBLE);
//        filter=new LinearLayout(BrowseActivity.this);
//		filter.setOrientation(LinearLayout.VERTICAL);
       // filter=(LinearLayout)this.findViewById(R.id.filter);
      contentView = LayoutInflater.from(this).inflate(
	               R.layout.filter, null);
	        // 设置按钮的点击事件
	 //   filter1= (LinearLayout) contentView.findViewById(R.id.filter1);
      lv=(ListView) contentView.findViewById(R.id.lv);
//	     te=new TextView(BrowseActivity.this);
//		te.setText("未选择过滤词，请从各分析表中选择感兴趣的词");
//		te.setTextColor(Color.BLACK);
		
		
		
		
		 if(checkNetwork())
		   { 
			//detlv.setBackgroundResource(R.drawable.load) ;
			new TextRefresh().execute(s,"1");
			new  HaosoutimesRefresh().execute(name);
			new ReportTimesRefresh().execute(name);
			//type="1"表示按时间降序    "2"表示按时间升序   "3"表示按最大匹配
			
			 bestmatch.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						detlv.setVisibility(View.GONE);
						layout.setBackgroundResource(R.drawable.load) ;
						bestmatch.setTextColor(0xff1E90FF);
						bestmatch.setBackgroundColor(R.drawable.shape2);
						timedesc.setTextColor(Color.WHITE);
						timedesc.setBackgroundColor(R.drawable.shape1);
						timeasc.setTextColor(Color.WHITE);
						timeasc.setBackgroundColor(R.drawable.shape1);
						new TextRefresh().execute(s,"3");
					}
				});
			timedesc.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						detlv.setVisibility(View.GONE);
						layout.setBackgroundResource(R.drawable.load) ;
						timedesc.setTextColor(0xff1E90FF);
						timedesc.setBackgroundColor(R.drawable.shape2);
						bestmatch.setTextColor(Color.WHITE);
						bestmatch.setBackgroundColor(R.drawable.shape1);
						timeasc.setTextColor(Color.WHITE);
						timeasc.setBackgroundColor(R.drawable.shape1);
						new TextRefresh().execute(s,"1");
					}
				});
			timeasc.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					detlv.setVisibility(View.GONE);
					// TODO Auto-generated method stub
					layout.setBackgroundResource(R.drawable.load) ;
					timeasc.setTextColor(0xff1E90FF);
					timeasc.setBackgroundColor(R.drawable.shape2);
					bestmatch.setTextColor(Color.WHITE);
					bestmatch.setBackgroundColor(R.drawable.shape1);
					
					timedesc.setTextColor(Color.WHITE);
					timedesc.setBackgroundColor(R.drawable.shape1);
					new TextRefresh().execute(s,"2");
				}
			});
			 
			
		   }  
		   else
		   {
			   imageToast=new Toast(this);
			   //定义一个InageView对象
		        ImageView imageView=new ImageView(this);
		        //为ImageView对象设置上去一张图片
		        imageView.setImageResource(R.drawable.netstatetip);
		        //将ImageView对象绑定到Toast对象imageToasr上面去
		        imageToast.setView(imageView);
		        //设置Toast对象显示的时间长短
		        imageToast.setDuration(Toast.LENGTH_LONG);
		        imageToast.setGravity(Gravity.CENTER, 0, 0);
		       
		        	
		            //显示Toast
		            imageToast.show();
		 
		   }
	}
	
	private boolean checkNetwork() {
	      ConnectivityManager conn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	      NetworkInfo net = conn.getActiveNetworkInfo();
	      if (net != null && net.isConnected()) {
	          return true;
	      }
	      return false;
	  }     
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
	    	   if(keyCode==KeyEvent.KEYCODE_BACK)
	    	   {
	    		   Intent intent=getIntent();
				    intent.putExtra("backfocus", "1");
				  BrowseActivity.this.setResult(3,intent);
	    		  BrowseActivity.this.finish();
	    	   }
		return super.onKeyDown(keyCode, event);
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, intent);
	     s=copys;
		if(requestCode==0&&resultCode==0)
		{
			Bundle data=intent.getExtras();
			final String result=data.getString("fit");
			//s+="OR"+"("+result+")";
			if(result.equals(""))
			{
				for (String m : FilterStrings) {
					
					s="("+m+")"+"AND"+s;
					
				}	
			}
			
			else
			{
			//s="("+result+")"+"AND"+s;
			FilterStrings.add(result);
			for (String m : FilterStrings) {
				
				s="("+m+")"+"AND"+s;
				
			}
			System.out.println("s:"+s);
			System.out.println(FilterStrings);
		//	filter.setText(filter.getText()+"   "+result);
			fitname.setText("已选择过滤词：");
			showornot.setVisibility(View.VISIBLE);
			showornot.setText("详情");
		/*	filter1.removeAllViews();
			final LinearLayout l=new LinearLayout(BrowseActivity.this);
			l.setOrientation(LinearLayout.HORIZONTAL);*/
		
			
			/*LinearLayout.LayoutParams param=new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT);
			l.setLayoutParams(param);
			l.setBackgroundColor(Color.WHITE);*/
			
			
		/*	ListView listview=new ListView(BrowseActivity.this);
			l.addView(listview);*/
			DeleteAdapter adapter=new DeleteAdapter(BrowseActivity.this, FilterStrings);
			lv.setAdapter(adapter);
			
			
			
		/*	final TextView tv=new TextView(BrowseActivity.this);
			tv.setTextColor(Color.BLACK);
			tv.setText(result);
			l.addView(tv);*/
			num++;
			/*final Button delete=new Button(BrowseActivity.this);
			 LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(      
	                   LinearLayout.LayoutParams.WRAP_CONTENT,      
	                   LinearLayout.LayoutParams.WRAP_CONTENT     
	           );  
			 p1.gravity=Gravity.RIGHT;
			delete.setText("X");
			delete.setTextColor(Color.RED);
			l.addView(delete,p1);
		*/
		//	filter1.addView(l);
		    showornot.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
//					if(showornot.getText().equals("展开"))   
//					{
//						showornot.setText("隐藏");
						//filter.setVisibility(View.VISIBLE);
				//	te.setText("");
						 final PopupWindow popupWindow = new PopupWindow(contentView,
					                LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT, true);
					        popupWindow.setTouchable(true);

					        popupWindow.setTouchInterceptor(new OnTouchListener() {

					     
					            public boolean onTouch(View v, MotionEvent event) {

					              //  Log.i("mengdd", "onTouch : ");

					                return false;
					                // 这里如果返回true的话，touch事件将被拦截
					                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
					            }

							
					        });
					        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
					        // 我觉得这里是API的一个bug
					        popupWindow.setBackgroundDrawable(getResources().getDrawable(
					                R.drawable.white_bac));

					        // 设置好参数之后再show
					        popupWindow.showAsDropDown(v);

//					}
//					else
//					{
//						showornot.setText("展开");
//						//filter1.setVisibility(View.INVISIBLE);
//					}
				}
			});
		    
			/*delete.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					l.removeView(tv);
					delete.setVisibility(View.GONE);
					s=s.replace("("+result+")"+"AND", "");
					Log.i("sss2", s);
					num--;
					if(num==0) 
						{
						fitname.setText("未选择过滤词；");
						//showornot.setVisibility(View.INVISIBLE);
						
						//filter1.addView(te);
						}
					
				}
			});*/
			
			
		}
		Log.i("sss", s);
		Toast.makeText(BrowseActivity.this, s, Toast.LENGTH_SHORT).show();
		}
	}
	
	
	  private String readStream(InputStream is)
		{
			
			InputStreamReader isr;
			String result ="";
			try {
				String line="";
				isr=new InputStreamReader(is,"utf-8");
			    BufferedReader br=new  BufferedReader(isr);
			    while((line=br.readLine())!=null)
			    {
			    	result+=line;
			    }
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
			
		}
		
	  public String dealText(String text)
		{
			
			for(int i=0;i <=text.length()-4;i++)
			{

				if(text.substring(i, i+4).equals("<em>"))
				{
					int ylength=text.length();
					text=text.substring(0, i)+text.substring(i+4, ylength);
				}
				
			}
			
			for(int i=0;i <=text.length()-5;i++)
			{

				if(text.substring(i, i+5).equals("</em>"))
				{
					int ylength=text.length();
					text=text.substring(0, i)+text.substring(i+5, ylength);
				}
			}
			return text;
		}
	private String getHaosou(String name)
	{
		Log.i("haosoutimes", "1");
		String url="http://websensor.playbigdata.com/fss3/service.svc/GetHaosouCount?query="+URLEncoder.encode(name)+"&start=&end=";
		String haosoutimes = null;
		try 
		{
			String jsonData=readStream(new URL(url).openStream());
		 JSONObject data = new JSONObject(jsonData);
		JSONObject d=data.getJSONObject("d");
		 haosoutimes=d.getString("Total");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return haosoutimes;
		
	}
	 class HaosoutimesRefresh extends AsyncTask<String,Void,String>{
			@Override
			protected String doInBackground(String... arg0) {
				// TODO Auto-generated method stub
				Log.i("haosoutimes", "2");
				return getHaosou(arg0[0]);
			}

			protected void onPostExecute(final String result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				//lsv.setBackgroundResource(0) ;
				haosou=(TextView)BrowseActivity.this.findViewById(R.id.haosou);
				haosou.setText(result);
			}	
		}


	 private Map<String,Integer> getReportTimes(String name)
		{
			Log.i("haosoutimes", "1");
			String queryurl="http://websensor.playbigdata.com/fss3/Search.aspx?q="+ URLEncoder.encode(name);
		 	String url="http://websensor.playbigdata.com/fss3/service.svc/RetrieveData?url="+queryurl+"&getText=true&facet=true";
			int  reportTimes = 0;
			int posReportTimes=0;
			int negReportTimes=0;
			Map<String,Integer> map=new HashMap<String,Integer>();
			try 
			{
				String jsonData=readStream(new URL(url).openStream());
			    JSONObject data = new JSONObject(jsonData);
			    JSONObject d=data.getJSONObject("d");
			    reportTimes=Integer.parseInt(d.getString("TotalCount"));
			    JSONArray TrendInfoAll= d.getJSONArray("TrendInfoAll");
			    for(int i=0;i<TrendInfoAll.length();i++)
			    {
			    	 JSONObject o= TrendInfoAll.getJSONObject(i); 
			    	 negReportTimes+= Integer.parseInt(o.getString("Neg"));
			    	 posReportTimes+=Integer.parseInt(o.getString("Pos"));
			    }			   
			    map.put("reportTimes", reportTimes);//测试log  map的值
			    map.put("posReportTimes", posReportTimes);
			    map.put("negReportTimes", negReportTimes);
			    Log.i("map",reportTimes+"  "+posReportTimes+"  "+negReportTimes);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return map;
			
		}
		 class ReportTimesRefresh extends AsyncTask<String,Void,Map<String,Integer>>{
				

				

				private TextView pagenum;

				@Override
				protected Map<String,Integer> doInBackground(String... arg0) {
					// TODO Auto-generated method stub
					Log.i("haosoutimes", "2");
					return getReportTimes(arg0[0]);
				}
				@Override
				protected void onPostExecute(Map<String, Integer> result) {
					// TODO Auto-generated method stub
					super.onPostExecute(result);
					totalreport=(TextView)BrowseActivity.this.findViewById(R.id.totalreport);
					pagenum=(TextView)BrowseActivity.this.findViewById(R.id.pagenum);
					posreport=(TextView)BrowseActivity.this.findViewById(R.id.posreport);
					negreport=(TextView)BrowseActivity.this.findViewById(R.id.negreport);
					Log.i("haosoutimes", "3");
					totalreport.setText(result.get("reportTimes").toString());
					posreport.setText(result.get("posReportTimes").toString());
					negreport.setText(result.get("negReportTimes").toString());
					 /* map.put("reportTimes", reportTimes);
				    map.put("posReportTimes", posReportTimes);
				    map.put("negReportTimes", negReportTimes);*/
					pagenum.setText("Results 1-20 of "+result.get("reportTimes"));
				}

				/*protected void onPostExecute(final String result) {
					// TODO Auto-generated method stub
					
					//lsv.setBackgroundResource(0) ;
					totalreport=(TextView)BrowseActivity.this.findViewById(R.id.totalreport);
					pagenum=(TextView)BrowseActivity.this.findViewById(R.id.pagenum);
					Log.i("haosoutimes", "3");
					totalreport.setText(result);
					pagenum.setText("Results 1-20 of "+result);
				
				}	*/
			}

	
	
	
	
	private List<TextMessage> getTextData(String name,String type) {
		// TODO Auto-generated method stub
		//type="1"表示按时间降序    "2"表示按时间升序   "3"表示按最大匹配
		Log.i("url",name+"     "+type);
		String tag=null;
		if(type.equals("1"))   tag="Time+Desc";
		else if(type.equals("2"))   tag="Time+Asc";
		else if(type.equals("3"))   tag="score+desc";
		String queryurl="http://websensor.playbigdata.com/fss3/Search.aspx?q="+URLEncoder.encode(name)+"&page=2"+"&sort="+tag;
		//http://websensor.playbigdata.com/fss3/search2.aspx?q=%e9%99%90%e8%b4%ad&sort=Time+Asc Event River
	 	Log.i("url",queryurl);
		String Gettopicmsg_url="http://websensor.playbigdata.com/fss3/service.svc/RetrieveData?url="+queryurl+"&getText=true&facet=true";
	 	//http://websensor.playbigdata.com/fss3/service.svc/RetrieveData?url=http://websensor.playbigdata.com/fss3/Search.aspx?q=李克强宁夏考察&sort=Time+Desc&getText=true&facet=true
	 	List<TextMessage> list=new ArrayList<TextMessage>();
	 try {		
		 String jsonData=readStream(new URL(Gettopicmsg_url).openStream());
		 JSONObject data=new JSONObject(jsonData);
		 JSONObject d=data.getJSONObject("d");
			JSONArray Documents=d.getJSONArray("Documents");
			//String lzy="";
			for(int i=0;i<Documents.length();i++)
			{
				JSONObject zy=Documents.getJSONObject(i);
				String Text=dealText(zy.getString("Text"));
				//int p=dealstr.length()/2;
				//String Text=dealstr.substring(0, p)+"...";
				Log.i("textmessage","1");
				String Date=zy.getString("Date");
				Log.i("textmessage","2");
				String entity="";
				
				 if( zy.get("Entity")==JSONObject.NULL ||zy.isNull("Entity"))
					 entity="";
				 else
				 {
					 JSONArray Entity=zy.getJSONArray("Entity");
					 for(int j=0;j<Entity.length();j++)
				    {
					JSONObject m=Entity.getJSONObject(j);
					//String value=m.getString("Value");
					String v="";
					JSONArray value=m.getJSONArray("Value");
					for(int k=0;k<value.length();k++)
					//value=value.substring(2, value.length()-2);
					    v+=value.getString(k)+" ";	
					String it=m.getString("Key")+":"+v+"   ";
					entity+=it;
				    }
				 }
				
				Log.i("textmessage","3");
				String nugget="";
			   if( zy.get("Nugget")==JSONObject.NULL ||zy.isNull("Nugget"))
				   nugget="";
			   else
			   {
				   nugget="Nugget:";
				   JSONArray Nugget=zy.getJSONArray("Nugget");
				for(int j=0;j<Nugget.length();j++)
				{
					//Nugget.getString(j);
					//JSONObject m=Nugget.getJSONObject(j);
					nugget+=Nugget.getString(j)+" ";
				}
			   }
				
				Log.i("textmessage","4");
				String docid=zy.getString("Id");
				Log.i("textmessage","5");
				String Opinion=zy.getString("Opinion");
				Log.i("textmessage","6");
			 //   lzy += Text +'\n';
				TextMessage textm=new TextMessage(Date, docid, entity, nugget,Text, Opinion);
				Log.i("queryurl",textm.toString());
				list.add(textm);
			//	lsv.setBackgroundResource(0) ;
				
			}
			
			//tv.setText(lzy);
		
	 } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
		return list;
	}
	
	 class TextRefresh extends AsyncTask<String,Void,List<TextMessage>>{

		
		
		private DetailAdapter adapter;

		@Override
		protected List<TextMessage> doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			return getTextData(arg0[0],arg0[1]);
		}

		protected void onPostExecute(final List<TextMessage> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			//lsv.setBackgroundResource(0);
			layout.setBackgroundResource(0) ;
			detlv.setVisibility(View.VISIBLE);
			adapter=new DetailAdapter(BrowseActivity.this,result);
			
			adapter.notifyDataSetChanged();
			detlv.setAdapter(adapter);
			for(int i=0;i<result.size();i++)
				 System.out.println(result.get(i).toString());
		}
		

	    	

		
	}

	    
	class Click  implements View.OnClickListener
	{

		public void onClick(View v) {
			// TODO Auto-generated method stub
			int tag=0;
			switch(v.getId())
			{
			case R.id.subtopic:tag=0;
				                break;
			case R.id.Entity_Orgnization:tag=1;
            break;
			case R.id.Entity_Person:tag=2;
            break;
			case R.id.Entity_Location:tag=3;
            break;
			case R.id.province:tag=4;
            break;
			case R.id.city:tag=5;
            break;
			case R.id.countryside:tag=6;
            break;
			case R.id.pos_neg:tag=7;
            break;
			case R.id.pos:tag=8;
            break;
			case R.id.neg:tag=9;
            break;
				                
			}
			Intent intent=new Intent(BrowseActivity.this,BarChartActivity.class);
	     	intent.putExtra("name2", name);
	     	intent.putExtra("tag", tag);
//	     	intent.putExtra("relative2",  s);
			
		//	intent.putExtra("convey",name);
			
			intent.putExtra("convey",s);
	     	startActivityForResult(intent,0);
		}
		
	}
	class TrendsReport implements View.OnClickListener
	{
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// new TrendsreportAsyncTask(BrowseActivity.this).execute("˫ʮһ");
			Intent intent=new Intent(BrowseActivity.this,TrendsChartActivity.class);
	     //	intent.putExtra("name2", name);
	     	
	     	//intent.putExtra("relative2",s);
			intent.putExtra("convey",s);
	     	startActivity(intent);
		}		
	}
		public void toggleMenu(View view)
	{
		 mLeftMenu.toggle();
		
	}
//		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
//				long arg3) {
//			// TODO Auto-generated method stub
//			TextMessage t=.get(position);
//			
//		}
	
}
