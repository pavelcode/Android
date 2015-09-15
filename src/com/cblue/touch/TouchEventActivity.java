package com.cblue.touch;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;

/**
 *  触摸事件类型
 *  返回值为true，
 * @author Administrator
 *
 */
public class TouchEventActivity extends Activity {

	private FrameLayout mFrameLayout;
	public static final String TAG = TouchEventActivity.class.getSimpleName();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.touch_event);
		mFrameLayout = (FrameLayout) findViewById(R.id.touchevent_fl);
		mFrameLayout.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				int action = event.getAction();
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					Log.i(TAG, "按下");
					break;
				case MotionEvent.ACTION_MOVE:
					Log.i(TAG, "移动");
					Log.i(TAG, String.format("x %f ，y %f", event.getX(),event.getY()));
					break;
				case MotionEvent.ACTION_UP:
					Log.i(TAG, "抬起");
					break;

				
				}
			//	return false;
				return true;
			}
		});
	}

	
}
