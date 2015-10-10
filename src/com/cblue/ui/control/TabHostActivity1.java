package com.cblue.ui.control;

import com.cblue.android.R;
import com.cblue.ui.listview.ListViewActivity;
import com.cblue.ui.listview.ListViewPersonActivity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;

/**
 * 
 * 实现tabhost的第一种方式：每一个标签里面加载的是一个Activity
 * 
 * 1.配置文件
 *  TabHost  @android:id/tabhost
 *  TabWidget @android:id/tabs
 *  FrameLayout  @android:id/tabcontent
 *  
 *  2.类
 *   继承TabActivity
 *    1》获得TabHost的标签对象
 *    2》注册Activity管理器
 *    3》给TabHost对象添加选项卡
 *        newTabSpec 创建选项卡
 *        setIndicator 创建选项卡内容
 *        setContent  创建选项卡内容（Intent）
 *   
 * 
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("deprecation")
public class TabHostActivity1 extends TabActivity {
	private TabHost tabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabhost);

        //得到TabHost的标签的Java对象
		tabHost = getTabHost();
		
		//注册一下本地的Activity管理器
		tabHost.setup(this.getLocalActivityManager());
        //添加标签
		tabHost.addTab(tabHost.newTabSpec("tabs1").setIndicator("indi1")
				.setContent(new Intent(this, ListViewActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("tabs2").setIndicator("indi2")
				.setContent(new Intent(this, ListViewPersonActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("tabs3").setIndicator("indi3")
				.setContent(new Intent(this, ListViewActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("tabs4").setIndicator("indi3")
				.setContent(new Intent(this, ListViewPersonActivity.class)));
        //选项卡修改监听
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				Toast.makeText(TabHostActivity1.this, "tableid=" + tabId,Toast.LENGTH_SHORT).show();

			}
		});

	}

}
