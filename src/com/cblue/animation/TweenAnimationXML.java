package com.cblue.animation;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 补间动画xml控制
 * @author Administrator
 *
 */
public class TweenAnimationXML extends Activity {
	ImageView iv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation_tweenanim_xml);
         
		 Button btn1	= (Button) findViewById(R.id.btntweenxml1);
         Button btn2	= (Button) findViewById(R.id.btntweenxml2);
         Button btn3	= (Button) findViewById(R.id.btntweenxml3);
         Button btn4	= (Button) findViewById(R.id.btntweenxml4);
         Button btn5	= (Button) findViewById(R.id.btntweenxml5);
         
         iv = (ImageView) findViewById(R.id.img_tweenxml);
         
         //透明度动画
         btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Animation anim = AnimationUtils.loadAnimation(TweenAnimationXML.this,R.anim.animation_alpha_test);
				//iv.setAnimation(anim); 这种方法在Genymotion中，动画不管用，下面的方法通用
				//anim.startNow();
				//或者
				iv.startAnimation(anim);
			}
		});
         //缩放动画
         btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Animation anim = AnimationUtils.loadAnimation(TweenAnimationXML.this,R.anim.animation_scale_test);
				iv.startAnimation(anim);
			}
		});
         //位移动画
         btn3.setOnClickListener(new OnClickListener() {
 			
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				Animation anim = AnimationUtils.loadAnimation(TweenAnimationXML.this,R.anim.animation_translate_test);
 				iv.startAnimation(anim);
 			}
 		});
         //旋转动画
         btn4.setOnClickListener(new OnClickListener() {
  			
  			@Override
  			public void onClick(View v) {
  				// TODO Auto-generated method stub
  				Animation anim = AnimationUtils.loadAnimation(TweenAnimationXML.this,R.anim.animation_rotate_test);
  				iv.startAnimation(anim);
  			}
  		});
         //透明度+旋转动画
         btn5.setOnClickListener(new OnClickListener() {
   			
   			@Override
   			public void onClick(View v) {
   				// TODO Auto-generated method stub
   				Animation anim = AnimationUtils.loadAnimation(TweenAnimationXML.this,R.anim.animation_group_test);
   				iv.startAnimation(anim);
   			}
   		});
	}
  
}
