package com.cblue.ui.control;

import com.cblue.android.R;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;


/**
 * TabHost在底部三种实现方式
 * @author Administrator
 *
 */
public class TabHostBottom extends TabActivity{

	
	private TabHost tabHost;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.tabhost_bottom1);
		//setContentView(R.layout.tabhost_bottom2);
		setContentView(R.layout.tabhost_bottom3);
		// 从TabActivity上面获取放置Tab的TabHost
		tabHost = this.getTabHost();	
		tabHost.addTab(tabHost.newTabSpec("tab01").setIndicator("已接电话")
				.setContent(R.id.tab01));
		tabHost.addTab(tabHost.newTabSpec("tab02").setIndicator("未接电话")
				.setContent(R.id.tab02));
		tabHost.addTab(tabHost.newTabSpec("tab03").setIndicator("已拨电话")
				.setContent(R.id.tab03));
	}
}
