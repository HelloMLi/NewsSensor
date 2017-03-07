package lj.com.Adapter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import lj.com.main.ImageUtils;
import lj.com.main.R;
import lj.com.model.Topic;


import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SampleListAdapter1 extends BaseAdapter {
	ViewHolder holder;
	protected static final String tag = "Newsadapter";
	private Context context;
//	private List<Topic> list ;
	private LinkedHashMap<String, List<Integer>> list ;
	private Set<String> set;
	private ImageUtils imageutil;
//	private int hotsrc[]={R.drawable.hot1,R.drawable.hot7,R.drawable.hot365,R.drawable.g1,R.drawable.g7,R.drawable.g365};
	
//	private FocusService focuservice;
	public SampleListAdapter1(Context context,LinkedHashMap<String, List<Integer>> list) {
		super();
		this.context = context;
		this.list=list;
		this.set=list.keySet();
		imageutil=new ImageUtils();
		//focuservice=new FocusService();
		
	}


	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		 holder=null;
	//	final Topic topic=list.get(position);
        String topicname=(String) set.toArray()[position];
		List<Integer> l= list.get(topicname);
		/*String content="";
		for (Integer integer : l) {
			content+=integer+"#";
		}*/
		
		
		
//		String searchpic=topic.getName();
//		String query = URLEncoder.encode(searchpic);
//		String pic_url="http://183.174.228.2/pic/pic.aspx?c=1&q="+query+"&market=zh-CN";
//
		if(convertView==null)
		{	
			holder=new ViewHolder();
		    convertView=LayoutInflater.from(context).inflate(R.layout.news_item1, null);
  		    holder.tvtitle=(TextView)convertView.findViewById(R.id.title);
	      
    	    holder.iv=(ImageView)convertView.findViewById(R.id.ivpic);
    	    holder.niv[0]=(ImageView)convertView.findViewById(R.id.iv1);
    	    holder.niv[1]=(ImageView)convertView.findViewById(R.id.iv2);
    	    holder.niv[2]=(ImageView)convertView.findViewById(R.id.iv3);		
		    convertView.setTag(holder);
		}
		else
		{
			 holder=(ViewHolder) convertView.getTag();
		   
		}
		holder.iv.setImageResource(R.drawable.graybac);
	    holder.niv[0].setImageResource(R.drawable.whiteico);
	    holder.niv[1].setImageResource(R.drawable.whiteico);
	    holder.niv[2].setImageResource(R.drawable.whiteico);
		holder.tvtitle.setText(topicname);
		if(l.size()==1)
		{
			if(l.get(0)==1)    holder.niv[0].setImageResource(R.drawable.g1);
			else if(l.get(0)==7)    holder.niv[0].setImageResource(R.drawable.g7);
			else if(l.get(0)==365)    holder.niv[0].setImageResource(R.drawable.g365);
			
		}
		else if(l.size()>1)
		{
			for(int i=0;i<l.size();i++)
			{
				if(l.get(i)==1)   holder.niv[i].setImageResource(R.drawable.hot1);
				else if(l.get(i)==7)   holder.niv[i].setImageResource(R.drawable.hot7);
				else if(l.get(i)==365)   holder.niv[i].setImageResource(R.drawable.hot365);
			}
			
		}
		/* holder.niv[0].setTag(topicname);
		 holder.niv[1].setTag(topicname);
		 holder.niv[2].setTag(topicname);*/
  		holder.iv.setTag(topicname);
   		imageutil.showImage(holder.iv, holder.tvtitle.getText().toString());
		return convertView;
	}
	private static class ViewHolder
	{
		TextView tvtitle;
		ImageView iv;
		ImageView []niv=new ImageView[3];
	
	}
}
