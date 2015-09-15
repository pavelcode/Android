package com.cblue.dialog;

import java.util.Calendar;

import com.cblue.android.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.DatePicker.OnDateChangedListener;

public class DateDialogActivity extends Activity {
	
	private DatePickerDialog datePickerDialog;
	private TextView mDateDisplay;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_date);
		
		final Calendar c = Calendar.getInstance();
		int mYear = c.get(Calendar.YEAR);
		int mMonth = c.get(Calendar.MONTH);
		int mDay = c.get(Calendar.DAY_OF_MONTH);
		
		mDateDisplay = (TextView) findViewById(R.id.dateDisplay);
		
		datePickerDialog = new DatePickerDialog(DateDialogActivity.this,
				new OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// TODO Auto-generated method stub
						mDateDisplay.setText(new StringBuilder().append("时间：")
								.append(year).append("-")
								.append(monthOfYear + 1).append("-")
								.append(dayOfMonth));
					}
				}, mYear, mMonth, mDay);
		
		datePickerDialog.show();

	}
}
