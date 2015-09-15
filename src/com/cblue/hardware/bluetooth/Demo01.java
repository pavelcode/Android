package com.cblue.hardware.bluetooth;

import java.util.Iterator;
import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

/***
 * 蓝牙设备操作
 * 
 * @author Administrator
 * 
 */
public class Demo01 extends Activity {

	private static final String TAG = Demo01.class.getSimpleName();

	/**
	 * 1得到所有配对的蓝牙设备
	 */
	private void getBluetoolDevice() {
		// 1 得到蓝牙的适配器
		BluetoothAdapter bluetoolAdapter = BluetoothAdapter.getDefaultAdapter();
		// 2 判断是否是拥有蓝牙的设备
		if (bluetoolAdapter != null) {
			// 3 判断该设备的蓝牙是否打开
			if (!bluetoolAdapter.enable()) {
				//开启蓝牙设备
				Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
				startActivity(intent);
			}
			// 4得到所有的已经配对的蓝牙适配器对象
			  Set<BluetoothDevice> bluetoolDevices =  bluetoolAdapter.getBondedDevices();
			  Iterator<BluetoothDevice> iterator= bluetoolDevices.iterator();
			  while(iterator.hasNext()){
				  BluetoothDevice bluetoothDevice =  iterator.next();
				  Log.i(TAG,bluetoothDevice.getAddress());
			  }
		}else{
			Log.i(TAG, "没有蓝牙设备");
		}
	}
	
	/**
	 * 2设置蓝牙设备的可见性
	 */
	private void setVisibleToBluetooth(){
		//设置Intent的Action为蓝牙可见性
		Intent mIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
		//给Intent添加参数，设置蓝牙的可见时间为500
		mIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,500);
		startActivity(mIntent);
	}
	
	
	
	/**
	 * 3扫描蓝牙设备
	 */
	BluetoothReceiver bluetoothReceiver = null;
	private void scanBluetoothDevice(){
		//注册广播接收器
		IntentFilter mIntentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
	    bluetoothReceiver = new BluetoothReceiver();
		registerReceiver(bluetoothReceiver, mIntentFilter);
		
		BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		//扫描蓝牙设备，异步，并发送广播
		bluetoothAdapter.startDiscovery();
	}

	//卸载蓝牙广播
	private void unregisterReceiver(){
		
		   if(bluetoothReceiver!=null){
			   unregisterReceiver(bluetoothReceiver);
		   }
	}
	
	//接收蓝牙广播
	private class BluetoothReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			//得到Intent的Action操作名
			String actionName = intent.getAction();
			//Action操作名是蓝牙设备被找到
			if(BluetoothDevice.ACTION_FOUND.equals(actionName)){
				BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				Log.i(TAG,bluetoothDevice.getAddress());
			}
		}
	}
	

}
