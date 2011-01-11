package com.laolu.jyzz.client;

import android.app.ProgressDialog;
import android.util.Log;
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
	
	
	public MyWebChromeClient() {
		super();
	}
	@Override
	public boolean onJsAlert(WebView view, String url, String message,JsResult result) {
		Log.d(LOG_TAG, message);
		result.confirm();
		return true;
	}
	//这里处理view
	@Override
	public void onProgressChanged(WebView view, int newProgress) {
		super.onProgressChanged(view, newProgress);
//		pbarDialog.incrementProgressBy(newProgress);
		Log.d(LOG_TAG, "onProgressChanged");
	}

}
