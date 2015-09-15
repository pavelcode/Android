package com.cblue.hardware.wifi;

import com.cblue.android.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * 当一次进入首界面的时候，没有网络，就弹出dialog框，让用户设置网络
 * 当之后进入应用的时候，判断网络，然后Toast提示，直接加载数据库数据
 * @author Administrator
 *
 */
public class NetTestActivity extends Activity {

	private static final String TAG = NetTestActivity.class.getSimpleName();
	NetBoradcastReceiver mNetBoradcastReceiver = new NetBoradcastReceiver();
	IntentFilter mIntentFilter = new IntentFilter();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (NetTools.isConnect(NetTestActivity.this)) {
			Toast.makeText(NetTestActivity.this, "网络已经连接", 1).show();
			if (NetTools.wifiConnect(NetTestActivity.this)) {
				Toast.makeText(NetTestActivity.this, "wifi连接", 3).show();
			}
			Log.i(TAG, "aaaa"+NetTools.gprsConnect(NetTestActivity.this));
			if (NetTools.gprsConnect(NetTestActivity.this)) {
				Toast.makeText(NetTestActivity.this, "gprs连接", 3).show();
			}
		}else{
			settingNet(NetTestActivity.this);
		}
		
		//注册监测网络变化的广播
		mIntentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(mNetBoradcastReceiver, mIntentFilter);

	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(mNetBoradcastReceiver);
	}
	
	/**
	 * 跳转到设置网络的界面
	 */
	private void settingNet(Context context){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("设置网络");
		builder.setMessage("是否打开网络？");
		builder.setPositiveButton("同意",new OnClickListener() {
			Intent mIntent;
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if(android.os.Build.VERSION.SDK_INT>10){
					mIntent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
				}else {
					mIntent = new Intent();
					ComponentName componentName = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
					mIntent.setComponent(componentName);
				}
				startActivityForResult(mIntent, 0);
			}
		});
		builder.setNegativeButton("算了",new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
		builder.show();
		
	}

}
