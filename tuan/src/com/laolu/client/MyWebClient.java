package com.laolu.client;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.HttpAuthHandler;
import android.webkit.WebSettings;
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

//	private ProgressDialog progressDialog;//这里是否可以调整为
	public MyWebClient() {
		super();
	}
	

	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		super.onPageStarted(view, url, favicon);
		Log.d(LOG_TAG, "PageStarted");
		
//		progressDialog = new ProgressDialog( view.getContext() );//首先得到环境
//		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//		progressDialog.setMessage("请稍等...");
//		progressDialog.setCancelable(true);
//		progressDialog.setIndeterminate(true);
//		progressDialog.show();
	}
	
	@Override
	public void onPageFinished(WebView view, String url) {
		view.getSettings().setBlockNetworkImage(false);//阻止图片
		Log.d(LOG_TAG, "PageFinished");
//		view.pageDown(true);
//		progressDialog.cancel();
	}
	
	@Override
	public void onLoadResource(WebView view, String url) {
//		super.onLoadResource(view, url);
//		pbarDialog.incrementProgressBy(view.getProgress());//过程
		Log.d(LOG_TAG, "onLoadResource");
//		if(!progressDialog.isShowing()){
//			progressDialog.show();//显示但是没有动起来
//		}
	}
	
	@Override
	public void onScaleChanged(WebView view, float oldScale, float newScale) {
		super.onScaleChanged(view, oldScale, newScale);
		Log.d(LOG_TAG, "onScaleChanged");
	}
	@Override
	public void onReceivedError(WebView view, int errorCode,
			String description, String failingUrl) {
//		super.onReceivedError(view, errorCode, description, failingUrl);
		Log.d(LOG_TAG, "onReceivedError");
//		progressDialog.cancel();
		view.loadUrl("file:///android_asset/failure.html");
//		view.loadData(description, "text/html", "UTF-8");
	}
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
// 记得消耗掉这个事件。给不知道的朋友再解释一下，Android中返回True的意思就是到此为止吧,事件就会不会冒泡传递了，我们称之为消耗掉
		view.loadUrl(url);   
        return true;   

	}
	
	
	@Override
	public void onReceivedHttpAuthRequest(WebView view,
			HttpAuthHandler handler, String host, String realm) {
		super.onReceivedHttpAuthRequest(view, handler, host, realm);
		Log.d(LOG_TAG, "onReceivedHttpAuthRequest " + realm);
	}
	
//
//	public ProgressDialog getPbarDialog() {
//		return progressDialog;
//	}
//
//	public void setPbarDialog(ProgressDialog pbarDialog) {
//		this.progressDialog = pbarDialog;
//	}
	
	
}
