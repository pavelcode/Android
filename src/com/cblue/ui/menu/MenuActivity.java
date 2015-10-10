package com.cblue.ui.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cblue.android.R;

public class MenuActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/**
		 * 上下文菜单 给按钮注册一个上下文菜单
		 */
		setContentView(R.layout.menu_layout);
		Button btn = (Button) findViewById(R.id.btn);
		//TODO 长按事件
		registerForContextMenu(btn);
	}

	/**
	 * 创建选项菜单
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		/**
		 * 参数1 菜单的分组id 
		 * 参数2 菜单的id 
		 * 参数3 菜单的顺序
		 * 
		 */
		menu.add(0, 1, 2, "开始");
		menu.add(0, 2, 1, "设置");
		menu.add(0, 3, 3, "退出");
		
	     /**
	      * 多个菜单项显示的效果不同，最多显示6个
	      */
		/*	menu.add(1, 5, 5, "帮助");
		menu.add(1, 6, 6, "下载");
		menu.add(1, 7, 7, "账户");*/

		/**
		 * 子菜单
		 */
	/*	 SubMenu subMenu = menu.addSubMenu(0, 4, 4, "北京市"); 
		 subMenu.setHeaderIcon(R.drawable.ic_launcher);
		 subMenu.add(0, 1,1, "海淀区"); 
		 subMenu.add(0, 2, 2, "朝阳区");*/
		

	

		/**
		 * 设置菜单是否显示
		 */
		/* menu.setGroupVisible(0, false);*/

		/**
		 * xml添加菜单
		 */
		
		 MenuInflater inflater = getMenuInflater();
		 inflater.inflate(R.menu.menu_item, menu);
		 
		return true;
	}

	/**
	 * 选项菜单事件处理
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	
	   Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
	   
	   return true;
	}

	/**
	 * 添加上下文菜单
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.setHeaderTitle("");
		menu.add(0, 1, 1, "复制");
		menu.add(0, 2, 2, "剪切");
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	/**
	 * 上下文菜单事件处理
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "上下文菜单"+item.getTitle(), Toast.LENGTH_SHORT).show();
		return true;
	}

}