package lj.com.Adapter;

import java.util.List;



import lj.com.main.ImageUtils;
import lj.com.main.R;
import lj.com.model.Topic;
import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HotTopicAdapter extends BaseAdapter {
	protected static final String tag = "Newsadapter";
	private Context context;
	private List<Topic> list ;
	private ImageUtils imageutil;
	public HotTopicAdapter(Context context,List<Topic> list) {
		super();
		this.context = context;
		this.list=list;
		imageutil=new ImageUtils();
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
		ViewHolder holder;
		Topic topic=list.get(position);
		if(convertView==null)
		{	
			convertView=LayoutInflater.from(context).inflate(R.layout.nettopic_item, null);
		    holder=new ViewHolder();
		    holder.title=(TextView)convertView.findViewById(R.id.title);
			 holder.reltopic=(TextView)convertView.findViewById(R.id.reltopic);
		    holder.keyw=(TextView)convertView.findViewById(R.id.keyw);
			holder.iv=(ImageView)convertView.findViewById(R.id.ivpic);
			// holder.iv.setTag(topic.getName());
			convertView.setTag(holder);	
		}
		else
		{
			
			holder=(ViewHolder) convertView.getTag();
			
		//	 holder.iv=(ImageView) holder.iv.getTag();
		}
		if(topic.getName().equals("稍等...正在加载")||topic.getName().equals("加载失败，检查网络连接。"))
		{
			holder.title.setTextColor(Color.RED);
			holder.title.setTextSize(18);
			holder.title.setGravity(Gravity.CENTER);
			holder.iv.setVisibility(View.GONE);
		}	
		else
		{
			holder.title.setTextColor(Color.BLACK);
			holder.title.setTextSize(15);
			holder.title.setGravity(Gravity.LEFT);
			holder.iv.setVisibility(View.VISIBLE);
		}
		holder.title.setText(topic.getName());
		if(topic.getName().equals("稍等...正在加载")||topic.getName().equals("加载失败，检查网络连接。"))  
		{
			holder.reltopic.setText("");
			holder.keyw.setText("");
		}  
		else
		{
			 SpannableString spanttt = new SpannableString("相关话题：");
			 SpannableString spankw = new SpannableString("关键字：");
			 ForegroundColorSpan redSpan = new ForegroundColorSpan(0xFF1E90FF); 
			 spanttt.setSpan(redSpan, 0,spanttt.length() , Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
			 spankw.setSpan(redSpan, 0,spankw.length() , Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
			 holder.reltopic.setText(spanttt);
			 holder.reltopic.append("\n"+topic.getRelativetopic());
			 holder.keyw.setText(spankw);
			 holder.keyw.append(topic.getKeywords());
		}
		 holder.iv.setTag(topic.getName());
		 holder.iv.setImageResource(R.drawable.graybac);
		
		 imageutil.showImage(holder.iv, topic.getName());
		 return convertView;
	}
	private static class ViewHolder
	{
		TextView title;
		TextView keyw;
		TextView reltopic;
		ImageView iv;
	}
}
