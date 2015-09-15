package com.cblue.animation;

import com.cblue.android.R;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 使用布局文件实现逐帧动画
 * @author Administrator
 * 
 */
public class FreamAnimationXML extends Activity {

	ImageView iv;
	Button btn1, btn2;
	AnimationDrawable ad;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation_frame_xml);

		iv = (ImageView) findViewById(R.id.ivfreamxml);
		btn1 = (Button) findViewById(R.id.btnstart);
		btn2 = (Button) findViewById(R.id.btnstop);
		// 得到背景动画对象
		ad = (AnimationDrawable) iv.getBackground();

		btn1.setOnClickListener(listener);
		btn2.setOnClickListener(listener);
	}

	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btnstart:
				ad.start();
				break;
			case R.id.btnstop:
				ad.stop();
				break;

			}

		}
	};

}
