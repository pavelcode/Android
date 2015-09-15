package com.cblue.widget;

import com.cblue.android.R;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;


public class ExampleAppWidgetProvider extends AppWidgetProvider
{
	private final String broadCastString = "com.cblue.widget.appWidgetUpdate"; 
	
	private static final String TAG = "ExampleAppWidgetProvider";

	@Override
	public void onDeleted(Context context, int[] appWidgetIds)
	{
		Log.i(TAG, "Deleted");
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled(Context context)
	{
		Log.i(TAG, "Disabled");
		super.onDisabled(context);
	}

	@Override
	public void onEnabled(Context context)
	{
		Log.i(TAG, "Enabled");
		super.onEnabled(context);
	}

	@Override
	public void onReceive(Context context, Intent intent)
	{
		Log.i(TAG, "Receive");
		if (intent.getAction().equals(broadCastString))
		{				
			//只能通过远程对象来设置appwidget中的控件状态
			RemoteViews remoteViews  = new RemoteViews(context.getPackageName(),R.layout.widget_first);
			remoteViews.setTextViewText(R.id.txt, "修改后信息");
			
			//获得appwidget管理实例，用于管理appwidget以便进行更新操作
			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

			//相当于获得所有本程序创建的appwidget
			ComponentName componentName = new ComponentName(context,ExampleAppWidgetProvider.class);

			//更新appwidget
			appWidgetManager.updateAppWidget(componentName, remoteViews);

			Log.i(TAG, "OK");
		}
		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
	{
		Log.i(TAG, "Update");
		for (int i = 0; i < appWidgetIds.length; i++)
		{
			Intent intent = new Intent(context, WidgetActivity.class);
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
             //得到全部控件，设置事件
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_second);
			views.setOnClickPendingIntent(R.id.btn, pendingIntent);
			
			Intent intent2 = new Intent();
			intent2.setAction(broadCastString);
			PendingIntent pendingIntent2 = PendingIntent.getBroadcast(context, 0, intent2, 0);
			views.setOnClickPendingIntent(R.id.broadcast, pendingIntent2);

			appWidgetManager.updateAppWidget(appWidgetIds[i], views);
			Log.i(TAG, "Length:" + appWidgetIds.length + ";I=" + i);
		}
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

}
