package com.cblue.ui.nightandday;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NightAndDayActivity extends Activity {
	
	protected static final String TAG = NightAndDayActivity.class.getSimpleName();
	private Button button;
	private int[] styleArr ={R.style.DayTheme,R.style.NightTheme};
	private int currentStyle ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		currentStyle = styleArr[1];
		setTheme(currentStyle);
		setContentView(R.layout.nightandday);
		button = (Button) findViewById(R.id.nd_btn);
		//失败
		/*button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i(TAG, "currentStyle:"+currentStyle);
				if(currentStyle==R.style.DayTheme){
					setTheme(R.style.NightTheme);
					currentStyle=R.style.NightTheme;
				}else if(currentStyle==R.style.NightTheme){
					setTheme(R.style.DayTheme);
					currentStyle=R.style.DayTheme;
				}
				setContentView(R.layout.activity_main);
			}
		});*/
	}
	
	public void onChange(View view){
		Log.i(TAG, "currentStyle:"+currentStyle);
		if(currentStyle==R.style.DayTheme){
			setTheme(R.style.NightTheme);
			currentStyle=R.style.NightTheme;
		}else if(currentStyle==R.style.NightTheme){
			setTheme(R.style.DayTheme);
			currentStyle=R.style.DayTheme;
		}
		setContentView(R.layout.nightandday);
	}

	
}
