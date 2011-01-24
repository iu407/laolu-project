package com.yourname.main;



import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.ImageView;

public class SplashScreen extends Activity {
	
    /**
     * The thread to process splash screen events
     */
    private Thread mSplashThread;
    private SplashScreen sPlashScreen;
	protected int ACTIVE = 0x109;
	private final int SPLASH_DISPLAY_LENGHT = 3000; //延迟三秒  
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    // Splash screen view
    	setContentView(R.layout.splash);
    	
        // Start animating the image
	    final ImageView splashImageView = (ImageView) findViewById(R.id.SplashImageView);
	    splashImageView.setBackgroundResource(R.drawable.flag);
	    final AnimationDrawable frameAnimation = (AnimationDrawable)splashImageView.getBackground();
	    splashImageView.post(new Runnable(){
			@Override
			public void run() {
				frameAnimation.start();				
			}	    	
	    });
    	sPlashScreen = this;
    	
    	new Handler().postDelayed(new Runnable(){ 
    		  
            @Override 
            public void run() { 
                Intent mainIntent = new Intent(SplashScreen.this,MainActivity.class); 
                SplashScreen.this.startActivity(mainIntent); 
                SplashScreen.this.finish(); 
            } 
               
           }, SPLASH_DISPLAY_LENGHT); 
//    	
//    	// The thread to wait for splash screen events
//    	mSplashThread =  new Thread(){
//
//			@Override
//    		public void run(){
//    			try {
//    				synchronized(this){
//    					// Wait given period of time or exit on touch
//    					wait(5000);
//    				}
//    			} 
//    			catch(InterruptedException ex){    				
//    			}
//
//    			finish();
//    			
//    			// Run next activity
////    			Intent intent = new Intent();
////    			intent.setClass(sPlashScreen, MainActivity.class);
////    			startActivity(intent);
//    			 
//    			Message msg = new Message();
//    			msg.what = SplashScreen.this.ACTIVE ;
//    			mHandler.sendMessage(msg);
//    			stop();    
//    		}
//    	};
//    	
//    	mSplashThread.start();
//    	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
		return false;
	} 
	  
    /**
     * Processes splash screen touch events
     */
    @Override
    public boolean onTouchEvent(MotionEvent evt)
    {
    	if(evt.getAction() == MotionEvent.ACTION_DOWN)
    	{
    		synchronized(mSplashThread){
    			mSplashThread.notifyAll();
    		}
    	}
    	return true;
    }
    private Handler mHandler = new Handler(){
    	@Override
    	public void handleMessage(Message msg) {//处理消息
    		mHandler.post(new Runnable(){
				@Override
				public void run() {
					Intent intent = new Intent();
					intent.setClass(sPlashScreen, MainActivity.class);
	    			startActivity(intent);
				}
    			
    		});
    	}
    };

}
