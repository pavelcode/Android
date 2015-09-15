package com.cblue.control;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cblue.android.R;

public class EditTextActivity extends Activity {
	
	private EditText editText;
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.control_editext);
		/*setContentView(R.layout.control_editext2);
		editText = (EditText)findViewById(R.id.ET_ET1);
		button = (Button)findViewById(R.id.BTN_ET1);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			  String content = editText.getText().toString().trim();
			  if(content==null||"".equals(content)){
				 editText.setError("内容不能为空");
			  }else{
			  Toast.makeText(EditTextActivity.this, "content="+content, Toast.LENGTH_LONG).show();
			  }
			}
		});*/
		
	//
/*	editText.setOnKeyListener(new OnKeyListener() {
		
		public boolean onKey(View v, int keyCode, KeyEvent event) {
	        if(keyCode==KeyEvent.KEYCODE_ENTER){
	        	Toast.makeText(EditTextActivity.this, "回车键被点击", Toast.LENGTH_LONG).show();
	        }
			return false;
		}
	});*/
		
		
	}
}
