package com.cblue.ui.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.cblue.android.R;

/**
 * 开机动画
 * 透明度+旋转+动画监听
 * @author Administrator
 *
 */
public class BootAnimation extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation_boot);
		ImageView iv = (ImageView) findViewById(R.id.ivboot);
		//透明度
		AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f,1.0f);
		alphaAnimation.setDuration(2000);
		alphaAnimation.setRepeatCount(3);
		
		//旋转动画
		RotateAnimation rotateAnimation = new RotateAnimation(0, +300,Animation.RELATIVE_TO_SELF,Animation.RELATIVE_TO_SELF);
		rotateAnimation.setDuration(3000);
		rotateAnimation.setRepeatCount(5);
		//添加动画集合中
		AnimationSet animationSet = new AnimationSet(false);
		animationSet.addAnimation(alphaAnimation);
		animationSet.addAnimation(rotateAnimation);
		//startAnimation比setAnimation更加通用
		iv.startAnimation(animationSet);
		//添加动画监听器
		animationSet.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				
				//跳转
				
			}
		});
	    
	   
	    
	    
	}
   
}
