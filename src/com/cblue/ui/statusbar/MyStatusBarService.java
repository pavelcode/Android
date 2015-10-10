package com.cblue.ui.statusbar;

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
		Log.i(TAG,"��ʼ���ء�����");
		showStatusBar(false);
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Log.i(TAG, "ֹͣ���ء�������");
        showStatusBar(true);
	}

	private void showStatusBar(boolean isfinish)
	{
		NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		Notification notification = null;
		if(!isfinish)
		{
		notification = new Notification(R.drawable.statusbar, "���ؿ�ʼ",System.currentTimeMillis());
		Intent intent = new Intent(this,MyStatusBarActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, RCODE, intent,PendingIntent.FLAG_UPDATE_CURRENT);
		notification.setLatestEventInfo(this, "��������", "������", pendingIntent);
		}
		else
		{
		    notification = new Notification(R.drawable.statusbar, "�������",System.currentTimeMillis());
			Intent intent = new Intent(this,MyStatusBarActivity.class);
			PendingIntent pendingIntent = PendingIntent.getActivity(this, RCODE, intent,PendingIntent.FLAG_UPDATE_CURRENT);
			notification.setLatestEventInfo(this, "�������", "�Ѿ��������", pendingIntent);
		}
		notificationManager.notify(NID, notification);
	}
	
	

}
