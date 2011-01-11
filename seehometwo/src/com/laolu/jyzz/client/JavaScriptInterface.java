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
	private ProgressDialog pbarDialog;
	private WebView mWebView;
	
	public JavaScriptInterface(ProgressDialog pbarDialog, WebView webView) {
		this.pbarDialog = pbarDialog;
		this.mWebView = webView;
	}
	
	
	/**
	 * 页面使用<a onClick="window.demo.clickOnAndroid()">方式调用
	 */
	public void clickOnAndroid(final String url) {
		Log.d(LOG_TAG, "clickOnAndroid");
		if (!pbarDialog.isShowing()) {
			pbarDialog.show();
		}
		new Handler().post(new Runnable() {
			public void run() {
				mWebView.loadUrl(url);// 运行js
			}
		});
	}
	

}
