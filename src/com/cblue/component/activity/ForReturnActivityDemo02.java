package com.cblue.component.activity;

import com.cblue.android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ForReturnActivityDemo02 extends Activity {
	
	private final String TAG= ForReturnActivityDemo02.class.getSimpleName();
	private TextView resultTV;
	private EditText  resultET;
	private Button resultBtn;
	private String result;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_foreturn2);
		resultTV = (TextView)findViewById(R.id.returnTV1);
		resultET = (EditText)findViewById(R.id.returnET1);
		resultBtn = (Button)findViewById(R.id.returnBtn1);
		
		Intent intent = getIntent();
	    String message = intent.getStringExtra("message");
	    //得到Bundle的结果
/*	    Bundle bundle = intent.getBundleExtra("bundle");
		String message = bundle.getString("message");*/
	    resultTV.setText(message);
	    
	   
	    resultBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				result = resultET.getText().toString().trim();
				Log.i(TAG, "result="+result);
				intent.putExtra("result", result);
				setResult(1001, intent);
				finish();
			}
		});
	  
		
	}
}
