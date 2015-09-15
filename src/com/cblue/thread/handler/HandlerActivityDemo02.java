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
 * 首先，使用线程中进行修改，出现错误，说明不能使用线程来直接修改内容。
 * Handler使用线程更新UI
 * @author Administrator
 *
 */
public class HandlerActivityDemo02 extends Activity {


	private TextView textView;
	private Button button;
	private MyHandler myHandler = new MyHandler();

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
                new Thread(new MyThread()).start();
			}
		});
	}

	// 发送消息
	class MyThread implements Runnable {

		int count = 0;

		@Override
		public void run() {

			while (count <= 20) {
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				Message message = Message.obtain();
				message.arg1 = count;
				message.obj = "zhangsan";
				count++;
				myHandler.sendMessage(message);

			}

		}

	}

	// 接收消息
	class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			int arg1 = msg.arg1;
			String name = (String)msg.obj;
			//这里不能是int类型的，必须是字符串
			textView.append(name+arg1);
		}
	}

}
