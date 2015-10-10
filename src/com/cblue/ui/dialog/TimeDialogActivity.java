package com.cblue.ui.dialog;

import java.util.Calendar;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;

import com.cblue.android.R;

public class TimeDialogActivity extends Activity
{
	 TextView mTimeDisplay;
	 TimePickerDialog timePickerDialog;


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_time);
		
		mTimeDisplay = (TextView) findViewById(R.id.timeDisplay);
		
		//得到当前时间
		final Calendar c = Calendar.getInstance();
		int mHour = c.get(Calendar.HOUR_OF_DAY);
		int mMinute = c.get(Calendar.MINUTE);
		
		timePickerDialog = new TimePickerDialog(TimeDialogActivity.this, new OnTimeSetListener() {
			
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				mTimeDisplay.setText(new StringBuilder().append("时间：").append(hourOfDay).append(":").append(minute));
			}
		}, mHour, mMinute, true);

		timePickerDialog.show();
	}

	
}