package com.cblue.thread.runonuithread;


import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RunOnUIThreadDemo01 extends Activity {
	
	private TextView textView;
	private Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handler2);
		textView = (TextView) findViewById(R.id.handletx02);
		button = (Button) findViewById(R.id.handlebtn02);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("aaa", "11111111");
				new Thread(new Runnable() {
					int count=0;
					@Override
					public void run() {
						// TODO Auto-generated method stub	
						while(count<=20){
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							count++;
							Log.i("aaa", "count="+count);
							runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									textView.setText(count+"");
								}
							});
							
						}
						
					}
				}).start();
			}
		});
	}

}
