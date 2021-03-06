package com.cblue.component.activitylife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cblue.android.R;

/**
 * 全部掩盖Activity的生命周期
 * @author pavel
 *
 */
public class ActivityLifeCycleAllCover01 extends Activity {

	private final String TAG= ActivityLifeCycleAllCover01.class.getSimpleName();
	private Button btn ;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_allcover1);
        btn = (Button)findViewById(R.id.lifecycleBtn);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent intent = new Intent(ActivityLifeCycleAllCover01.this,ActivityLifeCycleAllCover02.class);
			startActivity(intent);
			}
		});
        
        // The activity is being created.
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
