package com.cblue.thread.handler;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * Timer Schedular TimeTask
 * 定时器，计划，定时任务
 * @author pavel
 *
 */
public class HandlerActivityDemo09 extends Activity {

	
	private TextView tv;
	private Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handler7);
		tv= (TextView)findViewById(R.id.tv07);
		btn = (Button)findViewById(R.id.btn07);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//五秒之后，每个1秒执行定时任务
				new Timer().schedule(new TimerTask() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						for(int i=0;i<10;i++){
							Message message = Message.obtain();
							message.arg1 =i;
							myHandler.sendMessage(message);
						}
					}
				}, 5000, 1000);
				
			}
		});
		
	}
	
	
	Handler myHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			 int value =  msg.arg1;
			 Log.i("aaa", "value="+value);
			 tv.setText(value+"");	
		};
	};
	
	
	
	
}
