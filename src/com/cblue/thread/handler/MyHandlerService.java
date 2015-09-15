package com.cblue.thread.handler;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;


public class MyHandlerService extends Service
{
	private static final int KAKU =1;
	private static final int COOLX=2;
	private static final String TAG = "MyHandlerService";
	private MyHandler myHandler;
	
	private class MyHandler extends Handler
	{

		public MyHandler(Looper looper)
		{
			super(looper);
		}
		
		@Override
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
			case KAKU:
				Log.i(TAG, "KAKU"+msg.obj);
				break;
			case COOLX:
				Log.i(TAG, "COOLX"+msg.obj);
			default:
				break;
			}
			super.handleMessage(msg);
		}
		
	}
	@Override
	public IBinder onBind(Intent intent)
	{
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public void onCreate()
	{
	    Log.i(TAG,"-->onCreate");
	    Looper looper = Looper.getMainLooper();
	    myHandler = new MyHandler(looper);
	    
	}


	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		Log.i(TAG, "-->onStartCommand");
		Message msg = myHandler.obtainMessage();
		msg.what=KAKU;
		msg.arg1=startId;
		msg.obj="传递的信息";
		myHandler.sendMessage(msg);
		return START_STICKY;
	}

	
	@Override
	public void onDestroy()
	{
		Log.i(TAG, "-->onDestroy");
		super.onDestroy();
	}

	

}
