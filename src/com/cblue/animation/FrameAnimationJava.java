package com.cblue.animation;

import com.cblue.android.R;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 使用代码实现逐帧动画
 * @author Administrator
 *
 */
public class FrameAnimationJava extends Activity {

	ImageView iv;
	Button btn1, btn2;
	AnimationDrawable ad;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation_frame_java);
		iv = (ImageView) findViewById(R.id.frame_anim_img);
		btn1 = (Button) findViewById(R.id.frame_anim_btn01);
		btn2 = (Button) findViewById(R.id.frame_anim_btn02);

		ad = new AnimationDrawable();
		// 通过循环构造AnimationDrawable对象
		for (int i = 1; i <= 8; i++) {
			// 得到R文件中的资源ID，需要知道R文件所在的包名，R文件中的类，这个资源的标示符
			int resID = getResources().getIdentifier("animationbao" + i,
					"drawable", "com.example.animationdemo");
			// 根据资源ID，得到资源对象，相当于 findViewById
			Drawable drawable = getResources().getDrawable(resID);
			// 把每个资源添加到动画对象之中，形成逐帧动画
			ad.addFrame(drawable, 200);
		}
		// setOneShot(boolean flag) 可以设置动画是否播放一次，false为连续播放
		ad.setOneShot(false);
		// 图片控件设置动画对象
		iv.setImageDrawable(ad);

		btn1.setOnClickListener(listener);
		btn2.setOnClickListener(listener);
	}

	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.frame_anim_btn01:
				ad.start();
				break;
			case R.id.frame_anim_btn02:
				ad.stop();
				break;

			}

		}
	};

}
