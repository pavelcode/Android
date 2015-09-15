package com.cblue.statusbar;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import com.cblue.android.R;

public class MyStatusBarService extends IntentService
{
	private static final String TAG ="MyStatusBarService";
	private static final int RCODE= 0;
	private static final int NID= 0x7f060000;

	public MyStatusBarService()
	{
		super("MyStatusBarService");
	}
	
	@Override
	protected void onHandleIntent(Intent intent)
	{
		Log.i(TAG,"开始下载。。。");
		showStatusBar(false);
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Log.i(TAG, "停止下载。。。。");
        showStatusBar(true);
	}

	private void showStatusBar(boolean isfinish)
	{
		NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		Notification notification = null;
		if(!isfinish)
		{
		notification = new Notification(R.drawable.statusbar, "下载开始",System.currentTimeMillis());
		Intent intent = new Intent(this,MyStatusBarActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, RCODE, intent,PendingIntent.FLAG_UPDATE_CURRENT);
		notification.setLatestEventInfo(this, "正在下载", "下载中", pendingIntent);
		}
		else
		{
		    notification = new Notification(R.drawable.statusbar, "下载完成",System.currentTimeMillis());
			Intent intent = new Intent(this,MyStatusBarActivity.class);
			PendingIntent pendingIntent = PendingIntent.getActivity(this, RCODE, intent,PendingIntent.FLAG_UPDATE_CURRENT);
			notification.setLatestEventInfo(this, "完成下载", "已经完成下载", pendingIntent);
		}
		notificationManager.notify(NID, notification);
	}
	
	

}
