package com.laolu.jyzz.webview;



import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
public class MainMgz extends Activity {

    private static final String LOG_TAG = "WebViewDemo";

    private WebView mWebView;
    private Button preBtn;
    private ProgressDialog pbarDialog;
    private String urlString;
    private String preUrlString;
    private String welcomeUrlString = "http://192.168.1.12:8080/jyzz/adr/l";

    private Handler mHandler = new Handler(){
    	@Override
    	public void handleMessage(Message msg) {//处理消息
    		loadUrl();
    	}
    	
    };

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
        init();
        
        showProgress();
		showPage();
       
    }
    private void init() {
		mWebView = (WebView) findViewById(R.id.webview);
		WebSettings webSettings = mWebView.getSettings();
		webSettings.setSavePassword(false);
		webSettings.setSaveFormData(false);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(false);
		mWebView.setWebChromeClient(new MyWebChromeClient());
		mWebView.addJavascriptInterface(new MyJavaScriptInterface(), "demo");// demo叫做接口名
//		mWebView.loadUrl(welcomeUrlString);// 类似于主页的
		
		setPreUrlString(welcomeUrlString);
		setUrlString(welcomeUrlString);
		preBtn = (Button) findViewById(R.id.preBtn);//上一页
		preBtn.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				setUrlString(preUrlString);
				showProgress();
				showPage();
			}
		});
		
		pbarDialog = new ProgressDialog( MainMgz.this );//首先得到环境
		pbarDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pbarDialog.setMessage("请稍等");
		pbarDialog.setMax(100);
		pbarDialog.setProgress(0);
		pbarDialog.setCancelable(true);
		pbarDialog.setIndeterminate(true);
		
		
	}

    final class MyJavaScriptInterface {

        MyJavaScriptInterface() {
        }

        /**
         * This is not called on the UI thread. Post a runnable to invoke
         * loadUrl on the UI thread.
         */
        public void clickOnAndroid(final String url) {
        	setUrlString(url);
        	showProgress();
        	showPage();
//            mHandler.post(new Runnable() {
//                public void run() {
//                	System.out.println(url);//这里可以增加些东西。例如progressdialog
//                    mWebView.loadUrl(url);//运行js
//                }
//            });

        }
    }

    /**
     * Provides a hook for calling "alert" from javascript. Useful for
     * debugging your javascript.
     */
    final class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.d(LOG_TAG, message);
            result.confirm();
            return true;
        }
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			progressHandler.sendMessage(progressHandler.obtainMessage()); 

			if(newProgress == 100){
				pbarDialog.dismiss();
			}
			super.onProgressChanged(view, newProgress);
		}
    }
    
	
	private void showProgress(){
		pbarDialog.show();
	}
	
	private void showPage(){
//		new Thread(){   
//            @Override  
//            public void run() {   
//            	mHandler.sendEmptyMessage(0);   
//            }}.start();   
		mHandler.sendEmptyMessage(0);
	}
	
	private void loadUrl(){
		  mHandler.post(new Runnable() {
              public void run() {
              	System.out.println(urlString);//这里可以增加些东西。例如progressdialog
                  mWebView.loadUrl(urlString);//运行js
              }
          });

	}

	public String getUrlString() {
		return urlString;
	}

	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}
	public String getPreUrlString() {
		return preUrlString;
	}
	public void setPreUrlString(String preUrlString) {
		this.preUrlString = preUrlString;
	}
	public String getWelcomeUrlString() {
		return welcomeUrlString;
	}
	public void setWelcomeUrlString(String welcomeUrlString) {
		this.welcomeUrlString = welcomeUrlString;
	}
	Handler progressHandler = new Handler() {
		public void handleMessage(Message msg) {            
			pbarDialog.incrementProgressBy(50);         
		}     
	}; 
}