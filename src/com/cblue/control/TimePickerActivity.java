package com.cblue.control;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;

import com.cblue.android.R;

public class TimePickerActivity extends Activity {
	
	TimePicker timePicker ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timepicker);
		timePicker = (TimePicker)findViewById(R.id.TP);
		timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
			
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				String time = hourOfDay+":"+minute;
				Toast.makeText(TimePickerActivity.this, "当前时间为:"+time, Toast.LENGTH_LONG).show();
			}
		});
		
	}
}
