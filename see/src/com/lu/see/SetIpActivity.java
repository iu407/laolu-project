package com.lu.see;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SetIpActivity extends Activity {
	private EditText ipEditTxt;
	private Button ipOkBtn;
	private Button ipCnlBtn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setip);
	}
	
	public void init(){
		ipEditTxt=(EditText)findViewById(R.id.ipEditTxt);
		ipOkBtn=(Button)findViewById(R.id.ipOkBtn);
		ipCnlBtn=(Button)findViewById(R.id.ipCnlBtn);
		
	}
}
