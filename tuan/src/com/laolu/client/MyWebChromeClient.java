package com.laolu.client;

import android.app.Activity;
import android.util.Log;
import android.view.Window;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
/**
 * WebChromeClient是辅助WebView处理Javascript的对话框，网站图标，网站title，加载进度等
 * @author laolu
 *
 */
public class MyWebChromeClient extends WebChromeClient {
	private static final String LOG_TAG = "MyWebChromeClient";
	
	private Activity activity;
	public MyWebChromeClient(Activity activity) {
		super();
		this.activity = activity;
	}
	@Override
	public boolean onJsAlert(WebView view, String url, String message,JsResult result) {
		Log.d(LOG_TAG, message);
		result.confirm();
		return true;
	}

	@Override
	public void onProgressChanged(WebView view, int newProgress) {
		activity.setProgress(newProgress * 100);
		Log.d(LOG_TAG, "onProgressChanged");
		
	}

}
