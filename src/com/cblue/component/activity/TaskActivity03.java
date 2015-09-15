package com.cblue.component.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cblue.android.R;

public class TaskActivity03 extends Activity {

	Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task3);
		button = (Button)findViewById(R.id.task_btn03);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_SENDTO,Uri.parse("sms://110"));
				intent.putExtra("sms_body","aaaa");
				startActivity(intent);
			}
		});
	}
}
