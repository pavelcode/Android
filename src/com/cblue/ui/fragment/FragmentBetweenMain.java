package com.cblue.ui.fragment;


import com.cblue.android.R;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * 实现Fragment之间传递数据
 * @author Administrator
 *
 */
public class FragmentBetweenMain extends Activity {
	
	FragmentManager mFragmentManager;
	FragmentTransaction mFragmentTransaction;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragmentbetweenmain);
		
		mFragmentManager = getFragmentManager();
		mFragmentTransaction =mFragmentManager.beginTransaction();
		FragmentBetweenLeft  mLeftFragment =new FragmentBetweenLeft();
		mFragmentTransaction.add(R.id.leftfrag, mLeftFragment);
		mFragmentTransaction.commit();
		
	}
	
	

	
}
