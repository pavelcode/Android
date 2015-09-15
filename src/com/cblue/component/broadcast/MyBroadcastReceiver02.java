package com.cblue.component.broadcast;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class MyBroadcastReceiver02 extends BroadcastReceiver {
	
	private static final String TAG= MyBroadcastReceiver02.class.getSimpleName();

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG, "收到广播");
	/*	NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
		builder.setTicker("广播来了");
		builder.setContentTitle("断网");
		builder.setContentText("断网了");
		manager.notify(1011, builder.build());*/

	}

}
