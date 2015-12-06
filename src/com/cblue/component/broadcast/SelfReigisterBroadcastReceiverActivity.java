package com.cblue.component.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cblue.android.R;

/**
 * 使用代码进行注册和取消注册广播接收器
 * 广播必须有action
 * @author Administrator
 *
 */
public class SelfReigisterBroadcastReceiverActivity extends Activity {

	private Button sendBroadcastButton;
	private Button registerBroadcastButton;
	private Button unRegisterBroadcastButton;
	private SelfRegisterBroadcastReceiver selfRegisterBroadcastReceiver;
	public static final String TAG = SelfReigisterBroadcastReceiverActivity.class.getSimpleName();
	// 是否可以取消广播
	private boolean flag = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mybroadcastreceiver_register);
		sendBroadcastButton = (Button) findViewById(R.id.self_MyBroadCastBtn);
		registerBroadcastButton = (Button) findViewById(R.id.register_MyBroadCastBtn);
		unRegisterBroadcastButton = (Button) findViewById(R.id.unregister_MyBroadCastBtn);
		sendBroadcastButton.setOnClickListener(listener);
		registerBroadcastButton.setOnClickListener(listener);
		unRegisterBroadcastButton.setOnClickListener(listener);

	}

	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			// 发送广播
			case R.id.self_MyBroadCastBtn:
				Log.i(TAG, "发送广播");
				Intent intent = new Intent();
				intent.setAction("myselfBroad.ACTION");
				sendBroadcast(intent);
				break;
				// 注册广播
			case R.id.register_MyBroadCastBtn:
				if(!flag){
				Log.i(TAG, "注册广播");
				flag = true;
				selfRegisterBroadcastReceiver = new SelfRegisterBroadcastReceiver();
				IntentFilter intentFilter = new IntentFilter();
				intentFilter.addAction("myselfBroad.ACTION");
				registerReceiver(selfRegisterBroadcastReceiver, intentFilter);
				}
				break;
			// 卸载广播
			case R.id.unregister_MyBroadCastBtn:
				if (flag) {
					Log.i(TAG, "取消注册广播");
					flag = false;
					unregisterReceiver(selfRegisterBroadcastReceiver);
				}
				break;
			}

		}
	};
}
