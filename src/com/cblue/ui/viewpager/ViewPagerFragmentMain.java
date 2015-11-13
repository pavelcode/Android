package com.cblue.ui.viewpager;

import java.util.ArrayList;
import java.util.List;

import com.cblue.android.R;


import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.Menu;


/**
 * ViewPager+PagerStrip实现的Tab效果
 * @author pavel
 *
 */
public class ViewPagerFragmentMain extends FragmentActivity {
	
	
	private ViewPager viewPager;
	private PagerTabStrip pagerTabStrp;
	
	
	private String[] titles={"标题一","标题二","标题三"};
	
	
	private List<Fragment> viewPagerFragmetList = new ArrayList<Fragment>();
	private ViewPagerFragment01 fragment01;
	private ViewPagerFragment02 fragment02;
	private ViewPagerFragment03 fragment03;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager_main);
		viewPager = (ViewPager)findViewById(R.id.vp);
		pagerTabStrp = (PagerTabStrip)findViewById(R.id.pts);
		// 文字下划线颜色
		pagerTabStrp.setTabIndicatorColor(getResources().getColor(android.R.color.holo_blue_light));
		//背景颜色
		pagerTabStrp.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
		
		fragment01 = new ViewPagerFragment01();
		fragment02 = new ViewPagerFragment02();
		fragment03 = new ViewPagerFragment03();
		
		viewPagerFragmetList.add(fragment01);
		viewPagerFragmetList.add(fragment02);
		viewPagerFragmetList.add(fragment03);
		
		viewPager.setAdapter(new MyViewPagerAdpater(getSupportFragmentManager()));
		
	}
	
	
	class MyViewPagerAdpater extends FragmentPagerAdapter{

		public MyViewPagerAdpater(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return viewPagerFragmetList.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return viewPagerFragmetList.size();
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return titles[position];
		}
		
		
	}
	
	
	

	

}
