package com.cblue.animation;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class TweenAnimationJava extends Activity {
	ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation_tweenanim_java);
		
		Button btn1 = (Button) findViewById(R.id.btntweenjava1);
		Button btn2 = (Button) findViewById(R.id.btntweenjava2);
		Button btn3 = (Button) findViewById(R.id.btntweenjava3);
		Button btn4 = (Button) findViewById(R.id.btntweenjava4);
		Button btn5 = (Button) findViewById(R.id.btntweenjava5);
		
		iv = (ImageView) findViewById(R.id.img_tweenjava);
		
		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 透明度动画
				Animation anim = new AlphaAnimation(0.0f, 1.0f);
				anim.setDuration(5000);
				iv.setAnimation(anim);
			}
		});
		
		btn2.setOnClickListener(new OnClickListener() {
       
			@Override
			public void onClick(View v) {
				// TODO 缩放动画
				/*
				 * 参数1：x方向起始大小(1f表示原图大小) 参数2：x方向终止大小(0.2f表示原图的0.2倍)
				 * 参数3：y方向起始大小(1f表示原图大小) 参数4：y方向终止大小(0.2f表示原图的0.2倍)
				 */
				// Animation anim2=new
				// ScaleAnimation(0.0f,1.0f,0.0f,1.0f,0.5f,0.5f);
				Animation anim2 = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,
						0.5f, 0.5f);
				anim2.setDuration(5000);
				anim2.setRepeatCount(2);
				anim2.setStartOffset(50);
				iv.startAnimation(anim2);

			}
		});
		
		btn3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 参数1：x轴的起始位置;参数2：x轴的终止位置;参数3:y轴的起始位置;参数4：y轴的终止位置
				/*
				 * 以x轴为例介绍参照与对应值的关系:
				 * 如果选择参照为Animation.ABSOLUTE，那么对应的值应该是具体的坐标值，比如100到300
				 * ，指绝对的屏幕像素单位 如果选择参照为Animation.RELATIVE_TO_SELF或者
				 * Animation.RELATIVE_TO_PARENT指的是相对于自身或父控件，
				 * 对应值应该理解为相对于自身或者父控件的几倍或百分之多少。
				 */
				Animation anim2 = new TranslateAnimation(0, 200, 0, 0);
				anim2.setDuration(5000);
				anim2.setInterpolator(new BounceInterpolator());
				iv.startAnimation(anim2);

			}
		});
		
		btn4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*参数1：旋转的起始角度
				     参数2：旋转的终止角度
				     参数3：旋转中心的x轴取值参照方式
				     参数4：中心点x轴的取值
				    参数5：旋转中心的y轴取值参照方式
				    参数6：中心点y轴的取值
                */
				Animation animation = new RotateAnimation(360, 0,
						Animation.RELATIVE_TO_SELF, 0.5f,
						Animation.RELATIVE_TO_SELF, 0.5f);
				animation.setDuration(5000);
				animation.setInterpolator(new BounceInterpolator());
				iv.startAnimation(animation);

			}
		});
		
		//多个动画混合效果
		btn5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			   AnimationSet mAnimationSet = new AnimationSet(false);
			   
			   AlphaAnimation mAlphaAnimation = new AlphaAnimation(1.0f, 0.0f);
			   mAlphaAnimation.setDuration(10000);
			   mAnimationSet.addAnimation(mAlphaAnimation);
			   
			   RotateAnimation mRotateAnimation = new RotateAnimation(0, +350, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF);
			   mRotateAnimation.setDuration(1000);
			   mRotateAnimation.setRepeatCount(3);
			   mAnimationSet.addAnimation(mRotateAnimation);
			   
			   iv.startAnimation(mAnimationSet);
			}
		});
	}

}
