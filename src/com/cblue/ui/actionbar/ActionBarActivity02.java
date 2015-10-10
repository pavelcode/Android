package com.cblue.ui.actionbar;

import com.cblue.android.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * ActionBar实现选项菜单
 * 主要是布局文件属性控制显示效果：showAsAction
 * @author Administrator
 *
 */
public class ActionBarActivity02 extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actionbar2);	
	}
	
	/* 
	 * 加载属性菜单
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.actionbar_menu, menu);
		return true;
	};
	
	/* 
	 * 菜单选项点击
	 */
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
	
		case R.id.item1_1:
			Toast.makeText(ActionBarActivity02.this, "item1_1被点击", 1).show();
			
			break;

		default:
			break;
		}
		
		return super.onMenuItemSelected(featureId, item);
	}

}
