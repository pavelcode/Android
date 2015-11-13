package com.cblue.ui.viewpager;

import java.util.ArrayList;
import java.util.List;

import com.cblue.android.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 使用ViewPager实现tab效果
 * @author pavel
 *
 */
public class ViewPagerTabActivity01 extends Activity implements OnClickListener {

	
	private LinearLayout ll1,ll2,ll3;
	private TextView tv1,tv2,tv3;
	private ViewPager mViewPager;
	private List<View> views;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager_tab_activity01);
		mViewPager = (ViewPager) findViewById(R.id.viewpager_tab01_frag);
		
		ll1 = (LinearLayout)findViewById(R.id.viewpager_tab01_ll1);
		ll2 = (LinearLayout)findViewById(R.id.viewpager_tab01_ll2);
		ll3 = (LinearLayout)findViewById(R.id.viewpager_tab01_ll3);
		ll1.setOnClickListener(this);
		ll2.setOnClickListener(this);
		ll3.setOnClickListener(this);
		
		tv1 = (TextView)findViewById(R.id.viewpager_tab01_tv1);
		tv2 = (TextView)findViewById(R.id.viewpager_tab01_tv2);
		tv3 = (TextView)findViewById(R.id.viewpager_tab01_tv3);
		
		views = new ArrayList<View>();
		View view1 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.viewpager_fragment, null);
		View view2 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.viewpager_fragment, null);
		View view3 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.viewpager_fragment, null);
		views.add(view1);
		views.add(view2);
		views.add(view3);
		
		mViewPager.setAdapter(new MyPagerAdpter());
		setSelect(0);
	}

	class MyPagerAdpter extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return views.size();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stu
			container.removeView(views.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			container.addView(views.get(position));
			return views.get(position);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return (arg0==arg1);
		}
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.viewpager_tab01_ll1:
			setSelect(0);
			mViewPager.setCurrentItem(0);
			break;
		case R.id.viewpager_tab01_ll2:
			setSelect(1);
			mViewPager.setCurrentItem(1);
			break;
		case R.id.viewpager_tab01_ll3:
			setSelect(2);
			mViewPager.setCurrentItem(2);
			break;

		default:
			break;
		}
		
	}
	
	private void setSelect(int position){
		switch (position) {
		case 0:
			tv1.setTextColor(Color.parseColor("#ff0000"));
			tv2.setTextColor(Color.parseColor("#000000"));
			tv3.setTextColor(Color.parseColor("#000000"));
			break;
		case 1:
			tv1.setTextColor(Color.parseColor("#000000"));
			tv2.setTextColor(Color.parseColor("#ff0000"));
			tv3.setTextColor(Color.parseColor("#000000"));
			break;
		case 2:
			tv1.setTextColor(Color.parseColor("#000000"));
			tv2.setTextColor(Color.parseColor("#000000"));
			tv3.setTextColor(Color.parseColor("#ff0000"));
			break;

		default:
			break;
		}
			
	
	}
}
