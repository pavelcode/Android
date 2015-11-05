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
 * runOnUiThread 是主线程
 * @author pavel
 *
 */
public class HandlerActivityDemo08 extends Activity {

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
				runOnUiThread(new  Runnable() {
					public void run() {
						for(int i=0;i<10;i++){
								 tv.setText(i+"");
								 Log.i("aaa", ""+i);
							}
					}
				}) ;
			}
		});
		
	}
	
	
	
}
