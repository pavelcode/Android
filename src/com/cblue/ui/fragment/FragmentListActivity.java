package com.cblue.ui.fragment;


import com.cblue.android.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;




/**
 * 使用低版本的Fragment
 * 左边是List列表
 * @author Administrator
 *
 */
public class FragmentListActivity extends FragmentActivity
{

	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragmentlist);
		
		FragmentManager mFragmentManager = getSupportFragmentManager();
		FragmentTransaction mTransformation = mFragmentManager.beginTransaction();
		FragmentListTitle titleFragment = new FragmentListTitle();
	    mTransformation.add(R.id.list_title,titleFragment);
		mTransformation.commit();
	}

	
	
}
