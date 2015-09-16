package com.cblue.file.download.singlethread;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class DownLoadHandler extends Handler {
	
	public static final int ACTION_HANDLER= 1;
	private static final String TAG = DownLoadHandler.class.getSimpleName();
	private Context context;
	
	public DownLoadHandler(Context context){
		this.context = context;
	}
	
	
	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		switch (msg.what) {
		case ACTION_HANDLER:
			FileInfo fileInfo = (FileInfo) msg.obj;
			//Log.i("aaa", "DownLoadHandler---"+fileInfo.toString());
			//开始下载
			new DownLoadTaskThread(fileInfo, context).start();
			break;

		default:
			break;
		}
	}

}
