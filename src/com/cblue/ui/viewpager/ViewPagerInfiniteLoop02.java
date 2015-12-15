package com.cblue.ui.viewpager;

import java.util.ArrayList;
import java.util.List;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

/**
 * 2 0 1 2 0
 * 给ViewPager前后多添加两个View，当跳转到第一个，就跳转到末尾图片，当跳转到最后一个，就跳转到首图片
 * @author pavel
 *
 */
public class ViewPagerInfiniteLoop02 extends Activity implements OnPageChangeListener {
	
	private ViewPager viewPager;
	private List<ImageView> views;
	private int pic[]={R.drawable.a,R.drawable.c,R.drawable.d};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager_infiniteloop);
		viewPager = (ViewPager) findViewById(R.id.viewpager_infiniteloop);
		initImageView();
		viewPager.setAdapter(new ViewPagerAdapter());
		viewPager.setOnPageChangeListener(this);
		viewPager.setCurrentItem(1);
	}


	private void initImageView() {
		// TODO Auto-generated method stub
		views = new ArrayList<ImageView>();
		int length = pic.length+2;//给ViewPager添加两个View空间
		for(int i=0;i<length;i++){
			ImageView imageView = new ImageView(this);
			ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		    imageView.setLayoutParams(layoutParams);
		    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		    views.add(imageView);
		}
		
	}
	
	class ViewPagerAdapter extends PagerAdapter{

		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			Log.i("aaa",position+"");
			if(position==0){
				//如果是第一个位置，默认选择第三张图片
				views.get(position).setImageResource(pic[2]);
			}else if(position==(views.size()-1)){
				//如果是最后一个位置，默认选择第一张图片
				views.get(position).setImageResource(pic[0]);
			}else {
				views.get(position).setImageResource(pic[position-1]);
			}
			container.addView(views.get(position));	
			return views.get(position);
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			//super.destroyItem(container, position, object);
			ImageView view = views.get(position);
			container.removeView(view);
			view.setImageBitmap(null);
			
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return views.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
		}
		
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
		//将要跳转的ViewPager的索引
		int pageIndex = position;
		Log.i("aaa","position="+position);
		if(position==0){
			//当前在第一个位置,需要跳转的最后一个图片
			pageIndex = pic.length;
		}else if(position==pic.length+1){
			//当前是最后一个位置，需要跳转到第一张图片
			pageIndex = 1;
		}
		//如果是边界跳转
		if(position!=pageIndex){
			viewPager.setCurrentItem(pageIndex, false);
			return;
		}
		
	}

}
