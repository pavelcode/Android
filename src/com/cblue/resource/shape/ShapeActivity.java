package com.cblue.resource.shape;

import com.cblue.android.R;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.widget.ImageView;

/**
 * 加载sharpe文件，作为背景
 * @author pavel
 *
 */
public class ShapeActivity extends Activity {

	
	private ImageView iv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shape_activity);
		iv = (ImageView)findViewById(R.id.iv);
		Drawable drawable = iv.getBackground();
		drawable.setLevel(32);
	}


}
