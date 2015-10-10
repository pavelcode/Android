package com.cblue.ui.control;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TextViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.control_textview);		
		//跑马灯效果
		//setContentView(R.layout.control_textview2);		
		super.onCreate(savedInstanceState);
		/*TextView textView = new TextView(this);
		textView.setText("你好");
		setContentView(textView);*/
		
	}
}
