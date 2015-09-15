package com.cblue.component.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class CopyFileService extends Service {
	
	private String fileName = null;
	private MyHandler myHandler;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i("aaa", "onCreate----");
		myHandler =new MyHandler();
	}

	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		fileName = intent.getStringExtra("fileName");
		if(fileName!=null){
			 new MyThread().start();
		}
		
		
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("aaa", "onDestroy----");
	}
	
	class MyThread extends Thread{
		
		
		@Override
		public void run() {
			// TODO Auto-generated method stub2
		    try {
				byte[] data = readInternalFile(fileName);
				writeSDCardFile(fileName, data);
				myHandler.sendEmptyMessage(1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		
		}
	}
	class MyHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if(msg.what==1){
				stopSelf();
			}
		}
	}
	
	public byte[] readInternalFile(String fileName) throws Exception {
		FileInputStream mFileInputStream = null;
		mFileInputStream = getApplicationContext().openFileInput(fileName);
		ByteArrayOutputStream mArrayOutputStream = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		int length = 0;
		while ((length = mFileInputStream.read(b)) != -1) {
			mArrayOutputStream.write(b, 0, length);
		}
		if (mFileInputStream != null) {
			mFileInputStream.close();
		}
		if (mArrayOutputStream != null) {
			mArrayOutputStream.close();
		}
		return mArrayOutputStream.toByteArray();
	}
	
	public boolean writeSDCardFile(String fileName, byte[] data)
			throws Exception {
		boolean flag = false;
		// 判断SD卡的状态
		String state = Environment.getExternalStorageState();
		FileOutputStream mFileOutputStream;
		// SD卡是否挂载成功
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			// 得到SD卡的根目录 /storage/sdcard
			File root = Environment.getExternalStorageDirectory();
			Log.i("SDCardFileTools", root.toString());
			// 创建写入文件对象
			File file = new File(root, fileName);
			mFileOutputStream = new FileOutputStream(file);
			mFileOutputStream.write(data, 0, data.length);
			flag = true;
			if (mFileOutputStream != null) {
				mFileOutputStream.close();
			}
		}
		return flag;
	}
	
}
