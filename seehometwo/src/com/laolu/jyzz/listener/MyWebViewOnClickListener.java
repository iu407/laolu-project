package com.laolu.jyzz.listener;

import android.app.ProgressDialog;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;

public class MyWebViewOnClickListener implements OnClickListener {
	private static final String LOG_TAG = "MyOnClickListener";
	private ProgressDialog pbarDialog;
	private String  urlString;
	private WebView mWebView;


	public MyWebViewOnClickListener(ProgressDialog pbarDialog, String urlString,
			WebView webView) {
		super();
		this.pbarDialog = pbarDialog;
		this.urlString = urlString;
		mWebView = webView;
	}


	@Override
	public void onClick(View v) {
		Log.d(LOG_TAG, "onClick");
		if (!pbarDialog.isShowing()) {
			pbarDialog.show();
		}
		new Handler().post(new Runnable() {
			public void run() {
				mWebView.loadUrl(urlString);// 运行js
			}
		});
	}

}
