package lj.com.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import lj.com.model.Trends;
import lj.com.service.HotTopicService;
import lj.com.service.TrendArrayService;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TrendsChartActivity extends Activity {
//	private String name;
	LinearLayout li1;
	TrendArrayService t=new TrendArrayService(TrendsChartActivity.this);
//	private String relative;
	private TextView pro;
	private ProgressDialog myDialog;
	View mChartView;
	private String convey;
	private String name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		  setContentView(R.layout.trendschart);
		  
	        //�õ���Դ
	        //li1 = (LinearLayout) findViewById(R.id.li1);
	       
		  name=this.getIntent().getStringExtra("name2");
//		  relative=this.getIntent().getStringExtra("relative2");
		  convey=this.getIntent().getStringExtra("convey");
		  Log.i("TRENDSchart", convey);
		  Log.i("tag", "1");
		   li1 = (LinearLayout) findViewById(R.id.li1);
		 
		 //  pro=(TextView)this.findViewById(R.id.pro);
//		    builder = new AlertDialog.Builder(TrendsChartActivity.this);
//	    	builder.setMessage("���ڿ��ټ���......");
//           builder.show();
		   
		   Log.i("tag", "2");
		   if(checkNetwork())
		   {
			   li1.setBackgroundResource(R.drawable.load);
			   new TrendsreportAsyncTask(TrendsChartActivity.this).execute(convey);  
		   }  
		   else
		   {
			   TextView tv=new TextView(this);
			   tv.setText("��������ʧ��");
			   Button button =new Button(this);
			  button.setText("���¼���");
			  LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(      
	                   LinearLayout.LayoutParams.FILL_PARENT,      
	                   LinearLayout.LayoutParams.FILL_PARENT      
	           );      
			li1.setLayoutParams(p);
		
			li1.setOrientation(LinearLayout.VERTICAL);
			  li1.setBackgroundColor(Color.WHITE);
			  LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(      
	                   LinearLayout.LayoutParams.WRAP_CONTENT,      
	                   LinearLayout.LayoutParams.WRAP_CONTENT     
	           );      
			 li1.addView(tv,p1);
			  li1.setGravity(Gravity.CENTER|Gravity.CENTER_VERTICAL);
			
			 li1.addView(button,p1);
			 button.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(!checkNetwork())
					{
					    Toast.makeText(TrendsChartActivity.this, "�������ʧ��", Toast.LENGTH_LONG).show();
					    return;
					}
				else 
				{
					li1.removeAllViews();
					 li1.setBackgroundResource(R.drawable.load);
					   new TrendsreportAsyncTask(TrendsChartActivity.this).execute(convey); 
				}
				
				}
			});
			  
			   
//			   Intent intent=new Intent(this,CheckActivity.class);
//			   startActivity(intent);
			   
		   }
		   
		   
//		  int size=t.getcount();
//		  Log.i("tag", "3");
//		  Log.i("tag", "aa"+size);
//		  if(size==0)
//		  {  new TrendsreportAsyncTask(TrendsChartActivity.this).execute(name);  
//		  Log.i("tag", "bb");
//		  }
//		  else{
//			  Log.i("tag", "4");
//			  int x[]=new int[size];
//			String Lable[]=new String[size];
//			int  yPos[]=new int[size];
//			int yNeg[]=new int[size];
//			int yNeu[]=new int[size];
//			int yAll[]=new int[size];
//			for(int i=0;i<size;i++)
//			{
//				Trends trend=t.find(i);
//				Log.i("tag", trend.toString());
//				x[i]=i;
//				Lable[i]=trend.getDate();
//				yPos[i]=trend.getPos();
//				yNeg[i]=trend.getNeg();
//				yNeu[i]=trend.getNeu();
//				yAll[i]=trend.getAll();
//			}
//			
//			 Log.i("tag", "5");
//		     li1.addView(paint(x,Lable,yPos,yNeg,yNeu,yAll)); 
//		     Log.i("tag", "6");
//		  }
			
