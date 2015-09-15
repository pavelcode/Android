package com.cblue.control;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ScrollViewHorizantalActivity extends Activity {
	
	private LinearLayout mLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scrollviewhorizantal);
		mLayout = (LinearLayout) findViewById(R.id.svh_ll);
		
		for(int i=0;i<10;i++){
			ImageView imageView = new ImageView(ScrollViewHorizantalActivity.this);
			imageView.setImageResource(R.drawable.ic_launcher);
			mLayout.addView(imageView);
		}
		
	}

}
