package com.cblue.waterfall;



import android.app.Activity;
import android.os.Bundle;

import com.cblue.android.R;

/**
 * 
 * 第一种方式实现垂直瀑布流
 * 问题：会出现崩溃的问题，判断是图片没有经过二次处理
 * 优点：代码写的符合面向对象的思想
 * @author Administrator
 *
 */
public class Vertical01_WaterFall_Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vertica01_waterfall);
		
		Vertical01_WaterFall waterFall = (Vertical01_WaterFall) findViewById(R.id.waterfall);
		waterFall.setup();
	}

}
