package com.cblue.ui.customerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;

public class TuYaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		
		TuYaView tuYaView = new TuYaView(TuYaActivity.this,metrics.widthPixels,metrics.heightPixels);
	    setContentView(tuYaView);
	}

	
}
