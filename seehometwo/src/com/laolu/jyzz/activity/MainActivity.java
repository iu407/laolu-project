package com.laolu.jyzz.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.laolu.jyzz.client.JavaScriptInterface;
import com.laolu.jyzz.client.MyWebChromeClient;
import com.laolu.jyzz.client.MyWebClient;
import com.laolu.jyzz.listener.MyWebViewOnClickListener;
public class MainActivity extends Activity {
    private static final String LOG_TAG = "MainMgz";
    private WebView mWebView;//主要的功能都在这里
    private TextView homeTextView;
    private ProgressDialog pbarDialog;
    private String urlString;
    private String preUrlString;
    private String welcomeUrlString = "http://192.168.1.12:8080/jyzz/adr/img";

    @Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		Log.d(LOG_TAG, "Create");
		setContentView(R.layout.main);// 页面的view
		init();
		showPage();
	}
    /**
     * 初始化页面组件
     */
    private void init() {
		mWebView = (WebView) findViewById(R.id.webview);
		WebSettings webSettings = mWebView.getSettings();
		webSettings.setSavePassword(false);
		webSettings.setSaveFormData(false);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(false);
		mWebView.setWebViewClient(new MyWebClient());
		mWebView.setWebChromeClient(new MyWebChromeClient());
		mWebView.addJavascriptInterface(new JavaScriptInterface(mWebView), "demo");
		setPreUrlString(welcomeUrlString);
		setUrlString(welcomeUrlString);
		
		homeTextView = (TextView) findViewById(R.id.homeTextView);
		homeTextView.setOnClickListener(new MyWebViewOnClickListener(urlString,mWebView));
	}
    
    /**
	 * 创建菜单
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater=getMenuInflater();//增压泵
		inflater.inflate(R.menu.menu, menu);//指定使用的XML.增加个一个文件夹menu
		return true;
	}
	
	private void showPage(){
		 mWebView.loadUrl(urlString);
	}
	/**
	 * 返回按钮事件
	 */
	@Override
	public void onBackPressed() {
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
	public String getWelcomeUrlString() {
		return welcomeUrlString;
	}
	public void setWelcomeUrlString(String welcomeUrlString) {
		this.welcomeUrlString = welcomeUrlString;
	}

	public ProgressDialog getPbarDialog() {
		return pbarDialog;
	}
	public void setPbarDialog(ProgressDialog pbarDialog) {
		this.pbarDialog = pbarDialog;
	} 
}