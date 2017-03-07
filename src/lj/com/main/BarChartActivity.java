package lj.com.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;


import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.json.JSONArray;
import org.json.JSONObject;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BarChartActivity extends Activity {
	
	
	// private List<String> list = new ArrayList<String>(); 
	// private Spinner mySpinner;    
	    private ArrayAdapter<String> adapter; 
	 
 	 LinearLayout li1;
 	 String tem[];
//	private String name;
//	private String relative;
	String queryurl;
	String Gettopicmsg_url;
	private int tag;
	String T[]={"相关子话题","相关机构","相关人物","相关地点","相关省份","相关城市","相关区县","正负面","相关正面词","相关负面词"};
	private String convey;
 	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.subtopicchart);
		// mySpinner = (Spinner)findViewById(R.id.spinner); 
//		 list.add("请选择感兴趣的词进行过滤");  
//		 list.add("1"); list.add("2"); 
//		 adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);    
//	        //第三步：为适配器设置下拉列表下拉时的菜单样式。    
//	        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);    
//	        //第四步：将适配器添加到下拉列表上    
//	        mySpinner.setAdapter(adapter); 
	        //得到资源
	        li1 = (LinearLayout) findViewById(R.id.li);
//	        name=this.getIntent().getStringExtra("name2");
	        tag=this.getIntent().getIntExtra("tag",0);
