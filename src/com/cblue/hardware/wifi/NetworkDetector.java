package com.cblue.hardware.wifi;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络监测
 * @author Administrator
 *
 */
public class NetworkDetector {
	
	public static boolean detector(Activity activity){
		//根据系统服务得到链接管理者
		ConnectivityManager manager = (ConnectivityManager)activity.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
	     if(manager == null){
	    	 return false;
	     }
	     //根据网络链接的管理者得到网络信息
	   NetworkInfo networkInfo = manager.getActiveNetworkInfo();
	       if(networkInfo==null||!networkInfo.isAvailable()){
	    	   return false;
	       }
	       return true;
	}

}
