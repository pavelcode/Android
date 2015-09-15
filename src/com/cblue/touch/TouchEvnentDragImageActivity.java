package com.cblue.touch;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;

/**
 * 单点：手指拖动图片移动
 * @author Administrator
 *
 */
public class TouchEvnentDragImageActivity extends Activity {
	
	private FrameLayout mFrameLayout;
	private ImageView mImageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.touch_event_dragimage);
		mImageView = (ImageView) findViewById(R.id.touchevent_drag_iv);
		mFrameLayout  = (FrameLayout) findViewById(R.id.touchevent_drag_fl);
		mFrameLayout.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action = event.getAction();
				switch (action) {
				case MotionEvent.ACTION_MOVE:
				//得到ImageView在整个布局中的布局参数
				LayoutParams layoutParam= 	(LayoutParams) mImageView.getLayoutParams();
				//把当前的触摸的X位置设置给布局参数 
				layoutParam.leftMargin= (int) event.getX();			
				layoutParam.topMargin = (int) event.getY();
				mImageView.setLayoutParams(layoutParam);
					break;
				}
				return true;
			}
		});
		
	}
	
	

}