//			 relative=this.getIntent().getStringExtra("relative2");
	        convey=this.getIntent().getStringExtra("convey");
	       queryurl="http://websensor.playbigdata.com/fss3/Search.aspx?q="+ URLEncoder.encode(convey);
	    	Gettopicmsg_url="http://websensor.playbigdata.com/fss3/service.svc/RetrieveData?url="+queryurl+"&getText=true&facet=true";
	        //初始化柱状图
	   //    new  Refresh().execute(Gettopicmsg_url);
	       
	       
	       if(checkNetwork())
		   {
			   li1.setBackgroundResource(R.drawable.load);
			 
//			   LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(      
//	                   LinearLayout.LayoutParams.WRAP_CONTENT,      
//	                   LinearLayout.LayoutParams.WRAP_CONTENT     
//	           );      
//			li1.setLayoutParams(p);
			new  Refresh(tag).execute(Gettopicmsg_url); 
			  // li1.setBackgroundResource(0);
			//li1.addView(mySpinner);
		   }  
		   else
		   {
			   TextView tv=new TextView(this);
			   tv.setText("网络连接失败");
			   Button button =new Button(this);
			  button.setText("重新加载");
			  LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(      
	                   LinearLayout.LayoutParams.FILL_PARENT,      
	                   LinearLayout.LayoutParams.FILL_PARENT      
	           );
			  p.gravity=Gravity.CENTER;
			  li1.setGravity(Gravity.CENTER|Gravity.CENTER_VERTICAL);
			  
			li1.setLayoutParams(p);
		
			li1.setOrientation(LinearLayout.VERTICAL);
			  li1.setBackgroundColor(Color.WHITE);
			  LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(      
	                   LinearLayout.LayoutParams.WRAP_CONTENT,      
	                   LinearLayout.LayoutParams.WRAP_CONTENT     
	           );  
			 p1.gravity=Gravity.CENTER;
			 li1.addView(tv,p1);
			 
			
			 li1.addView(button,p1);
			 button.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(!checkNetwork())
					{
					    Toast.makeText(BarChartActivity.this, "加载数据失败", Toast.LENGTH_LONG).show();
					    return;
					}
				else 
				{
					li1.removeAllViews();
					 li1.setBackgroundResource(R.drawable.load);
					 new  Refresh(tag).execute(Gettopicmsg_url);
				}
				
				}
			});
		   }
	       
	       
		
	}
 	
	       @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
	    	   if(keyCode==KeyEvent.KEYCODE_BACK)
	    	   {
	    		    Intent intent=getIntent();
				    intent.putExtra("fit", "");
					 BarChartActivity.this.setResult(0,intent);
	    		   BarChartActivity.this.finish();
	    	   }
		return super.onKeyDown(keyCode, event);
		
	}

		private boolean checkNetwork() {
	 	      ConnectivityManager conn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	 	      NetworkInfo net = conn.getActiveNetworkInfo();
	 	      if (net != null && net.isConnected()) {
	 	          return true;
	 	      }
	 	      return false;
	 	  }     
	 	
 	 public GraphicalView xychar(String title, int[] value,
		      int color, int x, int y,double[] range, int []xLable ,String xtitle, boolean f,String []Lab)
	  {
 		
	    //多个渲染
	    XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
	    //多个序列的数据集
	    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
	    //构建数据集以及渲染
	 //   for (int i = 0; i < titles.length; i++) {
	    
	      XYSeries series = new XYSeries(title);
	    //  double [] yLable= value.get(i);
	      int[] yLable= value;
	      for (int j=0;j<yLable.length;j++) {
	        series.add(xLable[j],yLable[j]);
	      }
	      dataset.addSeries(series);
	      XYSeriesRenderer xyRenderer = new XYSeriesRenderer();
	      // 设置颜色
	      xyRenderer.setColor(color);
	      // 设置点的样式 //
	     // xyRenderer.setPointStyle(PointStyle.SQUARE);
	      // 将要绘制的点添加到坐标绘制中

	      renderer.addSeriesRenderer(xyRenderer);
	   // }
	      
	    //设置x轴标签数
	    renderer.setXLabels(x);
	    //设置Y轴标签数
	    renderer.setYLabels(y);
	    //设置x轴的最大值
	    renderer.setXAxisMax(x - 0.5);
	    //设置轴的颜色
	    renderer.setAxesColor(Color.BLACK);
	    //设置x轴和y轴的标签对齐方式
	    renderer.setXLabelsAlign(Align.CENTER);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    // 设置现实网格
	    renderer.setShowGrid(true); 
	  
//	    for(int i=0;i<xdate.length;i++)
//       {
//      	    renderer.addXTextLabel(i,Lable[i]); 
//       }  
//	    renderer.addXTextLabel(2008, "北京");
//	    renderer.addXTextLabel(2009, "广东");
//	    renderer.addXTextLabel(2010, "浙江");
//	    renderer.addXTextLabel(2011, "福建");
//	    renderer.addXTextLabel(2012, "厦门");
//	   
	    for(int i=0;i<xLable.length;i++)
	        {
	    	   if(Lab[i].length()>6) Lab[i]=Lab[i].substring(0,6)+"...";
	    	   String extra="";
	    	   String str="";
	    	   if(Lab[i].length()%2!=0)   
	    		   {
	    		   str=Lab[i].substring(0, Lab[i].length()/2*2);
	    		   extra=Lab[i].substring( Lab[i].length()/2*2,Lab[i].length());
	    		   }
	    	   else 
	    	   {
	    		   str=Lab[i];
	    		   extra="";
	    	   }
	    	   String s="";
	    	  
	    	   for(int j=0;j<str.length();j=j+2)
		    	   {
		    		  String p= str.substring(j, j+2);
		    		  s=s+p+"\n";
		    		  
		    	   } 
	    	   s=s+extra;Log.i("xxx",s);
	       	renderer.addXTextLabel(xLable[i],s); 
	       	
	        }  
	    renderer.setXLabels(0);
	  //  renderer.setXLabelsAngle(-90); 
	    
	    
	    
	    renderer.setShowAxes(true); 
	    // 设置条形图之间的距离
	    renderer.setBarSpacing(0.2);
	    renderer.setInScroll(false);
	    renderer.setPanEnabled(false, false);
	    renderer.setClickEnabled(false);
	    //设置x轴和y轴标签的颜色
	    renderer.setXLabelsColor(Color.BLACK);
	    renderer.setYLabelsColor(0,Color.BLACK);
	      
	    int length = renderer.getSeriesRendererCount();
	    //设置图标的标题
	    renderer.setChartTitle(xtitle);
	    renderer.setChartTitleTextSize(60);
	    renderer.setLabelsColor(Color.BLACK);
	    renderer.setLabelsTextSize(30);
	    //设置图例的字体大小
	    renderer.setLegendTextSize(30);
	    //缩放设置
	    renderer.setPanEnabled(false, false);
	    renderer.setZoomEnabled(false, false);
	    //图例边距
	    renderer.setMargins(new int[]{150,150,150,80});//上左下右
	    
	    //设置x轴和y轴的最大最小值
	    renderer.setRange(range);
	    renderer.setMarginsColor(0x00888888);
	    for (int i = 0; i < length; i++) {
	      SimpleSeriesRenderer ssr = renderer.getSeriesRendererAt(i);
	      ssr.setChartValuesTextAlign(Align.RIGHT);
	      ssr.setChartValuesTextSize(30);
	      ssr.setDisplayChartValues(f);
	    }
	    GraphicalView mChartView = ChartFactory.getBarChartView(getApplicationContext(),
	        dataset, renderer, Type.DEFAULT);

	    return mChartView;
	  }
	 class Refresh extends AsyncTask<String,Void,Map<String,Integer>>{

	    	private int Tag;

	    	
			public Refresh(int tag) {
				super();
				Tag = tag;
			}

			@Override
			protected Map<String,Integer> doInBackground(String... params) {
				// TODO Auto-generated method stub
				 return getTopicData(params[0],Tag);
			}

			@Override
			protected void onPostExecute(Map<String, Integer> result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				int size=result.size();
				String []x=new String[size];
				tem=new String[size];
				int y[]=new int[size];
				Set set=result.entrySet();
				Iterator iterator =set.iterator();
				int i=0;
				while(iterator.hasNext())
				{
					Map.Entry mapentry=(Entry) iterator.next();
					x[i]=(String) mapentry.getKey();
					y[i]=(Integer) mapentry.getValue();
					Log.i("iterator", x[i]+"   "+y[i]);
					i++;
				}
				
				for(int k=0;k<size-1;k++)
					for(int j=0;j<=size-k-2;j++)
					{
						if(y[j]<y[j+1])   
						{
							int t1=y[j];
							y[j]=y[j+1];
							y[j+1]=t1;
							
							String t2=x[j];
							x[j]=x[j+1];
							x[j+1]=t2;
						}
					}
				for(int m=0;m<size;m++)
				{
					Log.i("yyy", x[m]+" "+y[m]);
	              tem[m]=x[m];
				}
				
				
				 //柱状图的两个序列的名字
					String title = T[tag];
				        //存放柱状图两个序列的值
				  //  double[] d1 = new double[] { 0.1, 0.3, 0.7, 0.8, 0.5 };
				        //两个状的颜色
			      int color=0xff1E90FF;	
			      int x1[]=new int[size];
			      for(int k=0;k<size;k++)
			    	  x1[k]=k+1;
			     li1.setBackgroundColor(0);
			     TextView tv=new TextView(BarChartActivity.this);
			     tv.setText("请选择感兴趣的词进行过滤:");
			     tv.setTextColor(Color.BLACK);
			    tv.setTextSize(20);
			     
			     li1.addView(tv);
			     final Spinner mySpinner=new Spinner(BarChartActivity.this);
			    // adapter=new ArrayAdapter<String>(BarChartActivity.this,android.R.layout.simple_list_item_multiple_choice,x);
			     adapter=new ArrayAdapter<String>(BarChartActivity.this,R.layout.check,tem);
			     mySpinner.setPrompt("请选择感兴趣的词进行过滤");
			     
			     mySpinner.setAdapter(adapter);
			     mySpinner.setBackgroundColor(Color.WHITE);
			     mySpinner.setOnItemSelectedListener( new mListener());
			    
//			     LinearLayout li2=new  LinearLayout(BarChartActivity.this);
//			     li2.setOrientation(LinearLayout.HORIZONTAL);
//			     li2.addView(mySpinner,new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
//				            LayoutParams.WRAP_CONTENT));
			     li1.addView(mySpinner,new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,
				            LayoutParams.WRAP_CONTENT));
			    Button b=new Button(BarChartActivity.this);
			    b.setText("进行过滤");
			    //b.setTextColor(Color.BLACK);
			    b.setBackgroundColor(R.drawable.shape2);
			   b.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 String selected=(String) mySpinner.getSelectedItem();
					 Toast.makeText(BarChartActivity.this,selected, Toast.LENGTH_SHORT).show();
					 Intent intent=getIntent();
					 intent.putExtra("fit", selected);
					 
					 BarChartActivity.this.setResult(0,intent);
					 BarChartActivity.this.finish();
					 
				}
			});
			     li1.addView(b);
			  
