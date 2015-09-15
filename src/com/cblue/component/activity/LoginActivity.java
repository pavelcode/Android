package com.cblue.component.activity;

import com.cblue.android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * 
 * 一个综合的例子：
 * LoginActivity输入用户名和密码登陆进入ShowActivity，显示用户名和密码
 * 下面一个按钮选择性别，跳转到SexActivity，选择性别，把性别的结果显示在ShowAcitivity
 * 
 * @author Administrator
 *
 */
public class LoginActivity extends Activity
{
	private EditText loginName;
	private EditText loginPassword;
	private Button  submitButton;
	private final int requestCode = R.id.btn;
	
	private String name;
	private String passwd;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_table2);
		
	    loginName = (EditText)findViewById(R.id.loginet);
	    loginPassword = (EditText)findViewById(R.id.passwdet);
	    submitButton = (Button) findViewById(R.id.loginbtn);
	    
	    submitButton.setOnClickListener(listener);
	}
	private OnClickListener listener = new OnClickListener()
	{
		public void onClick(View v)
		{
           	name = loginName.getText().toString();
           	passwd = loginPassword.getText().toString();
           	Bundle  bundle = new Bundle();
           	bundle.putCharSequence("name",name);
           	bundle.putCharSequence("passwd",passwd);
           	Intent intent = new Intent(LoginActivity.this,ShowActivity.class);
           	intent.putExtras(bundle);
           	startActivity(intent);
		}
	};
	
}
