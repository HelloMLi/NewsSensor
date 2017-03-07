package lj.com.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends Activity {

	private Button btsearch;
	private EditText input;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		btsearch=(Button) this.findViewById(R.id.btsearch);
		input=(EditText)this.findViewById(R.id.input);
		btsearch.setOnClickListener(new btsearchListener());
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
	    	   if(keyCode==KeyEvent.KEYCODE_BACK)
	    	   {
	    		   Intent intent=getIntent();
				    intent.putExtra("back", "1");
				    SearchActivity.this.setResult(0,intent);
	    		   SearchActivity.this.finish();
	    	   }
		return super.onKeyDown(keyCode, event);
		
	}
	class btsearchListener implements View.OnClickListener
	{

		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(input.getText().toString().equals(""))
				Toast.makeText(SearchActivity.this, "请先输入要查询的内容", Toast.LENGTH_SHORT).show();
			else
			{
				Intent intent=new Intent(SearchActivity.this,BrowseActivity.class);
		     	intent.putExtra("name", input.getText().toString());
		     	startActivity(intent);
			}
		
		}
		
	}
}

