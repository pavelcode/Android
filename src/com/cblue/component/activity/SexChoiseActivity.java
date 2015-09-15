package com.cblue.component.activity;

import com.cblue.android.R;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SexChoiseActivity extends Activity
{

	private RadioGroup sexGroup;
	private final int resultCode = R.id.btn;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_sex);
		sexGroup = (RadioGroup)findViewById(R.id.loginsex);
		sexGroup.setOnCheckedChangeListener(listener);
	}
	private OnCheckedChangeListener listener = new OnCheckedChangeListener()
	{
		public void onCheckedChanged(RadioGroup group, int checkedId)
		{
			RadioButton radioButton = (RadioButton)findViewById(checkedId);
			Intent intent = getIntent();
			Bundle bundle = new Bundle();
			bundle.putCharSequence("sex", radioButton.getText().toString());
			intent.putExtras(bundle);
			setResult(resultCode, intent);
			finish();
		}
	};
   
}
