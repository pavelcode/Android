package com.cblue.actionbar;

import java.util.List;

import com.cblue.android.R;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

/**
 * 下拉菜单
 * @author Administrator
 *
 */
public class ActionBarActivity04 extends Activity implements OnNavigationListener {

	
	ActionBar mActionBar;
	String data[]={"aaa","bbb","ccc"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actionbar4);
		mActionBar = getActionBar();
        mActionBar.setTitle("下拉菜单");
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		mActionBar.setListNavigationCallbacks(new ArrayAdapter<String>(ActionBarActivity04.this, android.R.layout.simple_spinner_item, data), this);
	}
	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		Toast.makeText(ActionBarActivity04.this, "itemPosition="+itemPosition, 1).show();
		return false;
	}
}
