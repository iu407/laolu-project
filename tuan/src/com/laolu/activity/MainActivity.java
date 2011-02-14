package com.laolu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.laolu.client.JavaScriptInterface;
import com.laolu.client.MyWebChromeClient;
import com.laolu.client.MyWebClient;
import com.laolu.listener.MyWebViewOnClickListener;
public class MainActivity extends Activity {
    private static final String LOG_TAG = "MainMgz";
    private WebView mWebView = null;//主要的功能都在这里
    private WebSettings webSettings =null;
    private TextView homeTextView;
    private TextView refreshTextView;//刷新
    private TextView preTextView;//返回
    private String preUrlString;
    private String urlString = "http://114.80.218.74:8080/ss/fe/pdl";//默认路径
    private String aboutUrlString  = "file:///android_asset/about.html";//关于路径
    @Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		Log.d(LOG_TAG, "MainActivity Create");
		getWindow().requestFeature(Window.FEATURE_PROGRESS);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		init();
		showPage();
	}
    /**
     * 初始化页面组件
     */
    private void init() {
    	setContentView(R.layout.main);//页面布局使用的xml
		mWebView = (WebView) findViewById(R.id.webview);
		mWebView.setBackgroundResource(R.drawable.defautbg);
		webSettings = mWebView.getSettings();
		webSettings.setBlockNetworkImage(false);//阻止图片
		webSettings.setJavaScriptEnabled(true);//支持enable
		
		mWebView.setWebViewClient(new MyWebClient());
		mWebView.setWebChromeClient(new MyWebChromeClient(this));
		mWebView.addJavascriptInterface(new JavaScriptInterface(mWebView,this), "demo");
		
		homeTextView = (TextView) findViewById(R.id.homeTextView);
		refreshTextView = (TextView) findViewById(R.id.refreshTextView);
		preTextView = (TextView) findViewById(R.id.preTextView);//返回
		homeTextView.setOnClickListener(new MyWebViewOnClickListener(getUrlString(),mWebView));
		refreshTextView.setOnClickListener(new MyWebViewOnClickListener(getUrlString(),mWebView));
		preTextView.setOnClickListener(new MyWebViewOnClickListener(getUrlString(),mWebView));
	}
    /**
	 * 创建菜单
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater=getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_about:
			mWebView.loadUrl(aboutUrlString);
			break;
		case R.id.menu_exit:
			finish();
			break;
		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	
	
	private void showPage(){
		 mWebView.loadUrl(urlString);
	}
	/**
	 * 返回按钮事件
	 */
	@Override
	public void onBackPressed() {
		if(mWebView.canGoBack()){   
			mWebView.goBack();
            return;   
		  }
		super.onBackPressed();   

	}
	public String getUrlString() {
		return urlString;
	}

	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}
	public String getPreUrlString() {
		return preUrlString;
	}
	public void setPreUrlString(String preUrlString) {
		this.preUrlString = preUrlString;
	}
}