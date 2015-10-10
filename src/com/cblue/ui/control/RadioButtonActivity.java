package com.cblue.ui.control;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.cblue.android.R;

public class RadioButtonActivity extends Activity
{
	private RadioGroup radioGroup;
	private RadioButton radioButton;
	private Button btn;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.control_radiogroup);
		radioGroup = (RadioGroup) findViewById(R.id.rgGender);
		
		/**
		 * 控件点击
		 */
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
			public void onCheckedChanged(RadioGroup group, int checkedId)
			{
				//radioButton = (RadioButton) findViewById(checkedId);
				//Toast.makeText(RadioButtonActivity.this, "被点击" + radioButton.getText(), Toast.LENGTH_SHORT).show();
				switch (checkedId) {
				case R.id.rbBoy:
					Toast.makeText(RadioButtonActivity.this, "男孩选中",Toast.LENGTH_LONG).show();
					break;

				case R.id.rbGirl:
					 Toast.makeText(RadioButtonActivity.this, "女孩选中",Toast.LENGTH_LONG).show();
					break;
				}
			}
		});
		
		
	/**
	 * 控件提交
	 */
		btn = (Button) findViewById(R.id.rediobtn);
		btn.setOnClickListener(new OnClickListener(){
			public void onClick(View v)
			{ 
				//循环RadioGroup的子类，得到每个radioButton，判断选中的radioButton
				for (int i = 0; i < radioGroup.getChildCount(); i++)
				{
					radioButton = (RadioButton) radioGroup.getChildAt(i);
					if (radioButton.isChecked())
					{
						Toast.makeText(RadioButtonActivity.this, "选中" + radioButton.getText(), Toast.LENGTH_SHORT).show();
					    break;
					}
				}
			}
		});
	}
}