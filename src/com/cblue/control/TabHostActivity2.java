package com.cblue.control;

import android.app.TabActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;

import com.cblue.android.R;


/**
 * 实现tabhost的第二种方式：每一个标签里面加载的是同一个Activity
 * 1.配置文件
 *  TabHost  @android:id/tabhost
 *  TabWidget @android:id/tabs
 *  FrameLayout  @android:id/tabcontent
 *  LinearLayout  @+id/tabN
 *  
 *  2.类
 *   继承TabActivity
 *    1》获得TabHost的标签对象
 *    2》注册Activity管理器
 *    3》给TabHost对象添加选项卡
 *        newTabSpec 创建选项卡
 *        setIndicator 创建选项卡内容
 *        setContent  创建选项卡内容（给的是控件自定义id）
 *   
 * 
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("deprecation")
public class TabHostActivity2 extends TabActivity {
	private TabHost tabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 从TabActivity上面获取放置Tab的TabHost
		tabHost = this.getTabHost();
		//加载布局，放入到tabhost对象中
		LayoutInflater.from(this).inflate(R.layout.tabhost2,
				tabHost.getTabContentView(), true);
		// 添加标签页
		tabHost.addTab(tabHost.newTabSpec("tab01").setIndicator("已接电话")
				.setContent(R.id.tab1));
		tabHost.addTab(tabHost.newTabSpec("tab02").setIndicator("未接电话",getResources().getDrawable(R.drawable.icon))
				.setContent(R.id.tab2));
		tabHost.addTab(tabHost.newTabSpec("tab03").setIndicator("已拨电话")
				.setContent(R.id.tab3));
		
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				Toast.makeText(TabHostActivity2.this, "tableid=" + tabId,Toast.LENGTH_SHORT).show();

			}
		});

	}

}
