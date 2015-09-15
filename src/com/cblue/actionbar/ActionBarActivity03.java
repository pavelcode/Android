package com.cblue.actionbar;

import com.cblue.android.R;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.os.Bundle;
import android.widget.Toast;

/**
 * 选项卡
 * @author Administrator
 *
 */
public class ActionBarActivity03 extends Activity {
	
	
	private ActionBar mActionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actionbar2);
		
		mActionBar= getActionBar();
		//设置导航模式
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		mActionBar.setTitle("选项卡");
		mActionBar.addTab(mActionBar.newTab().setText("tab1").setTabListener(mTabListener));
		mActionBar.addTab(mActionBar.newTab().setText("tab2").setTabListener(mTabListener));
		mActionBar.addTab(mActionBar.newTab().setText("tab3").setTabListener(mTabListener));
		
		
	}
	
	private TabListener mTabListener = new TabListener() {
		
		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			int count = mActionBar.getTabCount();
			for(int i = 0;i<count;i++){
				Toast.makeText(ActionBarActivity03.this, tab.getText(), Toast.LENGTH_LONG).show();
			}
		}
		
		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}
	};

}
