package com.cblue.ui.event;

import android.app.Activity;
import android.location.GpsStatus.Listener;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TouchEventActivity extends Activity implements OnTouchListener
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	    LinearLayout linearLayout = new LinearLayout(this);
	    linearLayout.setOnTouchListener(this);
	    setContentView(linearLayout);
	    
	}
	public boolean onTouch(View v, MotionEvent event)
	{
       Toast.makeText(TouchEventActivity.this,"touch事件",Toast.LENGTH_LONG).show();
		return true;
	}

	
}