//			     li1.addView(li2,new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT,
//				            LayoutParams.WRAP_CONTENT));
			     final View  mChartView= xychar(title, y, color, size, 4, new double[] { 0,
					           size+0.5, 0,max(y)+0.5}, x1, T[tag], true,x);
		     
				 li1.addView( mChartView ,  new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
					            LayoutParams.WRAP_CONTENT));
				 
				 
				 
//				 
//				 mChartView.setOnClickListener(new View.OnClickListener() {
//				       public void onClick(View v) {
//				         SeriesSelection seriesSelection = ((GraphicalView) mChartView).getCurrentSeriesAndPoint();
//				         double[] xy = ((GraphicalView) mChartView).toRealPoint(0);
//				         if (seriesSelection == null) {
//				           Toast.makeText(BarChartActivity.this, "No chart element was clicked", Toast.LENGTH_SHORT)
//				               .show();
//				         } else {
//				           Toast.makeText(
//				        		   BarChartActivity.this,
//				               "Chart element in series index " + seriesSelection.getSeriesIndex()
//				                   + " data point index " + seriesSelection.getPointIndex() + " was clicked"
//				                   + " closest point value X=" + seriesSelection.getXValue() + ", Y=" + seriesSelection.getValue()
//				                   + " clicked point value X=" + (float) xy[0] + ", Y=" + (float) xy[1], Toast.LENGTH_SHORT).show();
//				         }
//				       }
//				     });
				     
