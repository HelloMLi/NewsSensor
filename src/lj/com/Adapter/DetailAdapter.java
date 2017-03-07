package lj.com.Adapter;

import java.util.List;



import lj.com.main.R;
import lj.com.main.ShowWebView;
import lj.com.model.TextMessage;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class DetailAdapter extends BaseAdapter {

	private Context context;
	private List<TextMessage> list ;
	public DetailAdapter(Context context,List<TextMessage> list) {
		super();
		this.context = context;
		this.list=list;
		
//		
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
		final TextMessage t=list.get(position);
		
//		String searchpic=topic.getName();
//		String query = URLEncoder.encode(searchpic);
//		String pic_url="http://183.174.228.2/pic/pic.aspx?c=1&q="+query+"&market=zh-CN";
//		
		if(convertView==null)
		{	convertView=LayoutInflater.from(context).inflate(R.layout.detail_item, null);
		holder=new ViewHolder();
		holder.date=(TextView)convertView.findViewById(R.id.date);
		holder.dt=(Button)convertView.findViewById(R.id.dt);
	    holder.content=(TextView)convertView.findViewById(R.id.content);
		convertView.setTag(holder);
		}
		else
		{
			holder=(ViewHolder) convertView.getTag();
		}			
			holder.date.setText(t.getDate());
			holder.dt.setTag(position);
			holder.dt.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent(context,ShowWebView.class);
                   	intent.putExtra("docid", t.getDocid());
        	  	    context.startActivity(intent);
				}
			});
			//holder.content.setText(t.toString());
			holder.content.setText(t.getText());
			SpannableString sp = new SpannableString(t.getOpinion());
		    ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.WHITE);
			sp.setSpan(redSpan, 0,sp.length() , Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
		    sp.setSpan(new BackgroundColorSpan(0xFFEEB422), 0, sp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
			holder.content.append(sp);
			holder.content.append("\n");
			SpannableString en = new SpannableString(t.getEntity());
		//	en.setSpan(new BackgroundColorSpan(0xFFEEB422), 0, en.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
			en.setSpan(new ForegroundColorSpan(0xFFCD0000), 0, en.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
			
			holder.content.append(en);
			if(!t.getNugget().equals(""))
			   holder.content.append("\n");
			
			SpannableString nu = new SpannableString(t.getNugget());
			//nu.setSpan(new BackgroundColorSpan(0xFFEEB422), 0, nu.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
			nu.setSpan(new ForegroundColorSpan(0xFFCD0000), 0, nu.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
			holder.content.append(nu);
		
		return convertView;
	}
	private static class ViewHolder
	{
		TextView date;
		TextView content;
		Button dt;
		
	}
}
