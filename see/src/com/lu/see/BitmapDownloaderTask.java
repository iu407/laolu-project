package com.lu.see;

import java.io.InputStream;
import java.lang.ref.WeakReference;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class BitmapDownloaderTask extends AsyncTask<String, Void, Bitmap> { 
    private String url; 
    private final WeakReference<ImageView> imageViewReference;  //使用WeakReference解决内存问题
 
    public BitmapDownloaderTask(ImageView imageView) { 
        imageViewReference = new WeakReference<ImageView>(imageView); 
    } 
 
    @Override 
    protected Bitmap doInBackground(String... params) {   //实际的下载线程，内部其实是concurrent线程，所以不会阻塞
   
         return downloadBitmap(params[0]);   

  } 
 
    private Bitmap downloadBitmap(String string) {
    	DefaultHttpClient client = new DefaultHttpClient();
		try {
		
			HttpGet getRequest = new HttpGet(string);
			HttpResponse response = client.execute(getRequest);
			final int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				Log.e("cwjDebug", "Error " + statusCode
						+ " while retrieving bitmap from " + url);
				return null;
			}

			final HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream inputStream = null;
				try {
					inputStream = entity.getContent();
					final Bitmap bitmap = BitmapFactory
							.decodeStream(inputStream);
					return bitmap;
				} finally {
					if (inputStream != null) {
						inputStream.close();
					}
					entity.consumeContent();
				}
			}
		} catch (Exception e) {
//			getRequest.abort();
//			Log.ERROR("android123Debug", "Error while retrieving bitmap from "
//					+ url, e.toString());
		} finally {
			if (client != null) {
//				client.close();
			}
		}
		return null;
	}

	@Override 
     protected void onPostExecute(Bitmap bitmap) {   //下载完后执行的
        if (isCancelled()) { 
            bitmap = null; 
        } 
 
        if (imageViewReference != null) { 
            ImageView imageView = imageViewReference.get(); 
            if (imageView != null) { 
                imageView.setImageBitmap(bitmap);  //下载完设置imageview为刚才下载的bitmap对象
            } 
        } 
    } 
}

