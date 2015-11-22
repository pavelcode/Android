package com.cblue.component.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

public class MyBinderService extends Service {
	private static String TAG = "MyBinderService";
	private MyBinder myBinder = new MyBinder();

	public class MyBinder extends Binder {
		// 要求一个方法：要求返回当前的绑定服务的对象
		public MyBinderService getBinder() {
			return MyBinderService.this;
		}

		@Override
		protected boolean onTransact(int code, Parcel data, Parcel reply,
				int flags) throws RemoteException {
			// TODO Auto-generated method stub
			String dataStr = data.readString();
			Log.i("aaa", "dataStr=" + dataStr);
			reply.writeString("from service reply");
			return super.onTransact(code, data, reply, flags);
		}


	}
	

	@Override
	public void onCreate() {
		Log.i(TAG, "-->onCreate");
		super.onCreate();
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.i(TAG, "-->onBind");
		return myBinder;
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		//当执行unbindService的方法时候，执行这个onUnbind()
		Log.i("aaa", "--->onUnbind");
		return super.onUnbind(intent);
	}

	@Override
	public void onDestroy() {
		Log.i(TAG, "-->onDestroy");
		super.onDestroy();
	}

	public void myMethod() {
		new Thread(new Runnable() {
			/**
			 * 直接使用耗时操作，说明Activity和Service在一个线程中 必须加一个线程，执行耗时操作
			 */
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Log.i(TAG, "1111111111111111");
				try {
					Thread.sleep(20 * 1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.i(TAG, "22222222222222222");
				Log.i(TAG, "我的服务要执行方法");
			}
		}).start();

	}
}