// 
		 
	}
	
	 private boolean checkNetwork() {
	      ConnectivityManager conn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	      NetworkInfo net = conn.getActiveNetworkInfo();
	      if (net != null && net.isConnected()) {
	          return true;
	      }
	      return false;
	  }     
	
	
	//trendsreport
		 private int max(int a[])
		    {
		    	int max=a[0];
		    	for(int i=0;i<a.length;i++)
		    	{
		    		if(a[i]>max)   max=a[i];
		    	}
		    	return max;
		    }
		    private int min(int a[])
		    {
		    	int min=a[0];
		    	for(int i=0;i<a.length;i++)
		    	{
		    		if(a[i]<min)   min=a[i];
		    	}
		    	return min;
		    }
		    private View paint(int xdate[],String Lable[],int yPos[],int yNeg[],int yNeu[],int yAll[])
		    {
		    	// String[] titles = new String[] { "Crete", "Corfu", "Thassos", "Skiathos" };
		    	 String[] titles = new String[] { "Pos", "Neg", "Neu", "All" };
		        // List<double[]> x = new ArrayList<double[]>();
		    	 List<int []> x = new ArrayList<int[]>();
		         for (int i = 0; i < titles.length; i++) {
		          //  x.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
		        	 x.add(xdate);
		         }
		         List<int []> values = new ArrayList<int[]>();
		         values.add(yPos);
		         values.add(yNeg);
		         values.add(yNeu);
		         values.add(yAll);
//		         values.add(new double[] { 12.3, 12.5, 13.8, 16.8, 20.4, 24.4, 26.4, 26.1, 23.6, 20.3, 17.2, 13.9 });
//		         values.add(new double[] { 10, 10, 12, 15, 20, 24, 26, 26, 23, 18, 14, 11 });
//		         values.add(new double[] { 5, 5.3, 8, 12, 17, 22, 24.2, 24, 19, 15, 9, 6 });
//		         values.add(new double[] { 9, 10, 11, 15, 19, 23, 26, 25, 22, 18, 13, 10 });
		         int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.CYAN, Color.YELLOW };
		         PointStyle[] styles = new PointStyle[] { PointStyle.POINT, PointStyle.POINT, PointStyle.POINT, PointStyle.POINT };
		         XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		         int length = renderer.getSeriesRendererCount();
		         for (int i = 0; i < length; i++) {
		             ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
		         }
		         for(int i=0;i<xdate.length;i++)
		         {
		        	// if(i%100==0)
		        	  //  renderer.addXTextLabel(i,Lable[i]); 
		         }  
		         
		         
		         //here
		         setChartSettings(renderer,convey+"媒体报道趋势","Date","trend",min(xdate),max(xdate), 0, max(yAll), Color.LTGRAY, Color.LTGRAY);
		         renderer.setXLabelsAngle(-25); 
		         renderer.setXLabels(30);
		         renderer.setYLabels(10);
		         renderer.setShowGrid(true);
		         renderer.setXLabelsAlign(Align.RIGHT);
		         renderer.setYLabelsAlign(Align.RIGHT);
		         renderer.setXLabels(0);
		         
		         renderer.setClickEnabled(true); //�Ƿ���Ե��  
		         renderer.setSelectableBuffer(30); //�������Ĵ�С
		        
		         renderer.setZoomButtonsVisible(true);
		         renderer.setPanLimits(new double[] { 0, max(xdate), 0, max(yAll) });
		        renderer.setZoomLimits(new double[] { 0,  max(xdate), 0,  max(yAll)});
		         renderer.setZoomInLimitY(max(yAll));
		         View view = ChartFactory.getLineChartView(this, buildDataset(titles, x, values), renderer);
		         view.setBackgroundColor(Color.BLACK);
		         return view;
