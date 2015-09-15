package com.cblue.thread.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.cblue.android.R;

/**
 * 
 * Handler发送信息的多种方式
 *  发送的内容  arg1
 *          arg2
 *          Object
 *          发送的目标 targetHandler
 *  
 * @author Administrator
 *
 */
public class HandlerActivityDemo05 extends Activity {
	
	private TextView textView;
	private Button button;
	private MyHandler04 myHandler04;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handler4);
		textView = (TextView)findViewById(R.id.handlertv04);
		button = (Button)findViewById(R.id.handlerbtn04);
		myHandler04 = new MyHandler04();
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread(new MyThread04()).start();
			}
		});		
		
	}
	public class MyThread04 implements Runnable{
		@Override
		public void run() {
			
			// TODO Auto-generated method stub
			/*Message message = Message.obtain();
			message.arg1=1;
			message.obj = "zhangsan";
			message.what =1;
			myHandler04.sendMessage(message);*/
			
			/*Message message = Message.obtain(myHandler04);
			message.arg1=1;
			message.obj="zhangsan";
			message.what=1;
			message.sendToTarget();
			*/
			
			/*Message message = Message.obtain(myHandler04, 1);
			message.arg1=1;
			message.obj="zhangsan";
			message.sendToTarget();
			*/
			
			/*Message message = Message.obtain(myHandler04, 1, 2, 3, "张三");
			message.sendToTarget();
			*/
			
/*			Message message = myHandler04.obtainMessage();
			message.what=1;
			message.arg1=1;
			message.sendToTarget();*/
			
			//发送空消息(就是发送一个标志位)
			
			myHandler04.sendEmptyMessage(1);
			
			//发送延迟消息
			Message message = Message.obtain();
			message.arg1=1;
			message.obj = "zhangsan";
			message.what =1;
			myHandler04.sendMessageDelayed(message, 1000);
				
		}
	}
	
	public class MyHandler04 extends Handler{
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.what==1){
				textView.setText(msg.arg1+"---"+msg.obj);
			}
			
		}
	}
	
	
}
