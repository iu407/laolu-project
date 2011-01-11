package com.laolu.jyzz.listener;

import com.laolu.jyzz.activity.R;

import android.app.ProgressDialog;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;

public class MyWebViewOnClickListener implements OnClickListener {
	private static final String LOG_TAG = "MyOnClickListener";
	private String  urlString;
	private WebView mWebView;


	public MyWebViewOnClickListener( String urlString,
			WebView webView) {
		super();
		this.urlString = urlString;
		this.mWebView = webView;
	}


	@Override
	public void onClick(View v) {
		if(R.id.homeTextView == v.getId()){
			Log.d(LOG_TAG, "homeTextView Click");
			mWebView.loadUrl(urlString);
		}
		
	}

}
