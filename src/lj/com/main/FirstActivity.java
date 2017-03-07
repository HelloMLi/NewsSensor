package lj.com.main;



import lj.com.Fragment.CCBTab;
import lj.com.Fragment.MyTab;
import lj.com.Fragment.SearchTab;
import lj.com.Fragment.SettingTab;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;


public class FirstActivity extends FragmentActivity implements OnClickListener {

	private Button mainpage;
	private Button setting;
	private Button mine;
	private Button search;
	 private Fragment mainpagetab;
	 private Fragment mytab;
   private Fragment searchtab;
	private Fragment settingtab;
	
	

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		 initView();
		 initEvents();
	     setSelect(0);
	}

	private void setSelect(int i) {
		// TODO Auto-generated method stub
		FragmentManager fm=this.getSupportFragmentManager();
		FragmentTransaction transaction=fm.beginTransaction();
		hideFragment(transaction);
		switch(i)
		{
		case 0:
			if(mainpagetab==null)
			{
				mainpagetab=new CCBTab();
				transaction.add(R.id.tab, mainpagetab);
			}
			else{
				transaction.show(mainpagetab);
			}
			//mImgWeixin.setImageResource(R.drawable.tab_weixin_pressed);
			break;
		case 1:
			if(searchtab==null)
			{
				searchtab=new SearchTab();
				transaction.add(R.id.tab , searchtab);
			}
			else{
				transaction.show(searchtab);
			}
		//	mImgWeifrd.setImageResource(R.drawable.tab_find_frd_pressed);
			break;
		case 2:
			if(mytab==null)
			{
				mytab=new MyTab();
				transaction.add(R.id.tab, mytab);
			}
			else{
				transaction.show(mytab);
			}
			//mImgWeiaddress.setImageResource(R.drawable.tab_address_pressed);
			break;
		case 3:
			if(settingtab==null)
			{
				settingtab=new SettingTab();
				transaction.add(R.id.tab, settingtab);
			}
			else{
				transaction.show(settingtab);
			}
		//	mImgsetting.setImageResource(R.drawable.tab_settings_pressed);
			break;
		}
		transaction.commit();
	}

	private void hideFragment(FragmentTransaction transaction) {
		// TODO Auto-generated method stub
		if(mainpagetab!=null)
		{
			transaction.hide(mainpagetab);
		}
		if(searchtab!=null)
		{
			transaction.hide(searchtab);
		}
		if(mytab!=null)
		{
			transaction.hide(mytab);
		}
		if(settingtab!=null)
		{
			transaction.hide(settingtab);
		}
	}

	private void initEvents() {
		// TODO Auto-generated method stub
		mainpage.setOnClickListener(this);
		setting.setOnClickListener(this);
		mine.setOnClickListener(this);
		search.setOnClickListener(this);
		//btsearch.setOnClickListener(this);
	}

	private void initView() {
		// TODO Auto-generated method stub
		mainpage=(Button)this.findViewById(R.id.mainpage);
		search=(Button)this.findViewById(R.id.sc);
		mine=(Button)this.findViewById(R.id.mine);
		setting=(Button)this.findViewById(R.id.setting);
//		btsearch=(Button)this.findViewById(R.id.btsearch);
//		input=(EditText)this.findViewById(R.id.input);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.mainpage:
			setSelect(0);
			break;
		case R.id.sc:
			setSelect(1);
			break;
		case R.id.mine:
			setSelect(2);
			break;
		case R.id.setting:
			setSelect(3);
			break;
//		case R.id.btsearch:
//			if(input.getText().toString().equals(""))
//				Toast.makeText(this, "请先输入要查询的内容", Toast.LENGTH_SHORT).show();
//			else
//			{
//				Intent intent=new Intent(this,BrowseActivity.class);
//		     	intent.putExtra("name", input.getText().toString());
//		     	startActivity(intent);
//			}
//		
//			break;
		}
	}
	
	
	

}
