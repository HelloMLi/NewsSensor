package lj.com.Adapter;

import java.util.List;

import lj.com.main.ImageUtils;
import lj.com.main.R;
import lj.com.model.Topic;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SampleListAdapter extends BaseAdapter {
	ViewHolder holder;
	protected static final String tag = "Newsadapter";
	private Context context;
	private List<Topic> list ;
	private ImageUtils imageutil;
//	private FocusService focuservice;
	public SampleListAdapter(Context context,List<Topic> list) {
		super();
		this.context = context;
		this.list=list;
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
		final Topic topic=list.get(position);
//		
//		String searchpic=topic.getName();
//		String query = URLEncoder.encode(searchpic);
//		String pic_url="http://183.174.228.2/pic/pic.aspx?c=1&q="+query+"&market=zh-CN";
//		
		if(convertView==null)
		{	holder=new ViewHolder();
		    convertView=LayoutInflater.from(context).inflate(R.layout.news_item, null);
  		    holder.tvtitle=(TextView)convertView.findViewById(R.id.title);
    	    holder.iv=(ImageView)convertView.findViewById(R.id.ivpic);
    /*	holder.tvtitle.setText(topic.getName());
		holder.desc.setText(topic.getValue());*/
			convertView.setTag(holder);
		}
		else
		{
			holder=(ViewHolder) convertView.getTag();	   
		}
		holder.tvtitle.setText(topic.getName());
  		//holder.desc.setText(topic.getValue());
  		holder.iv.setImageResource(R.drawable.graybac);
    	holder.iv.setTag(topic.getName());
  		imageutil.showImage(holder.iv, holder.tvtitle.getText().toString());
		return convertView;
	}
	private static class ViewHolder
	{
		TextView tvtitle;
		ImageView iv;
//		Button fc;
	}
}
