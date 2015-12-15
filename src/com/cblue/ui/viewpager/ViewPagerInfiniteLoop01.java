package com.cblue.ui.viewpager;

import java.util.ArrayList;
import java.util.List;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * 设置ViewPager有无限大的值，当一个值累加的时候，它的余数是图片个数的范围（0到图片个数的累加）
 * @author pavel
 *
 */
public class ViewPagerInfiniteLoop01 extends Activity {

	private ViewPager mViewPager;
	private int [] images ={R.drawable.a,R.drawable.c,R.drawable.d,R.drawable.f};
	private List<ImageView> views;  //保存所有图片控件
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager_infiniteloop);
		mViewPager = (ViewPager) findViewById(R.id.viewpager_infiniteloop);
		initImageViews();
		
		mViewPager.setAdapter(new ViewPagerAdapter());
		mViewPager.setCurrentItem(500);
		
	}


	/**
	 * 初始化图片的ImageView视图
	 */
	private void initImageViews() {
		// TODO Auto-generated method stub
		views = new ArrayList<ImageView>();
		for(int i=0;i<images.length;i++){
			ImageView imageView = new ImageView(this);
			ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
			imageView.setLayoutParams(layoutParams);
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			imageView.setImageResource(images[i]);
			views.add(imageView);
		}
	}
	
	class ViewPagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
		}
		
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			Log.i("aaa", "Integer.MAX_VALUE="+Integer.MAX_VALUE);
			Log.i("aaa", "position="+position);
			Log.i("aaa", "views.size()="+views.size());
			Log.i("aaa", "position%views.size()="+position%views.size());
			//position%views.size()  求余，得到被除数范围的递增值
			//避免一个控件有多个父控件
			//得到该控件的父控件，如果父控件不为空，说明该控件已经有父控件，移除当前父控件的控件，重新添加子控件
			if(views.get(position%views.size()).getParent()!=null){
				((ViewPager)views.get(position%views.size()).getParent()).removeView(views.get(position%views.size()));
			}
			((ViewPager)container).addView(views.get(position%views.size()), 0);
			
			return views.get(position%views.size());
		}
		
	    @Override
	    public void destroyItem(ViewGroup container, int position, Object object) {
	    	// TODO Auto-generated method stub
	    	((ViewPager)container).removeView(views.get(position%views.size()));
	    }
		
	}
	
	

}
