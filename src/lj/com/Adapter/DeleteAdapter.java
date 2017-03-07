package lj.com.Adapter;

import java.util.List;
import lj.com.main.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class DeleteAdapter extends BaseAdapter {
	protected static final String tag = "Newsadapter";
	private Context context;
	private List<String> list;

	public DeleteAdapter(Context context,List<String> list) {
		super();
		this.context = context;
		this.list=list;
	
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

	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		final String s=list.get(position);
		
		if(convertView==null)
		{	
			convertView=LayoutInflater.from(context).inflate(R.layout.index, null);
		    holder=new ViewHolder();
		    holder.indextv1=(TextView)convertView.findViewById(R.id.indextv);
		    holder.btn1=(Button)convertView.findViewById(R.id.btn);	   
     		convertView.setTag(holder);
		}
		else
		{
			holder=(ViewHolder) convertView.getTag();
		}
		holder.indextv1.setText(s);
		 holder.btn1.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 showInfo(position);  
			}

			
				
			private void showInfo(final int position) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(context).setTitle("我的提示").setMessage("确定要删除吗？").
				setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						list.remove(position);  
                        // 通过程序我们知道删除了，但是怎么刷新ListView呢？  
                        // 只需要重新设置一下adapter 
						notifyDataSetChanged(); 
					}
				}).show();
			}
		});
                
            
		return convertView;
	}
	private class ViewHolder
	{
		TextView indextv1;
		Button btn1;
	}
}
