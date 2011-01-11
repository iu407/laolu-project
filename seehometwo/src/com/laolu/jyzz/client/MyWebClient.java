package com.laolu.jyzz.client;

import com.laolu.jyzz.activity.MainActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
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

	public MyWebClient() {
		super();
	}
	
	
	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		super.onPageStarted(view, url, favicon);
		Log.d(LOG_TAG, "PageStarted");
		
		pbarDialog = new ProgressDialog( view.getContext() );//首先得到环境
		pbarDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pbarDialog.setMessage("请稍等...");
		pbarDialog.setCancelable(true);
		pbarDialog.setIndeterminate(true);
		pbarDialog.show();
	}
	
	@Override
	public void onPageFinished(WebView view, String url) {
		super.onPageFinished(view, url);
		Log.d(LOG_TAG, "PageFinished");
		pbarDialog.cancel();
		
	}
	
	@Override
	public void onLoadResource(WebView view, String url) {
		super.onLoadResource(view, url);
//		pbarDialog.incrementProgressBy(view.getProgress());//过程
		Log.d(LOG_TAG, "onLoadResource");
		if(!pbarDialog.isShowing()){
			pbarDialog.show();//显示但是没有动起来
		}
	}
	
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
// 记得消耗掉这个事件。给不知道的朋友再解释一下，Android中返回True的意思就是到此为止吧,事件就会不会冒泡传递了，我们称之为消耗掉
		return true;
	}
	

	public ProgressDialog getPbarDialog() {
		return pbarDialog;
	}

	public void setPbarDialog(ProgressDialog pbarDialog) {
		this.pbarDialog = pbarDialog;
	}
	
	
}
