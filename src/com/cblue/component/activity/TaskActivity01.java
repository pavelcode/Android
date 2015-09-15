package com.cblue.component.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cblue.android.R;

public class TaskActivity01 extends Activity {

	
	Button button ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task1);
		button = (Button)findViewById(R.id.task_btn01);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(TaskActivity01.this, TaskActivity02.class);
				startActivity(intent);
				
			}
		});
	}
}
