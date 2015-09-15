package com.cblue.control;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

import com.cblue.android.R;

public class ImageSwitchActivity extends Activity {

	private Button previousBtn;
	private Button nextBtn;
	private ImageSwitcher imageSwitcher;
	private int images[] = { R.drawable.a, R.drawable.c,
			R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.h };
	int count = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageswitch);
		imageSwitcher = (ImageSwitcher) findViewById(R.id.imageswitch);
		previousBtn = (Button) findViewById(R.id.previous);
		nextBtn = (Button) findViewById(R.id.next);
		
		previousBtn.setOnClickListener(listener);
		nextBtn.setOnClickListener(listener);
		
		imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
		imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));
		imageSwitcher.setFactory(new ViewFactory() {
			public View makeView() {
				ImageView image = new ImageView(getApplication());
				image.setScaleType(ImageView.ScaleType.FIT_CENTER);
				image.setLayoutParams(new ImageSwitcher.LayoutParams(
						LayoutParams.FILL_PARENT, LayoutParams.MATCH_PARENT));
				return image;
			}
		});
		imageSwitcher.setImageResource(images[count]);
	}

	private OnClickListener listener = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.previous:
				if (count - 1 < 0) {
					count = images.length - 1;
				} else {
					count = count - 1;
				}
				imageSwitcher.setImageResource(images[count]);
				break;
			case R.id.next:
				if (count+1 > images.length-1) {
					count = 0;
				} else {
					count = count+1;
				}
				imageSwitcher.setImageResource(images[count]);
				break;
			}
		}

	};
}
