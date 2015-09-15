package com.cblue.component.activity;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


/**
 * 现场保护:当屏幕切换的时候，保存数据，当重新进入activity的时候，得到这个数据
 * @author Administrator
 *
 */
public class SaveInstanceStateActivity extends Activity {

	private final String TAG= SaveInstanceStateActivity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i("aaa", "onCreate");
		if(savedInstanceState!=null&&savedInstanceState.getInt("currentPosition")!=0){
			Log.i("aaa", "currentPosition="+savedInstanceState.getInt("currentPosition"));
		}
		
	}
	
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		Log.i("aaa", "onSaveInstanceState");
		outState.putInt("currentPosition", 100);
	}
	
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
		Log.i("aaa", "onRestoreInstanceState");
		if(savedInstanceState!=null&&savedInstanceState.getInt("currentPosition")!=0){
			Log.i("aaa", "currentPosition="+savedInstanceState.getInt("currentPosition"));
		}	
	}
	
	
	
}