//				}
			}
			
	 }
		class mListener implements OnItemSelectedListener
		{

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(BarChartActivity.this,tem[arg2], Toast.LENGTH_SHORT).show();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		}
	
	 private int max(int a[])
	    {

		 if(a.length>0)
		 {
			 int max=a[0];
		    	for(int i=0;i<a.length;i++)
		    	{
		    		if(a[i]>max)   max=a[i];
		    	}
	    	return max;
		 }
		 else return 0;
	    	

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
	 
	 private  Map<String,Integer> getTopicData(String url,int tag) {
			// TODO Auto-generated method stub
		 Map<String,Integer> map=new HashMap<String,Integer>();
		 try {
			    String jsonString=readStream(new URL(url).openStream());
			    Log.i("tag", jsonString);
			    JSONObject data=new JSONObject(jsonString);
				JSONObject d=data.getJSONObject("d");
			    Log.i("tag", 1+"");
			    JSONArray Facets=d.getJSONArray("Facets");
			    Log.i("tag",2+"");
//				for(int i=0;i<Facets.length();i++)
//				{
					 Log.i("tag", 3+"");
					JSONObject n=Facets.getJSONObject(tag);
					JSONObject Value=n.getJSONObject("Value");
					JSONArray Items=Value.getJSONArray("Items");
					for(int j=0;j<Items.length();j++)
					{
						JSONObject obj=Items.getJSONObject(j);
						String key=obj.getString("key");
						int value=Integer.parseInt(obj.getString("value"));
						switch(tag)
						{
						case 0:map.put(key, value);
						       Log.i("nug", key+" "+value);
						       break;
						case 1:map.put(key, value);
					       break;
						case 2:map.put(key, value);
					       break;
					    case 3:key=key.substring(7, key.length());
					    	map.put(key, value);
					       break;
					    case 4:key=key.substring(7, key.length());
					    	map.put(key, value);
					       break;
					    case 5:key=key.substring(7, key.length());
					    	map.put(key, value);
					       break;
					    case 6:map.put(key, value);
					       break;
					    case 7:if(key.equals("1")) key="负面";
					          if(key.equals("2")) key="中性";
					         if(key.equals("3")) key="正面";
					    	map.put(key, value);
					       break;
					    case 8:map.put(key, value);
					       break;
					    case 9:map.put(key, value);
					       break;
						}
				
					}
					//li1.setBackgroundResource(0);
//			}
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			return map;
		}

}