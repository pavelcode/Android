package com.cblue.ui.control;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Toast;

import com.cblue.android.R;

public class RatingBarActivity extends Activity {
	
	RatingBar ratingBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.control_ratingbar);
		ratingBar = (RatingBar)findViewById(R.id.ratingbar);
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				Toast.makeText(RatingBarActivity.this, "当前评级为"+rating, Toast.LENGTH_LONG).show();
				
			}
		});
		
	}
}
