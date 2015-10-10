package com.cblue.ui.time;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


/**
 * 先讲定时执行，然后使用定时执行，直接更新UI失败,引出Handler
 * @author Administrator
 *
 */
public class TimerActivity extends Activity {
	
	public static final String TAG="TimerActivity";
	
	int count =0;

	TextView time_tv;
	Button time_btn;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timer);
		
		/*Timer mTimer = new Timer();
		mTimer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
			    Log.i(TAG, "----"+(count++));
				
			}
		}, new Date(),1000);*/
	
		time_tv = (TextView)findViewById(R.id.timer_tv);
		time_btn = (Button)findViewById(R.id.timer_btn);
	
		time_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Timer mTimer = new Timer();
				mTimer.schedule(new TimerTask() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						int i = count++;
					    time_tv.setText(i);
						
					}
				}, new Date(),1000);
			}
		});

	};

}
