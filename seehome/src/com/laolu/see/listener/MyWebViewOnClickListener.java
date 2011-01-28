package com.laolu.see.listener;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;

import com.laolu.see.activity.R;

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
//		if(R.id.refreshTextView == v.getId()){//刷新
//			Log.d(LOG_TAG, "refreshTextView Click");
//			String url = mWebView.getUrl();
//			mWebView.loadUrl(url);
//		}
//		if(R.id.preTextView == v.getId()){//刷新
//			if(mWebView.getOriginalUrl() != null)
//			{
//				mWebView.goBack();
//			}
//			
//		}
		
	}

}
