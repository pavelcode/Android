package com.cblue.actionbar;

import com.cblue.android.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * ActionBar隐藏和现实
 * ActionBar设置应用程序图标可以被点击
 * @author Administrator
 *
 */

public class ActionBarActivity01 extends Activity {

	Button button1;
	Button button2;
	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actionbar1);
		
		button1 = (Button) findViewById(R.id.actionbar_button1);
		button2 = (Button) findViewById(R.id.actionbar_button2);
		actionBar = getActionBar();
		if(actionBar!=null){
			//显示应用的图标
			actionBar.setDisplayShowTitleEnabled(true);
			//设置应用程序图标可以被点击
			actionBar.setDisplayHomeAsUpEnabled(true);
		
		}
		button1.setOnClickListener(listener);
		button2.setOnClickListener(listener);
	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.actionbar_button1:
				actionBar.show();
				break;

			case R.id.actionbar_button2:
				actionBar.hide();
				break;
			}

		}
	};
	
	
	
	/* 
	 * 菜单选项点击
	 */
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			Toast.makeText(ActionBarActivity01.this, "home被点击", 1).show();
			break;
		}
		
		return super.onMenuItemSelected(featureId, item);
	}

}
