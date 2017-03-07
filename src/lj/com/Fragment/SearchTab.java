package lj.com.Fragment;

import lj.com.main.BrowseActivity;
import lj.com.main.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class SearchTab extends Fragment {

	private Button btsearch;
	private EditText input;
	private View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i("tag", 1+"");
		 rootView = inflater.inflate(R.layout.search, container, false);
		 //mview=rootView;
		
		// btsearch.setOnClickListener(this);
		//return inflater.inflate(R.layout.search, null);
		 Log.i("tag", 2+"");
		 return rootView;
		//return inflater.inflate(R.layout.tab02, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.i("tag", 3+"");
		btsearch=(Button)rootView.findViewById(R.id.btsearch);
		input=(EditText)rootView.findViewById(R.id.input);
		Log.i("tag", 4+"");
		btsearch.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("tag", 5+"");
				if(input.getText().toString().equals(""))
					Toast.makeText(getActivity()
	                        .getApplicationContext(), "请先输入要查询的内容", Toast.LENGTH_SHORT).show();
				else
				{
					Intent intent=new Intent(getActivity()
	                        .getApplicationContext(),BrowseActivity.class);
			     	intent.putExtra("name", input.getText().toString());
			     	startActivity(intent);
				}
			}
		});
		
		
		
	}
}
