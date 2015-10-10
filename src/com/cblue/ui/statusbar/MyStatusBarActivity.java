package com.cblue.ui.statusbar;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cblue.android.R;

public class MyStatusBarActivity extends Activity
{
    private static final String TAG ="MyStatusBarActivity";
	private Button statusBarButton;
	private static final int NID= 0x7f060000;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		Log.i(TAG, "-->onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statusbar);
		statusBarButton = (Button) findViewById(R.id.stuatbarbut);
		statusBarButton.setOnClickListener(listener);
	}

	@Override
	protected void onStart()
	{
		super.onStart();
		Log.i(TAG, "-->onStart");
		NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		notificationManager.cancel(NID);

	   
	}

	private OnClickListener listener = new OnClickListener()
	{

		
		public void onClick(View v)
		{
			switch (v.getId())
			{
			case R.id.stuatbarbut:
				Intent intent = new Intent(MyStatusBarActivity.this,MyStatusBarService.class);
                startService(intent);
				break;

			default:
				break;
			}

		}

	};
}
