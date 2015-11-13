package com.cblue.ui.viewpager;

import java.util.List;

import com.cblue.android.R;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Fragment实现tab
 * @author pavel
 *
 */
public class ViewPagerTabActivity02 extends FragmentActivity implements OnClickListener {
	
	private LinearLayout ll1,ll2,ll3;
	private TextView tv1,tv2,tv3;
    private ViewPagerFragment01 mViewPagerFragment01;
    private ViewPagerFragment02 mViewPagerFragment02;
    private ViewPagerFragment03 mViewPagerFragment03;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.viewpager_tab_activity02);
		ll1 = (LinearLayout)findViewById(R.id.viewpager_tab02_ll1);
		ll2 = (LinearLayout)findViewById(R.id.viewpager_tab02_ll2);
		ll3 = (LinearLayout)findViewById(R.id.viewpager_tab02_ll3);
		ll1.setOnClickListener(this);
		ll2.setOnClickListener(this);
		ll3.setOnClickListener(this);
		
		tv1 = (TextView)findViewById(R.id.viewpager_tab02_tv1);
		tv2 = (TextView)findViewById(R.id.viewpager_tab02_tv2);
		tv3 = (TextView)findViewById(R.id.viewpager_tab02_tv3);
		
		mViewPagerFragment01 = new ViewPagerFragment01();
		mViewPagerFragment02 = new ViewPagerFragment02();
		mViewPagerFragment03 = new ViewPagerFragment03();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		FragmentManager mFragmentManager = getSupportFragmentManager();
		FragmentTransaction mFragmentTransaction ;
		switch (v.getId()) {
		case R.id.viewpager_tab02_ll1:
		 mFragmentTransaction = mFragmentManager.beginTransaction();
		 mFragmentTransaction.replace(R.id.viewpager_tab02_frag, mViewPagerFragment01);
		 mFragmentTransaction.commit();
		 setSelect(0);
			break;
		case R.id.viewpager_tab02_ll2:
			 mFragmentTransaction = mFragmentManager.beginTransaction();
			 mFragmentTransaction.replace(R.id.viewpager_tab02_frag, mViewPagerFragment02);
			 mFragmentTransaction.commit();
			 setSelect(1);
			break;
		case R.id.viewpager_tab02_ll3:
			 mFragmentTransaction = mFragmentManager.beginTransaction();
			 mFragmentTransaction.replace(R.id.viewpager_tab02_frag, mViewPagerFragment03);
			 mFragmentTransaction.commit();
			 setSelect(2);
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
