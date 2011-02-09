package com.laolu.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebView.PictureListener;
import android.widget.TextView;

import com.laolu.client.JavaScriptInterface;
import com.laolu.client.MyWebChromeClient;
import com.laolu.client.MyWebClient;
import com.laolu.listener.MyWebViewOnClickListener;
import com.laolu.utils.CommonUtil;
public class MainActivity extends Activity {
    private static final String LOG_TAG = "MainMgz";
    private WebView mWebView = null;//主要的功能都在这里
    private WebSettings webSettings =null;
    private TextView homeTextView;
    private TextView refreshTextView;//刷新
    private TextView preTextView;//返回
    private ProgressDialog pbarDialog;
    private String urlString;
    private String preUrlString;
    private String defaulUrlString = CommonUtil.CITYLIST;//默认路径
//    private String defaulUrlString = "http://3g.sina.com.cn";//默认路径
    private String aboutUrlString  = "file:///android_asset/about.html";//关于路径
    private Bitmap bitmap = null;
    private Canvas canvas = null;
    @Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		Log.d(LOG_TAG, "MainActivity Create");
//		If I use "myProgressBar.setProgressDrawable(R.drawable.my_progress);" it says: 
		getWindow().requestFeature(Window.FEATURE_PROGRESS);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.main);// 夹在页面布局
		init();
		showPage();
	}
    
    @Override
    protected void onStart() {
    	super.onStart();
    }
    /**
     * 初始化页面组件
     */
    private void init() {
//    	initdb();
//    	urlString = pm.toString();
    	

    	homeTextView = (TextView) findViewById(R.id.homeTextView);
		refreshTextView = (TextView) findViewById(R.id.refreshTextView);
		preTextView = (TextView) findViewById(R.id.preTextView);//返回
		
		mWebView = (WebView) findViewById(R.id.webview);
		mWebView.setBackgroundResource(R.drawable.defautbg);
		webSettings = mWebView.getSettings();
//		webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
//		webSettings.setSupportMultipleWindows(false);//多窗口是否阻止
		webSettings.setBlockNetworkImage(true);//阻止图片
//		webSettings.setSavePassword(false);
//		webSettings.setSaveFormData(false);
		webSettings.setJavaScriptEnabled(true);//支持enable
		webSettings.setAppCacheEnabled(true);
		webSettings.setAppCacheMaxSize(20*1024*1024);//20MB
		webSettings.setAppCachePath("/flash/");
		webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

//		webSettings.setSupportZoom(false);
//		mWebView.setBackgroundColor(0);
		
		mWebView.setWebViewClient(new MyWebClient());
		mWebView.setWebChromeClient(new MyWebChromeClient(this));
		mWebView.addJavascriptInterface(new JavaScriptInterface(mWebView,this), "demo");

//		mWebView.setPictureListener(new PictureListener(){
//			@Override
//			public void onNewPicture(WebView view, Picture picture) {
//				bitmap = Bitmap.createBitmap(
//												picture.getWidth(), 
//												picture.getHeight(), 
//												Bitmap.Config.RGB_565);
//				canvas = new Canvas(bitmap);             
//				picture.draw(canvas);
//				bitmap=null;
//			}
//			
//		});
		setPreUrlString(defaulUrlString);
		setUrlString(defaulUrlString);
		
		
		homeTextView.setOnClickListener(new MyWebViewOnClickListener(getUrlString(),mWebView));
		refreshTextView.setOnClickListener(new MyWebViewOnClickListener(getUrlString(),mWebView));
		preTextView.setOnClickListener(new MyWebViewOnClickListener(getUrlString(),mWebView));
	}
    /**
	 * 创建菜单
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater=getMenuInflater();//增压泵
		inflater.inflate(R.menu.menu, menu);//指定使用的XML.增加个一个文件夹menu
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_about:
			mWebView.loadUrl(aboutUrlString);
			break;
		case R.id.menu_exit:
			finish();
			break;
		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	
	
	private void showPage(){
		 mWebView.loadUrl(urlString);
	}
	/**
	 * 返回按钮事件
	 */
	@Override
	public void onBackPressed() {
		if(mWebView.canGoBack()){   
			mWebView.goBack();
            return;   
		  }else{
		  }
		super.onBackPressed();   

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
		return defaulUrlString;
	}
	public void setWelcomeUrlString(String welcomeUrlString) {
		this.defaulUrlString = welcomeUrlString;
	}

	public ProgressDialog getPbarDialog() {
		return pbarDialog;
	}
	public void setPbarDialog(ProgressDialog pbarDialog) {
		this.pbarDialog = pbarDialog;
	}
	
	@Override
	public Window getWindow() {
		return super.getWindow();
	}
	
//	OnClickListener ocl = new OnClickListener() {
//		@Override
//		public void onClick(DialogInterface dialog, int which) {
//			switch (which) {
//			case Dialog.BUTTON_NEGATIVE:
//				Toast.makeText(MainActivity.this, "正在清空数据。",
//						Toast.LENGTH_LONG).show();
//				SQLiteDatabase readableDatabase =  sqlHelper.getReadableDatabase();//可以读的操作
//				sqlHelper.onUpgrade(readableDatabase, 1, 1);
//				Toast.makeText(MainActivity.this, "请重新启动",
//						Toast.LENGTH_LONG).show();
//				break;
//			case Dialog.BUTTON_NEUTRAL:
//				Toast.makeText(MainActivity.this, "说不上喜欢不喜欢。",
//						Toast.LENGTH_LONG).show();
//				break;
//			case Dialog.BUTTON_POSITIVE:
//				Toast.makeText(MainActivity.this, "没有清空数据。",
//						Toast.LENGTH_LONG).show();
//				break;
//			}
//		}
//	};
}