package com.cblue.component.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver01 extends BroadcastReceiver {
	
	private static final String TAG= MyBroadcastReceiver01.class.getSimpleName();

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG, "收到广播");

	}

}
