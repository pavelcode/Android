package com.cblue.thread.handler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cblue.android.R;

/**
 * 这次演示的过程中，第一次没有成功，要谨慎，这里没有用到handler
 * handler更新进度条
 * 
 * @author Administrator
 *
 */
public class HandlerActivityDemo04 extends Activity {
	
	private Button button;
	private ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.handler3);
	    progressDialog =  new ProgressDialog(HandlerActivityDemo04.this);
	    progressDialog.setTitle("提示");
	    progressDialog.setMessage("正在下载。。。。。。");
	    //设置是横向的进度条
	    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	    //设置不能取消进度条
	    progressDialog.setCancelable(false);
	     
	    button  = (Button)findViewById(R.id.handlebtn03);
	    button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				progressDialog.show();
				new Thread(new MyThread()).start();
				
			}
		});
	}
	
	public class MyThread implements Runnable{
	
	MyHandler myHandler = new MyHandler();
		int count = 0;
		@Override
		public void run() {
			//不断的传递进度条的值
			while(count<=100){
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					// TODO: handle exception
				}
				Message message = Message.obtain();
				message.arg1= count;
				myHandler.sendMessage(message);
				count++;
			}
			//当进度条满了之后，发送一个消息，让进度条消失
			Message endMessage = Message.obtain();
			endMessage.what=1;
			myHandler.sendMessage(endMessage);
			//myHandler.sendEmptyMessage(1);
			
		}
		
	}
	
	public class MyHandler extends Handler{
		
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			int progress = msg.arg1;
			progressDialog.setProgress(progress);
			if(msg.what==1){
				progressDialog.dismiss();
			}
		    
			
		}
	}

	

}



