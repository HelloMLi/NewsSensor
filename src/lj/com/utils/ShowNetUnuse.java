package lj.com.utils;

import lj.com.main.R;
import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.Toast;

public class ShowNetUnuse {
	
	public static boolean showpic( Toast imageToast,Context context)
	{
		   imageToast=new Toast(context);
	        //定义一个InageView对象
	        ImageView imageView=new ImageView(context);
	        //为ImageView对象设置上去一张图片
	        imageView.setImageResource(R.drawable.netstatetip);
	        //将ImageView对象绑定到Toast对象imageToasr上面去
	        imageToast.setView(imageView);
	        //设置Toast对象显示的时间长短
	        imageToast.setDuration(Toast.LENGTH_LONG);
	        imageToast.setGravity(Gravity.CENTER, 0, 0);
	        if(!CheckNetState.checkNetwork(context))
	        {
	        	
	            //显示Toast
	            imageToast.show();
	            return true;
	        }
	        else return false;
	        
	}
	

}
