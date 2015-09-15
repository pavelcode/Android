package com.cblue.component.broadcast;


import com.cblue.android.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * 接收到广播之后notification提示
 * @author Administrator
 *
 */
public class BroadcastNotificationDemo04 extends BroadcastReceiver {

	NotificationManager notificationManager = null;
	
	private static final String TAG= BroadcastNotificationDemo04.class.getSimpleName();

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "广播来了");
		
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
		//TODO 少了这行代码一直出不来
		//错误：Ignoring notification with icon==0: Notification(pri=0 contentView=com.cblue.android/0x1090071 vibrate=null sound=null defaults=0x0 flags=0x0 kind=[null]
		builder.setSmallIcon(R.drawable.icon);
		builder.setTicker("广播来了");
		builder.setContentTitle("广播标题");
		builder.setContentText("广播内容");
		notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(1001, builder.build());
	}

}
