package com.laolu.jyzz.client;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;

import com.laolu.jyzz.activity.MainActivity;
/**
 * WebChromeClient是辅助WebView处理Javascript的对话框，网站图标，网站title，加载进度等
 * @author laolu
 *
 */
public class JavaScriptInterface {
	private static final String LOG_TAG = "JavaScriptInterface";
	private WebView mWebView;
	private MainActivity mainActivity;
	
	
	public JavaScriptInterface(WebView webView,MainActivity mainActivity) {
		this.mWebView  = webView;
		this.mainActivity = mainActivity;
	}


	/**
	 * 页面使用<a onClick="window.demo.clickOnAndroid()">方式调用
	 */
	public void clickOnAndroid(final String url) {
		Log.d(LOG_TAG, "clickOnAndroid");
		
		mWebView.loadUrl(url);// 运行js
	}
	

	public void calltel(final String tel) {
		Log.d(LOG_TAG, "calltel");
		Uri telUri = Uri.parse("tel:"+tel);
		Intent intent = new Intent(Intent.ACTION_DIAL, telUri);
		mainActivity.startActivity(intent);
	}
}
