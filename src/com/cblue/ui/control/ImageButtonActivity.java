package com.cblue.ui.control;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

import com.cblue.android.R;

public class ImageButtonActivity extends Activity {
	
	ImageButton imageButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.control_imagebutton);
		imageButton = (ImageButton)findViewById(R.id.control_imagebutton01);
		imageButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(ImageButtonActivity.this, "imagebutton被点击了", Toast.LENGTH_LONG).show();
			}
		});
	}
}