//		        Intent intent = ChartFactory.getLineChartIntent(this, buildDataset(titles, x, values), renderer);
//		return intent;
		    }
		    private XYMultipleSeriesRenderer buildRenderer(int[] colors, PointStyle[] styles) {
		        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		        setRenderer(renderer, colors, styles);
		        return renderer;
		    }

		    private void setRenderer(XYMultipleSeriesRenderer renderer, int[] colors, PointStyle[] styles) {
		        renderer.setAxisTitleTextSize(30);
		        renderer.setChartTitleTextSize(50);
		        renderer.setLabelsTextSize(30);
		        renderer.setLegendTextSize(30);
		        renderer.setPointSize(5f);
		        renderer.setMargins(new int[] { 80, 80, 80, 80 });
		        int length = colors.length;
		        for (int i = 0; i < length; i++) {
		            XYSeriesRenderer r = new XYSeriesRenderer();
		            r.setColor(colors[i]);
		            r.setPointStyle(styles[i]);
		            renderer.addSeriesRenderer(r);
		        }
		    }
		    private void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle, String yTitle, double xMin, double xMax, double yMin, double yMax, int axesColor, int labelsColor) {
		        renderer.setChartTitle(title);
		        renderer.setXTitle(xTitle);
		        renderer.setYTitle(yTitle);
		        renderer.setXAxisMin(xMin);
		        renderer.setXAxisMax(xMax);
		        renderer.setYAxisMin(yMin);
		        renderer.setYAxisMax(yMax);
		        renderer.setAxesColor(axesColor);
		        renderer.setLabelsColor(labelsColor);
		    }

		    private XYMultipleSeriesDataset buildDataset(String[] titles, List<int[]> x, List<int[]> values) {
		        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		        addXYSeries(dataset, titles, x, values, 0);
		        return dataset;
		    }

		    private void addXYSeries(XYMultipleSeriesDataset dataset, String[] titles, List<int[]> x, List<int[]> values, int scale) {
		        int length = titles.length;
		        for (int i = 0; i < length; i++) {
		            XYSeries series = new XYSeries(titles[i], scale);
		            int[] xV = x.get(i);
		            int[] yV = values.get(i);
		            int seriesLength = xV.length;
		            for (int k = 0; k < seriesLength; k++) {
		                series.add(xV[k], yV[k]);
		            }
		            dataset.addSeries(series);
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
			
		  
		  
		    private List<Trends> getHotTopicJsonData(String name) {
				// TODO Auto-generated method stub
		    String queryurl="http://websensor.playbigdata.com/fss3/Search.aspx?q="+ URLEncoder.encode(name);
		 	String Gettopicmsg_url="http://websensor.playbigdata.com/fss3/service.svc/RetrieveData?url="+queryurl+"&getText=true&facet=true";
		 	  List<Trends> list=new ArrayList<Trends>();
			 try {
				//jsonData=readStream(new URL(Gettopiccluster_url).openStream());
				String jsonData=readStream(new URL(Gettopicmsg_url).openStream());
			    JSONObject data=new JSONObject(jsonData);
				JSONObject d=data.getJSONObject("d");
				JSONArray TrendInfoAll=d.getJSONArray("TrendInfoAll");
				Log.i("tag", TrendInfoAll.length()+"");
				for(int i=0;i<TrendInfoAll.length();i++)
				{
					JSONObject it=TrendInfoAll.getJSONObject(i);
					int  count=Integer.parseInt(it.getString("Count"));
					int neg=Integer.parseInt(it.getString("Neg"));
					int neu=Integer.parseInt(it.getString("Neu"));
					int pos=Integer.parseInt(it.getString("Pos"));
					String date=it.getString("Date");
					Trends trends=new Trends(date,count,neg,pos,neu);
					Log.i("tag", trends.toString());
					list.add(trends);
					
				 
//					if(t.find(date)==null)
//     					t.save(trends);
//					else
//					    t.update(trends);
					
				}
//				pro.setVisibility(View.GONE);
//				for(int i=0;i<list.size();i++)
//					Log.i(tag, list.get(i).toString());  
				
			 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
				
				
				return list;
			}
		    class TrendsreportAsyncTask extends AsyncTask<String,Void,List<Trends>>{

		    	private Context context;
		    	//AlertDialog.Builder builder = new AlertDialog.Builder(TrendsChartActivity.this);
//		    	builder.setMessage("���ڿ��ټ���......");
//                builder.show();
		    	public TrendsreportAsyncTask(Context context)
		    	{
		    		this.context= context;	
		    	}
		    	
		    	
		    	
				@Override
				protected void onPreExecute() {
					// TODO Auto-generated method stub
					super.onPreExecute();
					
					
				//	  builder = new AlertDialog.Builder(TrendsChartActivity.this);
//				    	builder.setMessage("���ڿ��ټ���......");
//			           builder.show();
				//	Toast.makeText(context, "���ڿ��ټ���", 500000).show();
	               
				}



				@Override
				protected List<Trends> doInBackground(String... params) {
					// TODO Auto-generated method stub
					
					return getHotTopicJsonData(params[0]);
				}

				@Override
				protected void onPostExecute(List<Trends> result) {
					// TODO Auto-generated method stub
					super.onPostExecute(result);
				
					//gv.setNumColumns(3);
//					Newsadapter adapter=new Newsadapter(LoadDataActivity.this,result,gv);
//				    gv.setAdapter(adapter);
					int size=result.size();
					Log.i("tag", size+"do");
					int x[]=new int[size];
					String Lable[]=new String[size];
					int  yPos[]=new int[size];
					int yNeg[]=new int[size];
					int yNeu[]=new int[size];
					int yAll[]=new int[size];
					for(int i=0;i<size;i++)
					{
						Trends trend=result.get(i);
						x[i]=i;
						Lable[i]=trend.getDate();
						yPos[i]=trend.getPos();
						yNeg[i]=trend.getNeg();
						yNeu[i]=trend.getNeu();
						yAll[i]=trend.getAll();
					//	Log.i("tag", Lable[i]+"   "+yPos[i]+"   "+yNeg[i]+"  "+yNeu[i]+"  "+yAll[i]+"   ");
					}
					// myDialog.cancel();	
					//pro.setVisibility(View.GONE);
					li1.setBackgroundResource(0);
					li1.setBackgroundColor(Color.WHITE);
					//  li1 = (LinearLayout) findViewById(R.id.li1);
					mChartView=paint(x,Lable,yPos,yNeg,yNeu,yAll);
				     li1.addView(mChartView);
				     
				     
				     mChartView.setOnClickListener(new View.OnClickListener() {
					       public void onClick(View v) {
					         SeriesSelection seriesSelection = ((GraphicalView) mChartView).getCurrentSeriesAndPoint();
					         double[] xy = ((GraphicalView) mChartView).toRealPoint(0);
					         if (seriesSelection == null) {
					           Toast.makeText(TrendsChartActivity.this, "No chart element was clicked", Toast.LENGTH_SHORT)
					               .show();
					         } else {
					           Toast.makeText(
					        		   TrendsChartActivity.this,
					               "Chart element in series index " + seriesSelection.getSeriesIndex()
					                   + " data point index " + seriesSelection.getPointIndex() + " was clicked"
					                   + " closest point value X=" + seriesSelection.getXValue() + ", Y=" + seriesSelection.getValue()
					                   + " clicked point value X=" + (float) xy[0] + ", Y=" + (float) xy[1], Toast.LENGTH_SHORT).show();
					         }
					       }
					     });
					     
					
		
				   
		   				
				}
		    }

	//����trendsreport
		

}
