package com.laolu.jyzz.client;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
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

	private ProgressDialog pbarDialog;
	
	

	public MyWebClient(ProgressDialog pbarDialog) {
		super();
		this.pbarDialog = pbarDialog;
	}
	
	
	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		super.onPageStarted(view, url, favicon);
		Log.d(LOG_TAG, "PageStarted");
		pbarDialog.show();
	}
	
	@Override
	public void onPageFinished(WebView view, String url) {
		super.onPageFinished(view, url);
		Log.d(LOG_TAG, "PageFinished");
		pbarDialog.dismiss();
		
	}

	public ProgressDialog getPbarDialog() {
		return pbarDialog;
	}

	public void setPbarDialog(ProgressDialog pbarDialog) {
		this.pbarDialog = pbarDialog;
	}
	
	
}
