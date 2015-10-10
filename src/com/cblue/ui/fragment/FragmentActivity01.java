package com.cblue.ui.fragment;

import com.cblue.android.R;
import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;



/**
 * 1>使用布局文件加载Fragment
 * 2>并实现了Activity与Fragment交互
 * @author Administrator
 *
 */
public class FragmentActivity01 extends Activity {
	
	
	Button mFrg_Activity_Btn01;
	FragmentManager mFragmentManager ;
	ContentFragment01 mFrg_Content;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragmentactivity01);
		
		mFrg_Activity_Btn01 = (Button)findViewById(R.id.frg_activity_btn01);
		mFragmentManager = getFragmentManager();
		mFrg_Content = (ContentFragment01)mFragmentManager.findFragmentById(R.id.frg_content);
		
		
		mFrg_Activity_Btn01.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TextView frg_textview01= (TextView)mFrg_Content.getView().findViewById(R.id.frg_textview01);
				frg_textview01.setText("变了");
			}
		});
		
		
	}

}
