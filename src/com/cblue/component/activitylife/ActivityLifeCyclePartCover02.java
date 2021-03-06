package com.cblue.component.activitylife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cblue.android.R;

public class ActivityLifeCyclePartCover02 extends Activity {

	private final String TAG= ActivityLifeCyclePartCover02.class.getSimpleName();
	Button button;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_partcover2);
        // The activity is being created.
        button = (Button)findViewById(R.id.lifecycle_partcover02_btn);
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				
			}
		});
        Log.i(TAG, "--onCreate");
    }
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		 Log.i(TAG, "--onRestart");
	}
    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
        Log.i(TAG, "--onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
        Log.i(TAG, "--onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
        Log.i(TAG, "--onPause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
        Log.i(TAG, "--onStop");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
        Log.i(TAG, "--onDestroy");
    }


}
