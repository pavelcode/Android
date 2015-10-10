package com.cblue.ui.viewpager;


import com.cblue.android.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

/**
 * 使用自定义的方式实现Fragment的切换效果，类似于今日头条的顶部
 * @author pavel
 *
 */
public class ViewPagerFragmentNavigate extends FragmentActivity {
	
	private ViewPagerFragmentNavigateHorizatalScrollView nav_ScrollView;
	private RelativeLayout nav_RelativeLayout;
	private RadioGroup nav_RadioGroup;
	private ImageView nav_iv_indicator;
	private ImageView nav_iv_left;
	private ImageView nav_iv_right;
	private ViewPager mViewPager;
	
	private int indicator_Width; /*每个tab卡底部图片的宽度*/
	private TabFragmentPagerAdapter mTabFragmentPagerAdapter;
	
	public static String [] titles={"标题一","标题二","标题三","标题四","标题五","标题六","标题七"};
	
	private LayoutInflater mLayoutInflater;
	
	private int currentIndicatorLeft =0;  /*当前标签左边的位置*/
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.viewpager_navigate);
		findViewById();
		initView();
		initListener();
	}
	

	private void findViewById(){
		nav_ScrollView = (ViewPagerFragmentNavigateHorizatalScrollView)findViewById(R.id.nav_scroll);
		nav_RelativeLayout =(RelativeLayout)findViewById(R.id.nav_rl);
		nav_RadioGroup = (RadioGroup)findViewById(R.id.nav_content);
		nav_iv_indicator=(ImageView)findViewById(R.id.nav_indicator);
		nav_iv_left = (ImageView)findViewById(R.id.nav_iv_left);
		nav_iv_right = (ImageView)findViewById(R.id.nav_iv_right);
		mViewPager =(ViewPager)findViewById(R.id.vp);
	}
	
	private void initView(){
		/*设置tab卡底部图片的宽度*/
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		indicator_Width=dm.widthPixels/4;
		
		android.view.ViewGroup.LayoutParams layoutParams = nav_iv_indicator.getLayoutParams();
		layoutParams.width = indicator_Width;
		nav_iv_indicator.setLayoutParams(layoutParams);
		
		nav_ScrollView.setParam(nav_RelativeLayout, nav_iv_left, nav_iv_right, dm.widthPixels, this);
		
		mLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		initRadioButtons();
		
		/*设置适配器*/
		mTabFragmentPagerAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mTabFragmentPagerAdapter);
		
		
	}
	
	
	private void initRadioButtons(){
		nav_RadioGroup.removeAllViews();
		for(int i=0;i<titles.length;i++){
		RadioButton radioButton = (RadioButton) mLayoutInflater.inflate(R.layout.viewpager_navigate_radiobutton_item, null);
		radioButton.setId(i);
		radioButton.setText(titles[i]);
		radioButton.setLayoutParams(new LayoutParams(indicator_Width, android.view.ViewGroup.LayoutParams.MATCH_PARENT));
		nav_RadioGroup.addView(radioButton);
		}
	}
	
	
	private void initListener(){
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				//nav_RadioGroup.getChildCount()>position 说明是一个正常的ChildCount
				if(nav_RadioGroup!=null){
					//模仿radiobutton被点击了
					((RadioButton)nav_RadioGroup.getChildAt(position)).performClick();
				}
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		nav_RadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				
				Log.i("aaa", "onCheckedChanged");
				if(nav_RadioGroup.getChildAt(checkedId)!=null){
					/*设置移动动画*/
				int toXDelta = nav_RadioGroup.getChildAt(checkedId).getLeft();
				
	         	TranslateAnimation animation = new TranslateAnimation(currentIndicatorLeft, toXDelta, 0f, 0f);
	         	animation.setInterpolator(new LinearInterpolator());
	         	animation.setDuration(100);
	         	animation.setFillAfter(true);
	         	nav_iv_indicator.startAnimation(animation);
	         	
	         	mViewPager.setCurrentItem(checkedId);
					
	         	currentIndicatorLeft = nav_RadioGroup.getChildAt(checkedId).getLeft();
	         	
	         	/*如果点击的radioButton大于2，x轴滑动的距离*/
	         	int scrollX = (checkedId>1?nav_RadioGroup.getChildAt(checkedId).getLeft():0)-nav_RadioGroup.getChildAt(2).getLeft();
	         	Log.i("aaa", "scrollX="+scrollX);
				nav_ScrollView.smoothScrollTo(scrollX, 0);			
				}
				
			}
		});
	}
	
	
	public class TabFragmentPagerAdapter extends FragmentPagerAdapter{

		public TabFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			Fragment fragment = null;
			switch (arg0) {
			case 0:
				fragment = new ViewPagerFragment01();
				break;
			case 1:
				fragment = new ViewPagerFragment02();
				break;
			case 2:
				fragment = new ViewPagerFragment03();
				break;
			case 3:
				fragment = new ViewPagerFragment01();
				break;
			case 4:
				fragment = new ViewPagerFragment02();
				break;
			case 5:
				fragment = new ViewPagerFragment02();
				break;
			case 6:
				fragment = new ViewPagerFragment02();
				break;
			
			}
			return fragment;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return titles.length;
		}
		
	}
	
	
	
}
