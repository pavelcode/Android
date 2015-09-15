package com.cblue.component.broadcast;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cblue.android.R;


/**
 * 
 * 1 使用显式Intent发送广播
 * 2 使用隐式Intent发送广播
 * 3 无序广播：使用隐式Intent发送广播给MyBroadcastReceiver01，MyBroadcastReceiver01
 * 4 有序广播：使用隐式Intent发送广播给
 * @author Administrator
 *
 */
public class MyBroadcastReceiverActivity extends Activity {

	
	private Button sendBroadcastButton;
	private Button sendMyBroadCastBtn;
	private Button notificationBroadCastBtn;
	private Button orderedBroadCastBtn;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mybroadcastreceiver);
		
		sendBroadcastButton = (Button)findViewById(R.id.SendBroadcastBtn);
		sendMyBroadCastBtn = (Button) findViewById(R.id.Send_MyBroadCastBtn);
		notificationBroadCastBtn = (Button)findViewById(R.id.NotificationBroadCastBtn);
		orderedBroadCastBtn = (Button) findViewById(R.id.OrderedBroadCastBtn);
		
		
		sendBroadcastButton.setOnClickListener(listener);
		sendMyBroadCastBtn.setOnClickListener(listener);
		notificationBroadCastBtn.setOnClickListener(listener);
		orderedBroadCastBtn.setOnClickListener(listener);	 
	}
	private OnClickListener listener = new OnClickListener()
	{
		
		
		public void onClick(View v)
		{
			
			switch (v.getId()) {
			//简单的发送一个广播
			case R.id.SendBroadcastBtn:
			Intent intent1 = new Intent(MyBroadcastReceiverActivity.this, MyBroadcastReceiver01.class);
			sendBroadcast(intent1);			
			break;
			//Intent-Filter定义的多个Receiver都接收到广播
			case R.id.Send_MyBroadCastBtn:
				Intent intent2 = new Intent();
				intent2.setAction("aaa");
				sendBroadcast(intent2);
				break;
		
			//有序广播
			case R.id.OrderedBroadCastBtn:
				Intent intent4 = new Intent();
				intent4.setAction("bbb");
				sendOrderedBroadcast(intent4, null);
				break;

		
				
				
				
				//Nofication提示广播
			case R.id.NotificationBroadCastBtn:
				Intent intent3 = new Intent(MyBroadcastReceiverActivity.this, BroadcastNotificationDemo04.class);
				sendBroadcast(intent3);	
				break;
			}
			
		}
	};

}
