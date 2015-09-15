package com.cblue.component.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

/**
 * 启动Service：执行一个耗时操作，当服务执行完之后，使用Handler把服务关闭掉
 * @author Administrator
 *
 */
public class StartService extends Service
{
  private static String TAG = "MyStartService";
  private MyHandler myHandler;
  
  class MyHandler extends Handler{
	  @Override
	public void handleMessage(Message msg) {
		 if(msg.what==1){
			 //停止服务
			stopSelf();
		 }
	}
  }
  
	@Override
	public IBinder onBind(Intent arg0)
	{
		return null;
	}

	@Override
	public void onCreate()
	{
		Log.i(TAG, "-->onCreate");
		myHandler = new MyHandler();
		super.onCreate();
	}

	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		Log.i(TAG, "启动服务");
		String name = intent.getStringExtra("name");
		Log.i(TAG, "name="+name);
		/**
		 * 直接使用耗时操作的话，按钮不能被重复点击了，5秒之后会出现ANR错误
		 */
/*		try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		/**
		 * 使用线程实现耗时操作,就不会出现ANR
		 */
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Log.i(TAG, "currentThreadID"+Thread.currentThread().getId());
				try {
					Thread.sleep(10*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//线程运行结束后，需要停止Service，否则会一直运行
				myHandler.sendEmptyMessage(1);
				
			}
		}).start();
		
		
		/***
		 * Service的分类
		 */
/*		int sticky = Service.START_STICKY;
		int notsticky = Service.START_NOT_STICKY;
		int redeliver = Service.START_REDELIVER_INTENT;*/

		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public void onDestroy()
	{
		Log.i(TAG, "-->onDestroy");
		
		super.onDestroy();
	}

	
	
	

}
