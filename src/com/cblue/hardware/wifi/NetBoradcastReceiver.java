package com.cblue.hardware.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

/**
 * 监听网络变化的广播
 * @author Administrator
 *
 */
public class NetBoradcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action = intent.getAction();
		if(ConnectivityManager.CONNECTIVITY_ACTION.equals(action)){
			if (NetTools.isConnect(context)) {
				Toast.makeText(context, "网络已经连接", 1).show();
				if (NetTools.wifiConnect(context)) {
					Toast.makeText(context, "wifi连接", 3).show();
				}
				//Log.i(TAG, "aaaa"+NetTools.gprsConnect(context));
				if (NetTools.gprsConnect(context)) {
					Toast.makeText(context, "gprs连接", 3).show();
				}
			}else{
				Toast.makeText(context, "网络未连接", 3).show();
			}

		}
	}

}
