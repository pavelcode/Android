package com.cblue.hardware.wifi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

/**
 * 检查wifi的工具类
 * 判断网络是否连接
 * 判断网络的类型：wifi，gprs
 * @author Administrator
 *
 */
public class NetTools {
	
	
	/**
	 * 判断网络连接
	 * @param context
	 * @return
	 */
	public static boolean isConnect(Context context){
		boolean flag = false;
		
		ConnectivityManager connManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if(networkInfo!=null){
        	return networkInfo.isAvailable();
        }
		return flag;
	}
	
	/**
	 * wifi连接
	 * @param context
	 * @return
	 */
	public static boolean wifiConnect(Context context){
		boolean flag = false;
		
		ConnectivityManager conneManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		State state = conneManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
		if(State.CONNECTED.equals(state)){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * GPRS连接
	 * @param context
	 * @return
	 */
	public static boolean gprsConnect(Context context){
		boolean flag = false;
		ConnectivityManager connManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
		if(State.CONNECTED.equals(state)){
			flag = true;
		}
		return flag;
	}
	
	
	// 获取连接类型
		public static int getConnectedType(Context context) {
			if (context != null) {
				ConnectivityManager mConnectivityManager = (ConnectivityManager) context
						.getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo mNetworkInfo = mConnectivityManager
						.getActiveNetworkInfo();
				if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
					return mNetworkInfo.getType();
				}
			}
			return -1;
		}
	

}
