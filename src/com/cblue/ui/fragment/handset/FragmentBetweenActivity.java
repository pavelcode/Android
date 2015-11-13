package com.cblue.ui.fragment.handset;



import com.cblue.android.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * 标题和内容的Fragment显示在Activity中
 * 实现Fragment之间传递数据
 * @author Administrator
 *
 */
public class FragmentBetweenActivity extends Activity {
	
	FragmentManager mFragmentManager;
	FragmentTransaction mFragmentTransaction;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragmentbetweenactivity_handset);
		
		mFragmentManager = getFragmentManager();
		mFragmentTransaction =mFragmentManager.beginTransaction();
		FragmentBetweenLeft  mLeftFragment =new FragmentBetweenLeft();
		mFragmentTransaction.add(R.id.leftfrag, mLeftFragment);
		mFragmentTransaction.commit();
		
	}
	
	

	
}
