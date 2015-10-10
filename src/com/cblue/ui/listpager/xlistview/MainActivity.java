package com.cblue.ui.listpager.xlistview;


import com.cblue.android.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

/**
 * 
 * 下拉刷新,上拉加载更多
 * 著名github项目XListView
 * git地址
 * source code: https://github.com/Maxwin-z/XListView-Android
 * 原理： 
 * XListView继承ListView。
 * 下拉刷新组件是ListView的一个Header。在ListView创建时就将这个自定义View塞进去，默认情况是看不到的，所以这个HeaderView的高度初始设置为0。
 * 上拉载入更多组件是Footer，为了确保这个footer在最后（可能会添加多个自定义footer），在用户调用setAdatper的时候再把这个footer塞进去。
 * 覆写ListView的onTouchEvent方法，处理各种情况。
 * 用户松手，启动mScroller，将header、footer回滚到所需状态。
 * 添加了用户下拉、上拉移动delay的效果。
 * 提供了两个接口：
 * a) IXListViewListener:  触发下拉刷新、上拉载入更多
 * b) OnXScrollListener: 这个和原生的OnScrollListener一样，但是在mScroller回滚时，也会触发这里的事件
 * 
 * 
 * Fragment+
 * @author Administrator
 *
 */
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


