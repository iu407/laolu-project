package com.lu.see;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetIpActivity extends Activity {
	private EditText ipEditTxt;
	private Button ipOkBtn;
	private Button ipCnlBtn;
	public static final int RESULT_SUBMIT = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setip);
		init();
		
		
		ipOkBtn.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				Bundle bundle = new Bundle(); 
				bundle.putString("ipstring", ipEditTxt.getText().toString()); 
				SetIpActivity.this.setResult(RESULT_SUBMIT, SetIpActivity.this.getIntent().putExtras(bundle)); 
				SetIpActivity.this.finish(); 
			}
			
		});
	}
	
	public void init(){
		ipEditTxt=(EditText)findViewById(R.id.ipEditTxt);
		ipOkBtn=(Button)findViewById(R.id.ipOkBtn);
		ipCnlBtn=(Button)findViewById(R.id.ipCnlBtn);
		
	}
}
