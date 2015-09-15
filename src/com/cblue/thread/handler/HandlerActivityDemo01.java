package com.cblue.thread.handler;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cblue.android.R;

/**
 * 为什么要使用线程？
 * 当我们使用主线程执行耗时操作的时候，主线程阻塞5s，就会出现ANR问题，所以必须重新创建新线程来执行耗时操作。
 * @author Administrator
 *
 */
public class HandlerActivityDemo01 extends Activity {
	
	private Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handler1);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(6*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
