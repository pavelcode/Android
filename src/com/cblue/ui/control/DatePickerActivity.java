package com.cblue.ui.control;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;
import android.widget.DatePicker.OnDateChangedListener;

import com.cblue.android.R;

public class DatePickerActivity extends Activity {

	
	DatePicker datePicker;
	   @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.control_datepicker);
		datePicker = (DatePicker)findViewById(R.id.DP);
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
	
		
		datePicker.init(year, month, dayOfMonth,new OnDateChangedListener() {
			
			public void onDateChanged(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				String date = year+"-"+(monthOfYear+1)+"-"+dayOfMonth;
				Toast.makeText(DatePickerActivity.this, "当前日期为："+date, Toast.LENGTH_LONG).show();
				
			}
		});
	}
}
