package com.cblue.touch;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;

/**
 * 多点触碰拖动图片
 * 
 * @author Administrator
 * 
 */
public class MultiPointTouchDragImageActivity extends Activity {

	private FrameLayout mFrameLayout;
	private ImageView mImageView;
	public static final String TAG = MultiPointTouchDragImageActivity.class.getSimpleName();
	float currentDistance = 0;//两根手指拖动之后当前的距离
	float dragDistance =-1;  // 两根手指拖动之前的距离

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.touch_event_dragimage);
		mFrameLayout = (FrameLayout) findViewById(R.id.touchevent_drag_fl);
		mImageView = (ImageView) findViewById(R.id.touchevent_drag_iv);

		mFrameLayout.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action = event.getAction();				
				switch (action) {
				case MotionEvent.ACTION_MOVE:
					//如果两根手指大于等于2根
					if (event.getPointerCount() >= 2) {
						// 两根手指之间X轴的距离
						float distanceX = event.getX(0) - event.getX(1);
						// 两根手指之间Y轴的距离
						float distanceY = event.getY(0) - event.getY(1);
						//两根手指之间的直线距离
						currentDistance = (float) Math.sqrt(distanceX
								* distanceX + distanceY * distanceY);
						Log.i(TAG,"currentDistance="+currentDistance);
						//没有拖动的时候，让当前距离和拖动距离一致
						Log.i(TAG,"dragDistance="+dragDistance);
						//设置dragDistance记录手指
					    if(dragDistance<0){
							dragDistance = currentDistance;	
						}else{
							if(dragDistance-currentDistance>5){
								Log.i(TAG, "图片放大");
								//保存手指结束的距离
								dragDistance = currentDistance;
								//让图片按照1.1倍的方法
								LayoutParams layoutParams = (LayoutParams) mImageView.getLayoutParams();
								layoutParams.width =  (int) (1.1f*mImageView.getWidth());
								layoutParams.height = (int) (1.1f*mImageView.getHeight());
								mImageView.setLayoutParams(layoutParams);
							}else if(currentDistance-dragDistance>5){
								Log.i(TAG, "图片缩小");
							    dragDistance = currentDistance;
								LayoutParams layoutParams = (LayoutParams) mImageView.getLayoutParams();
								layoutParams.width =  (int) (0.9f*mImageView.getWidth());
								layoutParams.height = (int) (0.9f*mImageView.getHeight());
								mImageView.setLayoutParams(layoutParams);
							}							
						}
					}
					break;
				}
				return true;
			}
		});

	}
}
