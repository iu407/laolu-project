package com.lu.see;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

public class CopyOfMain extends Activity {
	private ImageView picImageView;
	private String urlString ;
	private Button nBut;//next button
	private ProgressBar circleProgressBar;
	private Toast toast;
	
	private ProgressDialog progressDialog;
	
	
	private String dftBtnText = "远程照相";
	private String runBtnText = "正在拍照";
	
	protected static final int STOP = 0x108;
	protected static final int NEXT = 0x109;
	private int iCount = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main); 
		picImageView = (ImageView)findViewById(R.id.urlimage); 
		
		circleProgressBar = (ProgressBar)findViewById(R.id.circleProgressBar);
		circleProgressBar.setIndeterminate(false);   

		urlString = "http://192.168.1.12:8080/jyzz/jmf/show";//这个是默认的路径.路径需要配置
//		Bitmap bm = getHttpBitmap(urlString);
//		picImageView.setImageBitmap(bm);
		
		//这段是布局
		toast = Toast.makeText(getApplicationContext(), "正在远程拍照请稍等", Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		LinearLayout toastView = (LinearLayout) toast.getView();
		ImageView imageCodeProject = new ImageView(getApplicationContext());
		imageCodeProject.setImageResource(R.drawable.loadinfo);
		toastView.addView(imageCodeProject, 0);
		
		nBut=(Button)findViewById(R.id.okButton);
		nBut.setText(dftBtnText);
		nBut.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				circleProgressBar.setVisibility(View.VISIBLE);
				nBut.setText(runBtnText);
				Thread mThread = new Thread(new Runnable() {
					@Override
					public void run() {
						for(int i = 0 ; i < 10 ; i++)
						{
							try {
								iCount = (i + 1) * 20;
								Thread.sleep(500);
								Message msg = new Message();
								
								if (i == 4) {
									msg.what = CopyOfMain.this.STOP;
									mHandler.sendMessage(msg);
									break;
								} else// i<4
								{
									msg.what = CopyOfMain.this.NEXT;
									mHandler.sendMessage(msg);
								}

							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}  
				});
				mThread.start();
			}}
		);
	}

	public static Bitmap getHttpBitmap(String url) {
		URL myFileUrl = null;
		Bitmap bitmap = null;
		try {
			myFileUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) myFileUrl
					.openConnection();
			conn.setConnectTimeout(0);
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}


	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch(msg.what){
			case STOP://到了最大值
				Bitmap bm = getHttpBitmap(urlString);
				picImageView.setImageBitmap(bm);
				circleProgressBar.setVisibility(View.GONE);
				nBut.setText(dftBtnText);
				Thread.currentThread().interrupt();//中断当前线程.
				break;
			case NEXT:
				if(!Thread.currentThread().isInterrupted()){//当前线程正在运行
					circleProgressBar.setProgress(iCount);
					CopyOfMain.this.setProgress(iCount*100);
				}
				break;
			}
			super.handleMessage(msg);
		}
	};
}
/*
toast.show();
new AlertDialog.Builder(getApplicationContext()).setTitle("Android 提示").setMessage("这是一个提示,请确定").show(); 
Bitmap bm = getHttpBitmap(urlString);
picImageView.setImageBitmap(bm);
*/