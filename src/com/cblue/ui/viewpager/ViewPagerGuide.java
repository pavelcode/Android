package com.cblue.ui.viewpager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * ViewPager实现引导界面效果
 * 实现自动循环
 * 
 * @author pavel
 * 
 */
public class ViewPagerGuide extends Activity implements OnPageChangeListener {
	private ViewPager vp;
	private ViewPagerGuideAdapter vpAdapter;
	private List<View> views;

	private ImageView[] dots; // 底部小点图片
	private int currentIndex; // 记录当前选中位置
	private int currentPosition=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager_guide);
		initView();
		initDots();
	}

	private void initView() {

		LayoutInflater inflater = LayoutInflater.from(this);
		views = new ArrayList<View>();
		// 初始化引导图片列表
		views.add(inflater.inflate(R.layout.viewpager_guide_step1, null));
		views.add(inflater.inflate(R.layout.viewpager_guide_step2, null));
		views.add(inflater.inflate(R.layout.viewpager_guide_step3, null));
		// 初始化Adapter
		vpAdapter = new ViewPagerGuideAdapter(views, this);
		
		vp = (ViewPager) findViewById(R.id.pager);
		vp.setAdapter(vpAdapter);
		//添加监听
		vp.setOnPageChangeListener(this);
		//vp.addOnPageChangeListener(this); 低版本已经过期
		 
		//启动一个定时任务，在Handler中修改当前显示的Item
		new Timer().schedule(new TimerTask() {		
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Log.i("aaa", "---run----");
			    handler.sendEmptyMessage(currentPosition);		
			}
		},0,2000);
	}
	
     Handler handler = new Handler(){
		
    	public void handleMessage(android.os.Message msg) {
    		int position = msg.what;
    		Log.i("aaa", "position="+position);
    		 if((++position)>2){
    			position=0;
    			Log.i("aaa", "----");
    		 } 
    	     vp.setCurrentItem(position, true);
    	};
	};
	

	private void initDots() {
		LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
		dots = new ImageView[views.size()];

		// 循环取得小点图片
		for (int i = 0; i < views.size(); i++) {
			dots[i] = (ImageView) ll.getChildAt(i);
			dots[i].setEnabled(true);// 都设为灰色
		}

		currentIndex = 0;
		dots[currentIndex].setEnabled(false);// 设置为白色，即选中状态
	}

	private void setCurrentDot(int position) {
		if (position < 0 || position > views.size() - 1
				|| currentIndex == position) {
			return;
		}
		// 当前的图片设置为白色
		dots[position].setEnabled(false);
		// 刚划过的图片设置为灰色
		dots[currentIndex].setEnabled(true);
		// 划入的图片设置为当前图片
		currentIndex = position;
	}

	// 当滑动状态改变时调用
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	// 当当前页面被滑动时调用
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	// 当新的页面被选中时调用
	@Override
	public void onPageSelected(int position) {
		// 设置底部小点选中状态
		setCurrentDot(position);
		currentPosition = position;
       
		
	}
	
    

	
	
	
	
}
