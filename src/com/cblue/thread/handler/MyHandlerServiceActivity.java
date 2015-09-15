package com.cblue.thread.handler;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cblue.android.R;

/**
 * 
 * 这里用到了looper+黏性service+Handler 但是looper没有深刻理解
 * 
 * 这个例子不好，没有在Serivce中使用线程
 * Looper：在一个程序中只有一个looper对象，所以当你在子类中使用Looper对象的时候，必须得到主线程的Looper（ Looper.getMainLooper()）
 * @author Administrator
 *
 */
public class MyHandlerServiceActivity extends Activity
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
		  Intent intent = new Intent(MyHandlerServiceActivity.this,MyHandlerService.class);
			switch(v.getId())
			{
			   case R.id.but1:
				   startService(intent);
				   break;
				   
			   case R.id.but2:	   
				   stopService(intent);
				   break;
			}
		}
		
	};

}
