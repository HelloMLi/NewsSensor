package lj.com.Fragment;

import com.zhy.view.CircleMenuLayout;
import com.zhy.view.CircleMenuLayout.OnMenuItemClickListener;

import lj.com.main.HotAreaActivity4;
import lj.com.main.HotOrgActivity5;
import lj.com.main.HotnActivity2;
import lj.com.main.HotpersonActivity3;
import lj.com.main.NetTopicActivity1;
import lj.com.main.R;
import lj.com.main.WebSensorHotTopics6;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;



public class CCBTab extends Fragment {
	private View rootView;
	private CircleMenuLayout mCircleMenuLayout;

	private String[] mItemTexts = new String[] { "网络话题 ", "热点名词", "热点人物",
			"热门机构", "热门地点", "热门话题" };
	private int[] mItemImgs = new int[] { R.drawable.home_mbank_1_normal,
			R.drawable.home_mbank_2_normal, R.drawable.home_mbank_3_normal,
			R.drawable.home_mbank_4_normal, R.drawable.home_mbank_5_normal,
			R.drawable.home_mbank_6_normal };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView= inflater.inflate(R.layout.activitymain, container, false);
		return rootView;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	
		mCircleMenuLayout = (CircleMenuLayout) rootView.findViewById(R.id.id_menulayout);
		mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);
		
		

		mCircleMenuLayout.setOnMenuItemClickListener(new OnMenuItemClickListener()
		{
			
			
			public void itemClick(View view, int pos)
			{
				Toast.makeText(getActivity()
                        .getApplicationContext(), mItemTexts[pos],
						Toast.LENGTH_SHORT).show();
				switch(pos)
				{
				case 0:
					Intent intent0=new Intent(getActivity(),NetTopicActivity1.class);
				  	startActivity(intent0);
					break;
				case 1:
					Intent intent1=new Intent(getActivity(),HotnActivity2.class);
				  	startActivity(intent1);
					break;
				case 2:
					Intent intent2=new Intent(getActivity(),HotpersonActivity3.class);
				  	startActivity(intent2);
					break;
				
				case 3:
					Intent intent3=new Intent(getActivity(),HotOrgActivity5.class);
				  	startActivity(intent3);
					break;
					
				case 4:
					Intent intent4=new Intent(getActivity(),HotAreaActivity4.class);
				  	startActivity(intent4);
					break;
					
				case 5:
					Intent intent5=new Intent(getActivity(),WebSensorHotTopics6.class);
				  	startActivity(intent5);
					break;
				
				}

			}
			
		
			public void itemCenterClick(View view)
			{
				Toast.makeText(getActivity()
                        .getApplicationContext(),
						"you can do something just like ccb  ",
						Toast.LENGTH_SHORT).show();
				
			}
		});
		
	}

	}