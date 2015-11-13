package com.cblue.ui.viewpager;

import java.util.ArrayList;
import java.util.List;

import com.cblue.android.R;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * ViewPager+FragmentPagerAdapter实现tab效果
 * @author pavel
 *
 */
public class ViewPagerTabActivity03 extends FragmentActivity implements OnClickListener,OnPageChangeListener{
	
	
	private LinearLayout ll1,ll2,ll3;
	private TextView tv1,tv2,tv3;
	private ViewPager mViewPager;
	private List<Fragment> fragments;
	
	 @Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.viewpager_tab_activity03);
		ll1 = (LinearLayout)findViewById(R.id.viewpager_tab03_ll1);
		ll2 = (LinearLayout)findViewById(R.id.viewpager_tab03_ll2);
		ll3 = (LinearLayout)findViewById(R.id.viewpager_tab03_ll3);
		ll1.setOnClickListener(this);
		ll2.setOnClickListener(this);
		ll3.setOnClickListener(this);
		
		tv1 = (TextView)findViewById(R.id.viewpager_tab03_tv1);
		tv2 = (TextView)findViewById(R.id.viewpager_tab03_tv2);
		tv3 = (TextView)findViewById(R.id.viewpager_tab03_tv3);
		
		
		fragments = new ArrayList<Fragment>();
		ViewPagerFragment01 viewPagerFragment01 = new ViewPagerFragment01();
		ViewPagerFragment02 viewPagerFragment02 = new ViewPagerFragment02();
		ViewPagerFragment03 viewPagerFragment03 = new ViewPagerFragment03();
		fragments.add(viewPagerFragment01);
		fragments.add(viewPagerFragment02);
		fragments.add(viewPagerFragment03);
		
		mViewPager = (ViewPager) findViewById(R.id.viewpager_tab03);
		mViewPager.setAdapter(new MyFragmentPagerAdpter(getSupportFragmentManager()));
		
		mViewPager.setOnPageChangeListener(this);
		setTabColor(0);
	}
	 
	
	 
	class MyFragmentPagerAdpter  extends FragmentPagerAdapter{

		public MyFragmentPagerAdpter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return fragments.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return fragments.size();
		}
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.viewpager_tab03_ll1:
			mViewPager.setCurrentItem(0);
			
			break;
		case R.id.viewpager_tab03_ll2:
			mViewPager.setCurrentItem(1);
			break;
		case R.id.viewpager_tab03_ll3:
			mViewPager.setCurrentItem(2);
			break;

		default:
			break;
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
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		setTabColor(arg0);
		
	}
	
	private void setTabColor(int position){
		resetTabColor();
		switch (position) {
		case 0:
			tv1.setTextColor(Color.parseColor("#ff0000"));
			break;
		case 1:
			tv2.setTextColor(Color.parseColor("#ff0000"));
			break;
		case 2:
			tv3.setTextColor(Color.parseColor("#ff0000"));
			break;

		default:
			break;
		}
	}
	
	private void resetTabColor(){
		tv1.setTextColor(Color.parseColor("#000000"));
		tv2.setTextColor(Color.parseColor("#000000"));
		tv3.setTextColor(Color.parseColor("#000000"));
	}

}
