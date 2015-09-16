package com.cblue.file.download.singlethread;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class DownLoadService extends Service {

	public static final String ACTION_START_DOWNLOAD="ACTION_START";
	public static final String ACTION_STOP_DOWNLOAD="ACTION_STOP";
	public static final String ACTION_BROADCAST="ACTION_BROADCAST";
	
	private DownLoadHandler downLoadHandler;
	public static boolean isPause = false;
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		 downLoadHandler  = new DownLoadHandler(DownLoadService.this);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		if(ACTION_START_DOWNLOAD.equals(intent.getAction())){
			FileInfo fileInfo = (FileInfo) intent.getSerializableExtra("fileInfo");
		//	Log.i("aaa", "DownLoadService="+fileInfo.toString());
			isPause = false;
			//启动线程进行下载
			new DownLoadInitThread(fileInfo, downLoadHandler).start();
		}else if(ACTION_STOP_DOWNLOAD.equals(intent.getAction())){
			FileInfo fileInfo = (FileInfo) intent.getSerializableExtra("fileInfo");
		//	Log.i("aaa", fileInfo.toString());
			isPause = true;
			
		}
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
