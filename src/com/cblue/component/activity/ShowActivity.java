package com.cblue.component.activity;

import com.cblue.android.R;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ShowActivity extends Activity
{

	private TextView nameTextView;
	private TextView pwdTextView;
	private TextView sexTextView;
	private Button sexChoiseButton;
	private final int requestCode1 = R.id.btn;
    
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_main);
		
		nameTextView = (TextView)findViewById(R.id.name);
		pwdTextView = (TextView)findViewById(R.id.pwd);
		sexTextView = (TextView)findViewById(R.id.sex);
		
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        nameTextView.setText(bundle.getCharSequence("name"));
        pwdTextView.setText(bundle.getCharSequence("passwd"));
       
    	sexChoiseButton = (Button)findViewById(R.id.sexbtn);
		sexChoiseButton.setOnClickListener(listener);
	}
	
	private OnClickListener listener = new OnClickListener()
	{
		public void onClick(View v)
		{
			Intent intent = new Intent(ShowActivity.this, SexChoiseActivity.class);
			startActivityForResult(intent, requestCode1);
		}
	};
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==requestCode1&&resultCode==requestCode1)
		{
			Bundle bundle = data.getExtras();
			sexTextView.setText(bundle.getCharSequence("sex"));
		}
		
		
	}
}
