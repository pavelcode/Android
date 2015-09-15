package com.cblue.component.service;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cblue.android.R;



public class StartServiceActivity extends Activity
{
	
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
		  Intent intent = new Intent(StartServiceActivity.this,StartService.class);
			switch(v.getId())
			{
			   case R.id.but1:
				 //启动服务
				   intent.putExtra("name", "zhang");
				   startService(intent);
				   break;
				   
			   case R.id.but2:	
				 //暂停服务
				   stopService(intent);
				   break;
			    default: break;
			}
		}
		
	};

}
