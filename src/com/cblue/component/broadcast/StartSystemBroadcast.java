package com.cblue.component.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 接收开机自启动广播
 * @author Administrator
 *
 */
public class StartSystemBroadcast extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		//定义一个接收广播，跳转到启动Actvity
		Intent mIntent = new Intent(context,MyBroadcastReceiverActivity.class);
		//设定一个标志位，重新启动一个Activity
		mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(mIntent);
		
	}

}
