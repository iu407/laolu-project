package com.lu.see;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Main extends Activity {
	private ImageView picImageView;
	private String urlString ;
	private Button nBut;//next button
	private Toast toast;
	private ProgressDialog pbarDialog;
	
	private String dftBtnText = "远程照相";
	private String runBtnText = "正在拍照";
	
	private static final int STOP = 0x108;
	private static final int NEXT = 0x109;
	private int iCount = 0;
	public static final int IP_SET_OK = 0; 
	private Bitmap bm = null;
	private String dbName = "seedb.db";
	private SqlHelper sqlHelper;
	private SQLiteDatabase sql;
	private PathModel pm;
	/**
	 * 初始化数据库
	 */
	private void initdb(){
		sqlHelper = new SqlHelper(this,dbName,null,1);//得到数据库，同时创建数据库
		SQLiteDatabase  readableDatabase =  sqlHelper.getReadableDatabase();//可以读的操作
		Cursor cursor = readableDatabase.query(SqlHelper.tableName, null, null, null, null, null, null);
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
			readableDatabase.insert(SqlHelper.tableName, null, contentValues);

		}
		cursor.close();
		readableDatabase.close();
	}
	
	private void init(){
		initdb();
		urlString = pm.toString();
		picImageView = (ImageView)findViewById(R.id.urlimage); 
		nBut=(Button)findViewById(R.id.okButton);
		nBut.setText(dftBtnText);
		nBut.setOnClickListener( new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if(bm!=null){
					if(bm.isRecycled()==false) bm.recycle();
				}
				showProgress();
				showImg();
			}
			}
		);
	}
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main); 
		init();
		
		showProgress();
		showImg();
		
	}
	/**
	 * 创建菜单
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater=getMenuInflater();//增压泵
		inflater.inflate(R.menu.menu, menu);//指定使用的XML
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int item_id = item.getItemId();// 得到当前选中MenuItem的ID
		switch (item_id) {
			case R.id.menu_setip: {
				Bundle bundle = new Bundle(); 
				bundle.putSerializable("pathModel", pm);
				Intent showNextPageIntent = new Intent(Main.this, SetIpActivity.class);
				showNextPageIntent.putExtras(bundle);
	            startActivityForResult(showNextPageIntent, IP_SET_OK); 
				break;
			}
		}
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == SetIpActivity.RESULT_SUBMIT){
			Bundle bundle = data.getExtras();
			String ipaddress = bundle.getString("ipstring");//这里的值要保存
			
			pm.setIpaddress(ipaddress);
			
			sqlHelper = new SqlHelper(this,dbName,null,1);//得到数据库，同时创建数据库
			SQLiteDatabase  readableDatabase =  sqlHelper.getReadableDatabase();//可以读的操作
			sqlHelper.onUpgrade(readableDatabase, 1, 1);//删除数据表
			ContentValues contentValues = new ContentValues();  
			contentValues.put("ipaddress", pm.getIpaddress());
			contentValues.put("port",      pm.getPort());
			contentValues.put("path",      pm.getPath());
			readableDatabase.insert(SqlHelper.tableName, null, contentValues);
			readableDatabase.close();
			
			urlString = pm.toString();
			showProgress();
			showImg();
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	//得到图片
	public Bitmap getHttpBitmap(String url) throws IOException {
		URL myFileUrl = null;
		bm = null;
		HttpURLConnection conn = null;
		InputStream is = null;
		try {
			myFileUrl = new URL(url);
			conn = (HttpURLConnection) myFileUrl.openConnection();
			conn.setDoInput(true);
			conn.setConnectTimeout(5000);//链接超时
			conn.connect();
			is = conn.getInputStream();
			bm = BitmapFactory.decodeStream(is);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			showErrMsg("连接超时！");
		} catch (IOException e) {
			e.printStackTrace();
			pbarDialog.dismiss();
			showErrMsg("连接超时！");
		} finally{
			is.close();
			conn.disconnect();
		}

		return bm;
	}

	
	 private Handler handler = new Handler(){   
	        @Override  
	        public void handleMessage(Message msg) {   
	        	try {
					getHttpBitmap(pm.toString());
				} catch (Exception e) {
					e.printStackTrace();
					showErrMsg("不能得到照片!");
				}
				picImageView.setImageBitmap(bm);
				
				pbarDialog.dismiss();
				nBut.setText(dftBtnText);
	               
	            //更新UI   
	        }};   

	
	private void showErrMsg(String msg){
		toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		LinearLayout toastView = (LinearLayout) toast.getView();
		ImageView imageCodeProject = new ImageView(getApplicationContext());
//		imageCodeProject.setImageResource(R.drawable.loadinfo);
		toastView.addView(imageCodeProject, 0);
		toast.show();
	}
	
	private void showProgress(){
		pbarDialog = new ProgressDialog( Main.this );//首先得到环境
		pbarDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pbarDialog.setMessage("Loading...");
		pbarDialog.setCancelable(false);
		pbarDialog.setIndeterminate(false);
		pbarDialog.show();
	}
		
	private void showImg(){
		new Thread(){   
            @Override  
            public void run() {   
                //这里增加方法
//              Calculation.calculate(4);   
            	handler.sendEmptyMessage(0);   
            }}.start();   

	}
}
