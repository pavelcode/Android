package com.cblue.ui.listpager.xlistview;


import com.cblue.android.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;


public class MainActivity extends FragmentActivity {

	private ViewPager mViewPager;
	private PagerAdapter mPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		initData();
	}

	private void initView() {
		setContentView(R.layout.xlistview_activity_main);
		mViewPager = (ViewPager) this.findViewById(R.id.xlistview_view_pager);
		mPagerAdapter = new PagerAdapter(this);
	}

	private void initData() {
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageScrolled(int position,
							float positionOffset, int positionOffsetPixels) {
					}
				});
		mPagerAdapter.addTab(Fragment01.class, null);
		mPagerAdapter.addTab(Fragment02.class, null);
		mPagerAdapter.addTab(Fragment03.class, null);
		mViewPager.setAdapter(mPagerAdapter);
	}

}


