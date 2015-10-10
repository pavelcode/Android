package com.cblue.ui.control;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

import com.cblue.android.R;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class CheckBoxActivity extends Activity
{
	private CheckBox cbJava;
	private CheckBox cbCSharp;
	private CheckBox cbPhp;
	private Button checkButton;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.control_checkbox);
		
		cbJava = (CheckBox) findViewById(R.id.cbJava);
		cbCSharp = (CheckBox) findViewById(R.id.cbCSharp);
		cbPhp = (CheckBox) findViewById(R.id.cbPhp);
		checkButton = (Button) findViewById(R.id.checkbtn);

		cbJava.setOnCheckedChangeListener(listener);
		cbCSharp.setOnCheckedChangeListener(listener);
		cbPhp.setOnCheckedChangeListener(listener);
		checkButton.setOnClickListener(btnListener);

	}
	
	

	/**
	 * 控件选择提示
	 */
	private OnCheckedChangeListener listener = new OnCheckedChangeListener()
	{ 
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		{
			Toast.makeText(CheckBoxActivity.this, buttonView.getText().toString(), Toast.LENGTH_SHORT).show();
		}
	};

	/**
	 * 控件提交提示
	 */
	private OnClickListener btnListener = new OnClickListener()
	{

		public void onClick(View v)
		{
            StringBuilder sBuilder = new StringBuilder();
            if(cbJava.isChecked())
            {
            	sBuilder.append(cbJava.getText().toString()+",");
            }
            if(cbCSharp.isChecked())
            {
            	sBuilder.append(cbCSharp.getText().toString()+",");
            }
            if(cbPhp.isChecked())
            {
            	sBuilder.append(cbPhp.getText().toString()+",");
            }
            Toast.makeText(CheckBoxActivity.this, "爱好是："+sBuilder.toString(), Toast.LENGTH_SHORT).show();
		}
	};
	
	
	/**
	 * xml控制控件选择提示
	 */
	public void onCheckboxClicked(View view){
	  	boolean isCheck = ((CheckBox)view).isChecked();
	  	switch (view.getId()) {
		case R.id.cbJava:
			if(isCheck){
	  			Toast.makeText(CheckBoxActivity.this, "java被选中", Toast.LENGTH_LONG).show();
	  		}
			break;
		case R.id.cbCSharp:
			if(isCheck){
	  			Toast.makeText(CheckBoxActivity.this, "c#被选中", Toast.LENGTH_LONG).show();
	  		}
			break;
		case R.id.cbPhp:
			if(isCheck){
	  			Toast.makeText(CheckBoxActivity.this, "php被选中", Toast.LENGTH_LONG).show();
	  		}
			break;		
		}
	}
}