package com.cblue.ui.animation;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cblue.android.R;

/**
 * 布局动画
 * @author Administrator
 *
 */
public class LayoutAnimation extends Activity {
	
	
	private ListView layoutAnimationListView;
	  @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation_layout);
		layoutAnimationListView = (ListView)findViewById(R.id.layout_animation_lv);
		List<String> news = new ArrayList<String>();
		for(int i=0;i<20;i++){
			news.add("news"+i);
		}
		ArrayAdapter<String> arrayAdpater = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, news);
		layoutAnimationListView.setAdapter(arrayAdpater);
		//创建布局动画控制器
		LayoutAnimationController mLayoutAnimationController = new LayoutAnimationController(AnimationUtils.loadAnimation(this, R.anim.animation_layout));
		//排序方式
		mLayoutAnimationController.setOrder(LayoutAnimationController.ORDER_RANDOM);
		//给ListView设置布局动画
		layoutAnimationListView.setLayoutAnimation(mLayoutAnimationController);
		//开始布局动画
		layoutAnimationListView.startLayoutAnimation();
		
	}

}
