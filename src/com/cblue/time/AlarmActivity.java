package com.cblue.time;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;

import com.cblue.android.R;

/**
 * 系统级别的定时服务
 * 启动时，先得到当前的时间，当点击按钮的时候，弹出一个时间框，显示当前时间
 * 选择定时任务要执行的时间
 * 时间到了，执行任务：发送一个广播，填出一个对话框，并启动一个音乐服务
 * 
 * @author Administrator
 */
public class AlarmActivity extends Activity {

	Button setAlarmBtn;
	Button stopAlarmBtn;
	AlarmManager mAlarmManager;
	PendingIntent operation;
	Calendar calendar;
	public static final String TAG="AlarmActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.time_alarm);
		setAlarmBtn = (Button) findViewById(R.id.setalarm_btn);
		stopAlarmBtn = (Button) findViewById(R.id.stopalarm_btn);
		
		setAlarmBtn.setOnClickListener(listener);
		stopAlarmBtn.setOnClickListener(listener);
		
		//管理器
		mAlarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.setalarm_btn:
		
				//得到当前时间，设定TimePickerDialog显示当前时间
			    calendar = Calendar.getInstance();
				calendar.setTimeInMillis(System.currentTimeMillis());
				int hour = calendar.get(Calendar.HOUR_OF_DAY);
				int minute = calendar.get(Calendar.MINUTE);
				Log.i(TAG, "获得当前时间：hour=" + hour + ":" + "minute"
						+ minute);
				
		TimePickerDialog  dialog = new TimePickerDialog(AlarmActivity.this, new OnTimeSetListener() {
					
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						// TODO Auto-generated method stub
						//得到定时任务的时间
						Log.i(TAG, "设定定时任务时间hour=" + hourOfDay + ":"
								+ "minute=" + minute);
						//calendar.setTimeInMillis(System.currentTimeMillis());
						calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
						calendar.set(Calendar.MINUTE, minute);
						calendar.set(Calendar.SECOND, 0);

						//定义一个广播任务
						Intent intent = new Intent();
						intent.setAction("com.cblue.time.alarmbroadcast");
						operation = PendingIntent.getBroadcast(AlarmActivity.this, 1000, intent, PendingIntent.FLAG_UPDATE_CURRENT);

						//任务触发时间  1970 0:0:0
						long triggerAtTime = calendar.getTimeInMillis();
						//安排一个任务
						//alarmManager.setRepeating(type, triggerAtTime, interval, operation);
						mAlarmManager.set(AlarmManager.RTC_WAKEUP, triggerAtTime, operation);
					}
				}, hour, minute, true);
				
				dialog.show();

				break;
			case R.id.stopalarm_btn:
				mAlarmManager.cancel(operation);
				break;

			}

		}
	};

}
