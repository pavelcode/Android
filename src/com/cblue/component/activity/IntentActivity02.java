package com.cblue.component.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.cblue.android.R;

/**
 * Activity之间进行跳转传递信息-接收信息
 * @author Administrator
 *
 */
public class IntentActivity02 extends Activity {
	
	private static final String TAG="IntentActivity02";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intent2);
		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		int  age = intent.getIntExtra("age", 0);
		
		Bundle bundle = intent.getBundleExtra("bundle");
		String address = bundle.getString("address");
		
		Log.i(TAG,"name="+name+" age="+age+" address="+address);
	}
	
	
	

}
