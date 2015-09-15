package com.cblue.component.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cblue.android.R;

/**
 * 加法返回值
 * 思路：
 * Activity1 输入加数和被加数，点击按钮把数据和被加数传递给Activity2（startActivityForResult）
 * Activity2 得到信息，显示出来。把结果输出，点击按钮，把结果返回给Activity1（setResult）
 * Activity1 得到返回值，显示出来（onActivityResult）
 * 
 * 
 * @author Administrator
 *
 */

public class ForReturnActivityDemo01 extends Activity {
	
	private EditText editText1;
	private EditText editText2;
	private TextView resultEditText;
	private Button btn1;
	private final String TAG=ForReturnActivityDemo01.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_foreturn1);
		
		editText1 = (EditText)findViewById(R.id.editText1);
		editText2 = (EditText)findViewById(R.id.editText2);
		resultEditText = (TextView)findViewById(R.id.textView3);
		btn1 = (Button)findViewById(R.id.button1);
		
		btn1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ForReturnActivityDemo01.this,ForReturnActivityDemo02.class);
				String input1 = editText1.getText().toString().trim();
				String input2 = editText2.getText().toString().trim();
				intent.putExtra("message", input1+" + "+input2+" = ");
				//使用Bundle方式发送数据
			/*	Bundle bundle = new Bundle();
				bundle.putString("message",input1+" + "+input1+" = ");
				intent.putExtra("bundle", bundle);*/
				
				startActivityForResult(intent, 1000);
			}
		});
		
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode==1000&&resultCode==1001){
			Log.i(TAG, requestCode+"=="+resultCode+"=="+data.getStringExtra("result"));
			String result = data.getStringExtra("result");
			Log.i(TAG, result);
			resultEditText.setText(result);
		}
		
	}
	
	
}
