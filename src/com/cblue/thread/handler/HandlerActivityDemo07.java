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
 * post  postDelayed postAtTime方法
 * @author pavel
 *
 */
public class HandlerActivityDemo07 extends Activity {

	private TextView tv;
	private Button btn;
	private Handler mHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handler7);
		tv= (TextView)findViewById(R.id.tv07);
		btn = (Button)findViewById(R.id.btn07);
		mHandler = new Handler();
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//mHandler.post(runnable);
				//延迟五秒执行一次
				//mHandler.postDelayed(runnable, 5*1000);
				//当前时间五秒之后执行一次
				 mHandler.postAtTime(runnable, SystemClock.uptimeMillis()+5000);
			}
		});
		
	}
	
	
	//这里是主线程，可以更新UI，不能使用线程休眠
	Runnable runnable = new  Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=0;i<10;i++){
			/*	 try {
					Thread.sleep(2*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				 tv.setText(i+"");
				 Log.i("aaa", ""+i);
			}
		}		
	};
	
	
	
	
	
	
}
