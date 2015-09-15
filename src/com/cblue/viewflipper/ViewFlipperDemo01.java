package com.cblue.viewflipper;



import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

/**
 * 使用touch事件，控制ViewFlipper的视图切换
 * @author Administrator
 *
 */
public class ViewFlipperDemo01 extends Activity {

	ViewFlipper vflipper;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewflipper_main);

		vflipper = (ViewFlipper) findViewById(R.id.view_flipper);

	}

	// 触摸屏幕的时候X轴的坐标
	float startX = 0;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		// 触碰的动作类型
		int action = event.getAction();
		switch (action) {
         //手指按下事件
		case MotionEvent.ACTION_DOWN:
              //记录下按下的X轴的位置
			  startX = event.getX();
			break;
         //手指抬起事件
		case MotionEvent.ACTION_UP:
			//判断按下的位置和抬起的位置的关系，判断是左滑还是右滑  
			//向右滑动
			if(startX<event.getX()){
			     //设置滑动动画
				vflipper.setInAnimation(this, R.anim.viewflipper_slide_in_left);
				vflipper.setOutAnimation(this, R.anim.viewflipper_slide_out_right);
				//显示上一个视图
				vflipper.showPrevious();
				
			}else if(startX>event.getX()){
				//向左滑动
				  //设置滑动动画
				vflipper.setInAnimation(this, R.anim.viewflipper_slide_in_right);
				vflipper.setOutAnimation(this, R.anim.viewflipper_slide_out_left);
				//显示下一个视图
				vflipper.showNext();
			}

			break;

		}

		return super.onTouchEvent(event);
	}

}