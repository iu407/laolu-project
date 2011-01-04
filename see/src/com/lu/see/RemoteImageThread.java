package com.lu.see;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class RemoteImageThread implements Runnable {
	private ImageView picImageView;
	private ProgressDialog pbarDialog;
	private String urlString = "http://192.168.1.12:8080/jyzz/jmf/show";
	public RemoteImageThread(ImageView picImageView, ProgressDialog apbarDialog) {
		super();
		this.picImageView = picImageView;
		this.pbarDialog = apbarDialog;
		
		pbarDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pbarDialog.setMessage("Loading...");
		pbarDialog.setCancelable(true);
		pbarDialog.setIndeterminate(false);
		pbarDialog.show();
		
	}
	
	
	@Override
	public void run() {
		if( pbarDialog.isShowing() ){
			Bitmap bm = getHttpBitmap(urlString);
			picImageView.setImageBitmap(bm);
			pbarDialog.dismiss();
		}
	}

	public Bitmap getHttpBitmap(String url) {
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
}
