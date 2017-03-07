package lj.com.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckNetState {

	public static boolean checkNetwork(Context context) {
	      ConnectivityManager conn = (ConnectivityManager)context. getSystemService(Context.CONNECTIVITY_SERVICE);
	      NetworkInfo net = conn.getActiveNetworkInfo();
	      if (net != null && net.isConnected()) {
	          return true;
	      }
	      return false;
	  }     
}
