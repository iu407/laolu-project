package com.laolu.jyzz.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.laolu.jyzz.client.JavaScriptInterface;
import com.laolu.jyzz.client.MyWebChromeClient;
import com.laolu.jyzz.client.MyWebClient;
import com.laolu.jyzz.db.SqlHelper;
import com.laolu.jyzz.listener.MyWebViewOnClickListener;
import com.laolu.jyzz.model.PathModel;
import com.laolu.jyzz.utils.CommonUtil;
public class MainActivity extends Activity {
    private static final String LOG_TAG = "MainMgz";
    private WebView mWebView;//主要的功能都在这里
    private TextView homeTextView;
    private TextView refreshTextView;//刷新
    private TextView preTextView;//返回
    private ProgressDialog pbarDialog;
    private String urlString;
    private String preUrlString;
    private String defaulUrlString = "http://192.168.1.100:8080/ss/fe/pdl";//默认路径
    private String aboutUrlString = "http://192.168.1.100:8080/ss/about";//默认路径
//    private String welcomeUrlString = "http://192.168.1.12:8080/jyzz/adr/img";//默认路径
    private SqlHelper sqlHelper;
    private PathModel pm;
    //private SQLiteDatabase  readableDatabase;
    @Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		Log.d(LOG_TAG, "MainActivity Create");
		setContentView(R.layout.main);// 夹在页面布局
		init();
		showPage();
	}
    /**
     * 初始化页面组件
     */
    private void init() {
    	initdb();
//    	urlString = pm.toString();
    	
    	homeTextView = (TextView) findViewById(R.id.homeTextView);
		refreshTextView = (TextView) findViewById(R.id.refreshTextView);
		preTextView = (TextView) findViewById(R.id.preTextView);//返回
		homeTextView.setBackgroundResource(R.drawable.fav);
		
		mWebView = (WebView) findViewById(R.id.webview);
		mWebView.setWebViewClient(new MyWebClient());
		mWebView.setWebChromeClient(new MyWebChromeClient());
		mWebView.addJavascriptInterface(new JavaScriptInterface(mWebView,this), "demo");
		setPreUrlString(pm.toString());
		setUrlString(pm.toString());
		
		
		
		homeTextView.setOnClickListener(new MyWebViewOnClickListener(getUrlString(),mWebView));
		refreshTextView.setOnClickListener(new MyWebViewOnClickListener(getUrlString(),mWebView));
		preTextView.setOnClickListener(new MyWebViewOnClickListener(getUrlString(),mWebView));
	}
    /**
     * 初始化数据库
     */
    private void initdb(){
		sqlHelper = new SqlHelper(this,CommonUtil.DBNAME,null,1);//得到数据库，同时创建数据库
		SQLiteDatabase readableDatabase =  sqlHelper.getReadableDatabase();//可以读的操作
		Cursor cursor = readableDatabase.query(CommonUtil.T_HOME, null, null, null, null, null, null);
		if (cursor.moveToFirst()) {//如果有数据
			do {
				String ipaddress = cursor.getString(0);
				String port = cursor.getString(1);
				String path = cursor.getString(2);
				System.out.println(ipaddress);
				pm = new PathModel(ipaddress,port,path);
			} while (cursor.moveToNext());
		} else {//
			sqlHelper.onUpgrade(readableDatabase, 1, 1);//删除数据表
			pm = new PathModel();
			ContentValues contentValues = new ContentValues();  
			contentValues.put("ipaddress", pm.getIpaddress());
			contentValues.put("port",      pm.getPort());
			contentValues.put("path",      pm.getPath());
			readableDatabase.insert(CommonUtil.T_HOME, null, contentValues);
		}
		cursor.close();
		readableDatabase.close();
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
//		case R.id.menu_setip:
//			Bundle bundle = new Bundle(); 
//			bundle.putSerializable("pathModel", pm);
//			Intent showNextPageIntent = new Intent(this, SetIpActivity.class);
//			showNextPageIntent.putExtras(bundle);
//            startActivityForResult(showNextPageIntent, CommonUtil.IP_SET_OK);
//			break;
//		case R.id.menu_resetdb://清空数据
//			new AlertDialog.Builder(this)
//			.setIcon(android.R.drawable.btn_star).setTitle("清空数据")
//			.setMessage("清空数据吗？")
//			.setNegativeButton("清空", ocl)
//			.setPositiveButton("返回", ocl)
//			.create().show();
//			break;
			
		case R.id.menu_about://关于
			mWebView.loadUrl(aboutUrlString);
			break;
		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == SetIpActivity.RESULT_SUBMIT){
			Bundle bundle = data.getExtras();
			String ipaddress = bundle.getString("ipstring");//这里的值要保存
			
			pm.setIpaddress(ipaddress);
			
			sqlHelper = new SqlHelper(this,CommonUtil.DBNAME,null,1);//得到数据库，同时创建数据库
			SQLiteDatabase  readableDatabase =  sqlHelper.getReadableDatabase();//可以读的操作
			sqlHelper.onUpgrade(readableDatabase, 1, 1);//删除数据表
			ContentValues contentValues = new ContentValues();  
			contentValues.put("ipaddress", pm.getIpaddress());
			contentValues.put("port",      pm.getPort());
			contentValues.put("path",      pm.getPath());
			readableDatabase.insert(CommonUtil.T_HOME, null, contentValues);
			readableDatabase.close();
			
			setUrlString(pm.toString());
			showPage();
		}
		
//		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
	private void showPage(){
		 mWebView.loadUrl(urlString);
	}
	/**
	 * 返回按钮事件
	 */
	@Override
	public void onBackPressed() {
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
	
	OnClickListener ocl = new OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case Dialog.BUTTON_NEGATIVE:
				Toast.makeText(MainActivity.this, "正在清空数据。",
						Toast.LENGTH_LONG).show();
				SQLiteDatabase readableDatabase =  sqlHelper.getReadableDatabase();//可以读的操作
				sqlHelper.onUpgrade(readableDatabase, 1, 1);
				Toast.makeText(MainActivity.this, "请重新启动",
						Toast.LENGTH_LONG).show();
				break;
			case Dialog.BUTTON_NEUTRAL:
				Toast.makeText(MainActivity.this, "说不上喜欢不喜欢。",
						Toast.LENGTH_LONG).show();
				break;
			case Dialog.BUTTON_POSITIVE:
				Toast.makeText(MainActivity.this, "没有清空数据。",
						Toast.LENGTH_LONG).show();
				break;
			}
		}
	};
}