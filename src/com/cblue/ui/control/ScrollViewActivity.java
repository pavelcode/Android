package com.cblue.ui.control;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cblue.android.R;

public class ScrollViewActivity extends Activity {
	
	private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.scrollview);
    	linearLayout = (LinearLayout)findViewById(R.id.SVLine);
    	for(int i=0;i<10;i++){
    		ImageView imageView = new ImageView(ScrollViewActivity.this);
    		//Drawable drawable = getResources().getDrawable(R.)
    		imageView.setImageResource(R.drawable.a);
    		linearLayout.addView(imageView);
    	}
    }

}
