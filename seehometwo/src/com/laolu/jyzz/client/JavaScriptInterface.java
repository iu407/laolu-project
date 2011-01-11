package com.laolu.jyzz.client;

import android.app.ProgressDialog;
import android.os.Handler;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
/**
 * WebChromeClient是辅助WebView处理Javascript的对话框，网站图标，网站title，加载进度等
 * @author laolu
 *
 */
public class JavaScriptInterface {
	private static final String LOG_TAG = "JavaScriptInterface";
	private WebView mWebView;
	
	public JavaScriptInterface( WebView webView) {
		this.mWebView  = webView;
	}
	
	
	/**
	 * 页面使用<a onClick="window.demo.clickOnAndroid()">方式调用
	 */
	public void clickOnAndroid(final String url) {
		Log.d(LOG_TAG, "clickOnAndroid");
		mWebView.loadUrl(url);// 运行js
//		new Handler().post(new Runnable() {
//			public void run() {
//				mWebView.loadUrl(url);// 运行js
//			}
//		});
	}
	

}
