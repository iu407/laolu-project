package com.lu.see;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
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
	
	private String ipstring = "192.168.1.1";
	private String port = "8080";
	private String path = "jmf/show";
	private Bitmap bm = null;
	
	private String dbName = "seedb.db";
	
	private SqlHelper sqlHelper;
	private SQLiteDatabase sql;
	
	/**
	 * 初始化数据库
	 */
	private void initdb(){
		sqlHelper = new SqlHelper(this,dbName,null,1);//得到数据库，同时创建数据库
//		SQLiteDatabase  writableDatabase =  sqlHelper.getWritableDatabase();//可以写的操作
		SQLiteDatabase  readableDatabase =  sqlHelper.getReadableDatabase();//可以读的操作
		Cursor cursor =  readableDatabase.query(SqlHelper.tableName, null, null, null, null, null, null);
		for(int i = 0 ; i <cursor.getCount(); i ++)
			{
				cursor.move(i);
				String ipstr = cursor.getString(0);
				System.out.println(ipstr);
			}
		
		cursor.close();
		readableDatabase.close();
	}
	
	private void init(){
		initdb();
		picImageView = (ImageView)findViewById(R.id.urlimage); 
		urlString = "http://localhost:8080/jyzz/jmf/show";//初始化路径
		nBut=(Button)findViewById(R.id.okButton);
		nBut.setText(dftBtnText);
		nBut.setOnClickListener( new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if(bm!=null){
					if(bm.isRecycled()==false) bm.recycle();
				}
				showProgress();
				showImg1();
			}
			}
		);
	}
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main); 
		init();
//		picImageView = (ImageView)findViewById(R.id.urlimage); 
//		urlString = "http://localhost:8080/jyzz/jmf/show";//这个是默认的路径.路径需要配置
//		Bitmap bm = getHttpBitmap(urlString);
//		picImageView.setImageBitmap(bm);
		
		
	
	}
	/**
	 * 创建菜单
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater=getMenuInflater();
		inflater.inflate(R.menu.menu, menu);//指定使用的XML
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int item_id = item.getItemId();// 得到当前选中MenuItem的ID
		switch (item_id) {
			case R.id.menu_setip: {
				Intent showNextPageIntent = new Intent(Main.this, SetIpActivity.class);
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
			String ipstring = bundle.getString("ipstring");//这里的值要保存
			urlString = "http://"+ ipstring +":"+ port +"/jyzz/jmf/show";
			
			showProgress();
			showImg1();
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


	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch(msg.what){
			case STOP://到了最大值
				try {
					getHttpBitmap(urlString);
				} catch (Exception e) {
					e.printStackTrace();
					showErrMsg("不能得到照片!");
				}
				picImageView.setImageBitmap(bm);
				
				pbarDialog.dismiss();
				nBut.setText(dftBtnText);
				
				Thread.currentThread().interrupt();//中断当前线程.
				break;
			case NEXT:
				if(!Thread.currentThread().isInterrupted()){//当前线程正在运行
					pbarDialog.setProgress(iCount);
				}
				break;
			}
			super.handleMessage(msg);
		}
	};
	
	 private Handler handler = new Handler(){   
	        @Override  
	        public void handleMessage(Message msg) {   
	        	try {
					getHttpBitmap(urlString);
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
		imageCodeProject.setImageResource(R.drawable.loadinfo);
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
		Thread mThread = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0 ; i < 10 ; i++)
				{
					try {
						iCount = (i + 1) * 20;
						Thread.sleep(300);
						Message msg = new Message();
						if (i == 4) {
							msg.what = Main.this.STOP;
							mHandler.sendMessage(msg);
							break;
						} else// i<4
						{
							msg.what = Main.this.NEXT;
							mHandler.sendMessage(msg);
						}

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}  
		});
		mThread.start();
	}
	
	private void showImg1(){
		new Thread(){   
			  
            @Override  
            public void run() {   
                //需要花时间计算的方法   
//                Calculation.calculate(4);   
                   
                //向handler发消息   
            	handler.sendEmptyMessage(0);   
            }}.start();   

	}
	
	
}
/*
toast.show();
new AlertDialog.Builder(getApplicationContext()).setTitle("Android 提示").setMessage("这是一个提示,请确定").show(); 
Bitmap bm = getHttpBitmap(urlString);
picImageView.setImageBitmap(bm);
*/