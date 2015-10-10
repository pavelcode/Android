package com.cblue.ui.touch;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;

/**
 * 得到触摸的手指数
 * 得到触摸的多根手指的位置
 * @author Administrator
 *
 */
public class MulitPointTouchActivity extends Activity{

	
	protected static final String TAG = MulitPointTouchActivity.class.getSimpleName();
	private FrameLayout mFrameLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.touch_event);
		mFrameLayout =(FrameLayout)findViewById(R.id.touchevent_fl);
		mFrameLayout.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action = event.getAction();
				switch (action) {
				case MotionEvent.ACTION_MOVE:
					//Log.i(TAG, "触摸的手指数是："+event.getPointerCount());
					//Log.i(TAG, String.format("x1 %f,y1 %f,x2 %f,y2  %f",event.getX(0),event.getY(0),event.getX(1),event.getY(1)));
					if(event.getPointerCount()>=2){
					  Log.i(TAG, String.format("x1 %f ，y1 %f, x2 %f, y2 %f", event.getX(0),event.getY(0),event.getX(1),event.getY(1)));
					}
					break;

			
				}
				return true;
			}
		});
		
	}
}
