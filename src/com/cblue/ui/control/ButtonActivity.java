package com.cblue.ui.control;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.cblue.android.R;

public class ButtonActivity extends Activity {

	Button button;

	Button button01;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.control_button);
		button = (Button) findViewById(R.id.control_button04);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				button.setText("点过了");
				button.setTextSize(50);
				button.setTextColor(Color.BLUE);
			}
		});

		button01 = (Button) findViewById(R.id.control_button05);
		
		
		

		//第一种方式 匿名内部类
		button01.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(ButtonActivity.this,"我被点了" , Toast.LENGTH_LONG).show();
				
			}
		});
		
		//第二种方式 内部类
	
		 OnClickListener listener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(ButtonActivity.this,"我被点了" , Toast.LENGTH_LONG).show();
			}
		};
		button01.setOnClickListener(listener);
		
		//第三种 代码实现控件
		/*LinearLayout layout = new LinearLayout(this);
		super.setContentView(layout);
		layout.setOrientation(LinearLayout.VERTICAL);
		
		Button button = new Button(this);
		button.setText("button01");
		button.setLayoutParams(new LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)));
		
		layout.addView(button);*/
		

	}

	
}
