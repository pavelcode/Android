package com.cblue.component.service;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cblue.android.R;

public class MyIntentServiceActivity extends Activity
{
	private static String TAG="MyIntentServiceActivity";
	private Button startServiceButton;
	private Button stopServiceButton;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service);
		startServiceButton = (Button) findViewById(R.id.but1);
		stopServiceButton =(Button) findViewById(R.id.but2);
		startServiceButton.setOnClickListener(listener);
		stopServiceButton.setOnClickListener(listener);
	}
	
	private OnClickListener  listener = new OnClickListener()
	{

		
		public void onClick(View v)
		{
		  Intent intent = new Intent(MyIntentServiceActivity.this,MyIntentService.class);
			switch(v.getId())
			{
			   case R.id.but1:
				   Log.i(TAG, "主线程"+Thread.currentThread().getId());
				   startService(intent);
				   break;
				   
			   case R.id.but2:	   
				   stopService(intent);
				   break;
			    default: break;
			}
		}
		
	};

}
