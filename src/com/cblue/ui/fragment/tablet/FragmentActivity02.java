package com.cblue.ui.fragment.tablet;


import com.cblue.android.R;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


/**
 * 1>使用代码加载Fragment
 * 2>实现了Activity与Fragment交互
 * 3>Fragment处理自己的按钮事件
 * @author Administrator
 *
 */
public class FragmentActivity02 extends Activity {
	
	FragmentManager mFragmentManager;
	FragmentTransaction mFragmentTransaction;
	ContentFragment02 mContentFragment;
	Button mButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragmentactivity02);
		mButton = (Button)findViewById(R.id.frg_activity_btn02);
		
		mFragmentManager = getFragmentManager();
		mFragmentTransaction = mFragmentManager.beginTransaction();
		mContentFragment = new ContentFragment02();
		mFragmentTransaction.add(R.id.fragactivity_ll, mContentFragment);
		mFragmentTransaction.commit();
		
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TextView frg_textview02= (TextView)mContentFragment.getView().findViewById(R.id.frg_textview02);
				frg_textview02.setText("变了");
			}
		});
	}

}
