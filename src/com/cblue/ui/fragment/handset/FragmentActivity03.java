package com.cblue.ui.fragment.handset;

import java.util.ArrayList;
import java.util.List;

import com.cblue.android.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 在UI的底部，有三个按钮，点击按钮，上面对应的Fragment显示
 * 1》首先使用replace替换，每次都会重新创建类
 *    注意：1  每一个替换都需要一个新的Transaction对象 
 *         2  add方法只能使用一次
 * 2》【理解错误】使用hidden，show 隐藏和显示Fragment 不会重新创建类（这种方式效果不好）
 * 3》添加回退栈
 * @author pavel
 *
 */
public class FragmentActivity03 extends FragmentActivity  implements OnClickListener{

	private Button btn1,btn2,btn3;
	private FragmentManager mFragmentManager;
	private FragmentTransaction mfFragmentTransaction;
	
	private ContentFragment01 fragment01 = new ContentFragment01();
	private ContentFragment02 fragment02 = new ContentFragment02();
	private ContentFragment03 fragment03 = new ContentFragment03();
	
	private List<Fragment> fragments = new ArrayList<Fragment>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_activity03_handset);
		btn1 = (Button) findViewById(R.id.fragment_activity03_btn1);
		btn2 = (Button) findViewById(R.id.fragment_activity03_btn2);
		btn3 = (Button) findViewById(R.id.fragment_activity03_btn3);
		
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		
		fragments.add(fragment01);
		fragments.add(fragment02);
		fragments.add(fragment03);
		
		mFragmentManager = getSupportFragmentManager();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.fragment_activity03_btn1:
			// FragmentTransaction 必须每次得到
			// 如果都使用add不会成功，可以使用replace
			mfFragmentTransaction = mFragmentManager.beginTransaction();
			/*trunFragment(fragments);
			if(!fragment01.isAdded()){
				mfFragmentTransaction.add(R.id.fragment_activity03_ll, fragment01);
			}else{
				mfFragmentTransaction.show(fragment01);
				
			}*/
			mfFragmentTransaction.replace(R.id.fragment_activity03_ll,fragment01);
			//添加回退栈
			mfFragmentTransaction.addToBackStack(null);
			mfFragmentTransaction.commit();
			break;
		case R.id.fragment_activity03_btn2:
			mfFragmentTransaction = mFragmentManager.beginTransaction();
			
			/*trunFragment(fragments);
			if(!fragment02.isAdded()){
				mfFragmentTransaction.add(R.id.fragment_activity03_ll, fragment02);
			}else{
				mfFragmentTransaction.hide(fragment01).show(fragment02);
			}*/
			//添加回退栈
			mfFragmentTransaction.addToBackStack(null);
			mfFragmentTransaction.replace(R.id.fragment_activity03_ll,fragment02);
			mfFragmentTransaction.commit();
			break;
		case R.id.fragment_activity03_btn3:
			mfFragmentTransaction = mFragmentManager.beginTransaction();
			/*trunFragment(fragments);
			if(!fragment03.isAdded()){
				mfFragmentTransaction.add(R.id.fragment_activity03_ll, fragment03);
			}else{
				
				mfFragmentTransaction.hide(fragment02).show(fragment03);
			}*/
			mfFragmentTransaction.replace(R.id.fragment_activity03_ll,fragment03);
			//添加回退栈
			mfFragmentTransaction.addToBackStack(null);
			mfFragmentTransaction.commit();
			break;
		}
	}
	/**
	 * 把所有的Fragment都隐藏，让某个Fragment显示
	 * @param allFragments
	 * @param showFragment
	 */
	private void trunFragment(List<Fragment> allFragments){
		//mfFragmentTransaction = mFragmentManager.beginTransaction();
	    for(Fragment hiddenFragment:allFragments){
	    	if(hiddenFragment.isAdded()){
	    		mfFragmentTransaction.hide(hiddenFragment);
	    	}
	    }	
	}
}
