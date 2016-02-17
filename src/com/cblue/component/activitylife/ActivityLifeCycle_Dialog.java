package com.cblue.component.activitylife;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.cblue.android.R;

/**
 * Activity中弹出一个Dialog对生命周期的影响
 * @author pavel
 *
 */
public class ActivityLifeCycle_Dialog extends Activity {

	private final String TAG= ActivityLifeCycle_Dialog.class.getSimpleName();
	
	Button button ;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_dialog);
        button = (Button)findViewById(R.id.activity_lifecycle_dialogbtn);
        button.setOnClickListener(new android.view.View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(ActivityLifeCycle_Dialog.this);
		        builder.setTitle("下载");
		        builder.setIcon(R.drawable.ic_launcher);
		        builder.setMessage("要下载吗?");
		        builder.setNegativeButton("取消", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
					
				
				});
		        builder.setPositiveButton("确定", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
		   
		        builder.show();
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
