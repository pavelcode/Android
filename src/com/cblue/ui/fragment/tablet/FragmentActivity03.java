package com.cblue.ui.fragment.tablet;

import com.cblue.android.R;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Fragment回退栈
 * @author Administrator
 *
 */
public class FragmentActivity03 extends Activity {

	FragmentManager mFragmentManager;
	
	ContentFragment01 mContentFragment01;
	ContentFragment02 mContentFragment02;
	ContentFragment03 mContentFragment03;
	Button mButton1,mButton2,mButton3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragmentactivity03);
		mButton1 = (Button)findViewById(R.id.frg_activity_btn03);
		mButton2 = (Button)findViewById(R.id.frg_activity_btn04);
		mButton3 = (Button)findViewById(R.id.frg_activity_btn05);
		mButton1.setOnClickListener(listener);
		mButton2.setOnClickListener(listener);
		mButton3.setOnClickListener(listener);
		mFragmentManager = getFragmentManager();
		
	}
	
	OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.frg_activity_btn03:
				mContentFragment01 = new ContentFragment01();
				FragmentTransaction mFragmentTransaction01 = mFragmentManager.beginTransaction();
				mFragmentTransaction01.replace(R.id.fragactivity_ll, mContentFragment01);
				//添加回退栈
				mFragmentTransaction01.addToBackStack(null);
				mFragmentTransaction01.commit();
				break;
			case R.id.frg_activity_btn04:
				mContentFragment02 = new ContentFragment02();
				FragmentTransaction mFragmentTransaction02 = mFragmentManager.beginTransaction();
				mFragmentTransaction02.replace(R.id.fragactivity_ll, mContentFragment02);
				//添加回退栈
				mFragmentTransaction02.addToBackStack(null);
				mFragmentTransaction02.commit();
				break;
			case R.id.frg_activity_btn05:
				mContentFragment03 = new ContentFragment03();
				FragmentTransaction mFragmentTransaction03 = mFragmentManager.beginTransaction();
				mFragmentTransaction03.replace(R.id.fragactivity_ll, mContentFragment03);
				//添加回退栈
				mFragmentTransaction03.addToBackStack(null);
				mFragmentTransaction03.commit();
				break;
		

		
			}
			
		}
	};
}
