package com.laolu.client;


import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * WebViewClient就是帮助WebView处理各种通知、请求事件的，
 * 
 * @author laolu
 * 
 */
public class MyWebClient extends WebViewClient {
	private static final String LOG_TAG = "MyWebClient";

	public MyWebClient() {
		super();
	}
	

	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		super.onPageStarted(view, url, favicon);
		Log.d(LOG_TAG, "PageStarted");
		
	}
	
	@Override
	public void onPageFinished(WebView view, String url) {
		super.onPageFinished(view,url);
		Log.d(LOG_TAG, "PageFinished");
	}
	
	@Override
	public void onLoadResource(WebView view, String url) {
		super.onLoadResource(view, url);
		Log.d(LOG_TAG, "onLoadResource");
	}
	
	@Override
	public void onScaleChanged(WebView view, float oldScale, float newScale) {
		super.onScaleChanged(view, oldScale, newScale);
		Log.d(LOG_TAG, "onScaleChanged");
	}
	@Override
	public void onReceivedError(WebView view, int errorCode,
			String description, String failingUrl) {
		Log.d(LOG_TAG, "onReceivedError");
		view.loadUrl("file:///android_asset/failure.html");
	}
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		view.loadUrl(url);   
        return true;   

	}
	
	
	@Override
	public void onReceivedHttpAuthRequest(WebView view,
			HttpAuthHandler handler, String host, String realm) {
		super.onReceivedHttpAuthRequest(view, handler, host, realm);
		Log.d(LOG_TAG, "onReceivedHttpAuthRequest " + realm);
	}
	
	
}
