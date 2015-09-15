package com.cblue.component.broadcast;


import com.cblue.android.R;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 监控电量广播
 * @author Administrator
 *
 */
public class BatteryStateActivity extends Activity {
	
	
	

	BatteryBroadcastReceiver batteryBroadcastReceiver = new BatteryBroadcastReceiver();
	TextView textView ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.barrerybroadcastreceiver);
	    textView = (TextView)findViewById(R.id.battery);
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		registerReceiver(batteryBroadcastReceiver, intentFilter);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		unregisterReceiver(batteryBroadcastReceiver);
	}
	
	/**
	 * 监听电量变化的广播
	 * @author Administrator
	 */
	public class BatteryBroadcastReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if(intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)){
				//获得当前电量
				int level = intent.getIntExtra("level", 0);
				//总电量刻度
				int scale = intent.getIntExtra("scale", 100);
				
				textView.setText("总电量为："+((level*100)/scale)+"%");
				
				if(level<15){
				   //做什么事情	
				}
			}
			
		}
	}
	
}
