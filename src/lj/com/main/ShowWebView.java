package lj.com.main;

import java.net.URLEncoder;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ShowWebView extends Activity {

	private WebView webView;
	private String id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.web);
		 webView = (WebView) findViewById(R.id.webView);
		id=this.getIntent().getStringExtra("docid");
		String url="http://websensor.playbigdata.com/fss3/docv.aspx?sid="+ URLEncoder.encode(id);
		//http://websensor.playbigdata.com/fss3/docv.aspx?sid=00000000000266239284
		 webView.loadUrl(url);
		 webView.setWebViewClient(new WebViewClient(){
	           public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            // TODO Auto-generated method stub
	               //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
	             view.loadUrl(url);
	            return true;
	        }
	       });
		 WebSettings settings = webView.getSettings();
		 settings.setJavaScriptEnabled(true);
		 webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
	}

}
