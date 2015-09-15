package com.cblue.component.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * IntentService产生一个任务序列
 * @author Administrator
 *
 */
public class MyIntentService extends IntentService
{
	private static String TAG = "MyIntentService";
	public MyIntentService(){
		super("MyIntentService");
	}
	@Override
	protected void onHandleIntent(Intent arg0)
	{
		try {
			Thread.sleep(6*1000);
			Log.i(TAG, "-->新线程--"+Thread.currentThread().getId());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
