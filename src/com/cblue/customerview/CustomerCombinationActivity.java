package com.cblue.customerview;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.view.View.OnClickListener;import android.widget.TextView;
import android.widget.Button;

public class CustomerCombinationActivity extends Activity {

	CustomerCombination mCombination1;
	Button btn1;
	TextView textView1,textView2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customercombinationactivity);
		mCombination1 = (CustomerCombination)findViewById(R.id.customerCombination1);
		btn1 =(Button) mCombination1.findViewById(R.id.c_button1);
		textView1 = (TextView)mCombination1.findViewById(R.id.c_textView1);
		textView2 = (TextView)mCombination1.findViewById(R.id.c_textView2);
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				textView1.setText("aaa");
				textView2.setText("bbb");
			}
		});
	}
}
