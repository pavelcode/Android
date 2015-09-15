package com.cblue.savedata.sharedpreferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cblue.android.R;

/**
 * SharedPreferences的简单应用1：简单的注册功能
 * 简单的保存数据，并读取数据
 * @author Administrator
 *
 */
public class SharedPreferencesActivity1 extends Activity
{
	private EditText editName;
	private EditText editAge;
	private Button btnSave;
	private Button btnShow;
	private TextView txtResult;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharepreference_register);
		
		editName = (EditText) findViewById(R.id.editName);
		editAge = (EditText) findViewById(R.id.editAge);
		btnSave = (Button) findViewById(R.id.btnSave);
		btnShow = (Button) findViewById(R.id.btnShow);
		txtResult=(TextView)findViewById(R.id.txtResult);
		
		btnSave.setOnClickListener(listener);
		btnShow.setOnClickListener(listener);
	}

	private OnClickListener listener = new OnClickListener()
	{
		
		public void onClick(View v)
		{
			SharedPreferences pres = SharedPreferencesActivity1.this.getSharedPreferences("myinfo", Context.MODE_PRIVATE);
			switch (v.getId())
			{
			case R.id.btnSave:
				Editor editor = pres.edit();
				editor.putString("name", editName.getText().toString());
				editor.putInt("age", Integer.valueOf(editAge.getText().toString()));
				editor.commit();
				Toast.makeText(SharedPreferencesActivity1.this, "保存成功", Toast.LENGTH_LONG).show();
				break;
				
			case R.id.btnShow:
				String name = pres.getString("name", "NO");
				int age = pres.getInt("age", 0);
				txtResult.setText("Name="+name+";age="+age);
				break;
			}
		}
	};
}