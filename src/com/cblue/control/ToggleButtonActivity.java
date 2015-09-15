package com.cblue.control;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ToggleButtonActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.control_toggle);
	}
	
	public void onToggleClicked(View view){
		boolean isChecked = ((ToggleButton)view).isChecked();
		if(isChecked){
			Toast.makeText(ToggleButtonActivity.this, "开关打开", Toast.LENGTH_LONG).show();
		}else{
			Toast.makeText(ToggleButtonActivity.this, "开关关闭", Toast.LENGTH_LONG).show();
		}
	}
	
	public void onSwitchClicked(View view){
		boolean isChecked = ((Switch)view).isChecked();
		if(isChecked){
			Toast.makeText(ToggleButtonActivity.this, "开关打开", Toast.LENGTH_LONG).show();
		}else{
			Toast.makeText(ToggleButtonActivity.this, "开关关闭", Toast.LENGTH_LONG).show();
		}
	}

}
