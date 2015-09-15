package com.cblue.customerview;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class CustomerViewActivity extends Activity {
	
	
	private CustomerView mCustomerView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customerview);
		mCustomerView = (CustomerView)findViewById(R.id.customerView1);
		mCustomerView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(CustomerViewActivity.this, "自定义控件被点击",1).show();
			}
		});
		
		
	}

}
