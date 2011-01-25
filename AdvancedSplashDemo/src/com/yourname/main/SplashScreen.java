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
    	
    	new Handler().postDelayed(new Runnable(){ 
    		  
            @Override 
            public void run() { 
                Intent mainIntent = new Intent(SplashScreen.this,MainActivity.class); 
                SplashScreen.this.startActivity(mainIntent); 
                SplashScreen.this.finish(); 
            } 
               
           }, SPLASH_DISPLAY_LENGHT); 
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
    	}
    	return true;
    }

}
