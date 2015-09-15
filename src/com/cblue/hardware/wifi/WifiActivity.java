package com.cblue.hardware.wifi;

import java.util.List;

import com.cblue.android.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class WifiActivity extends Activity implements OnClickListener {

	Button btn1;
	Button btn2;
	Button btn3;
	Button btn4;
	Button btn5;
	ListView lView;
	WifiAdmin wifiAdmin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wifi_list);
		initControl();
		wifiAdmin = new WifiAdmin(this);

	}

	public void initControl() {
		btn1 = (Button) findViewById(R.id.open);
		btn2 = (Button) findViewById(R.id.close);
		btn3 = (Button) findViewById(R.id.check);
		btn4 = (Button) findViewById(R.id.openwindow);
		btn5 = (Button) findViewById(R.id.scan);
		lView = (ListView) findViewById(R.id.listview);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.open:
			// 打开wifi
			wifiAdmin.setWifiEnable();
			Toast.makeText(WifiActivity.this,
					"打开wifi，wifi的状态是" + wifiAdmin.checkWifiState(), 1).show();
			break;
		case R.id.close:
			// 关闭wifi
			wifiAdmin.setWifiDisable();
			Toast.makeText(WifiActivity.this,
					"关闭wifi，wifi的状态是" + wifiAdmin.checkWifiState(), 1).show();
			break;
		case R.id.check:
			// 查看wifi状态
			Toast.makeText(WifiActivity.this,
					"查看wifi，wifi的状态是" + wifiAdmin.checkWifiState(), 1).show();
			break;
		case R.id.openwindow:
			// 打开wifi窗口
			wifiAdmin.openWifiSetting();
			break;
		case R.id.scan:
			// 查看wifi列表
			//TODO 只有打开wifi的状态下，才能看到列表，否则会是空指针异常
			lView.setAdapter(new WifiAdpater(WifiActivity.this, wifiAdmin
					.getAllWifi()));
			break;

		}

	}

}

class WifiAdpater extends BaseAdapter {

	private Context context;
	private List<ScanResult> list;

	public WifiAdpater(Context context, List<ScanResult> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		Log.i("aaa", "----");
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.wifi_item,
					null);
			viewHolder.texView = (TextView) convertView.findViewById(R.id.wifiText);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		ScanResult result = list.get(position);
		viewHolder.texView.setText(result.SSID);

		return convertView;
	}

	static class ViewHolder {
		TextView texView;
	}

}

class WifiAdmin {
	// 定义一个WifiManager对象
	WifiManager manager;
	// 定义一个WifiInfo对象
	WifiInfo mWifiInfo;
	// 已经连接过的网络配置信息列表
	// private List<WifiConfiguration> mWifiConfigurations;
	/**
	 * 手机屏幕关闭之后，并且其他的应用程序没有在使用wifi的时候，系统大概在两分钟之后，会关闭wifi，使wifi处于睡眠状态。这样的做法，
	 * 有利于电源能量的节省和延长电池寿命等。
	 * android为wifi提供了一种叫WifiLock的锁，能够阻止wifi进入睡眠状态，使wifi一直处于活跃状态
	 * 。这种锁，在下载一个较大的文件的时候，比较适合使用。
	 */
	// private WifiLock mWifiLock;
	Context context;

	public WifiAdmin(Context context) {
		this.context = context;
		// TODO Auto-generated constructor stub
		manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		mWifiInfo = manager.getConnectionInfo();
	}

	public void setWifiEnable() {
		if (!manager.isWifiEnabled()) {
			manager.setWifiEnabled(true);
		}
	}

	public void setWifiDisable() {
		if (manager.isWifiEnabled()) {
		manager.setWifiEnabled(false);
		}
	}

	public int checkWifiState() {
		return manager.getWifiState();
	}

	public void openWifiSetting() {
		Intent intent = new Intent();
		ComponentName component = new ComponentName("com.android.settings",
				"com.android.settings.WirelessSettings");
		intent.setComponent(component);
		intent.setAction("android.intent.action.View");
		((Activity) context).startActivityForResult(intent, 0);
	}

	public List<ScanResult> getAllWifi() {
		manager.startScan();
		List<ScanResult> result = manager.getScanResults();
		return result;
	}
	

	// 锁定wifiLock
/*	public void acquireWifiLock() {
		mWifiLock.acquire();
	}*/

	// 创建一个wifiLock
/*	public void createWifiLock() {
		mWifiLock = mWifiManager.createWifiLock("test");
	}*/

	// 得到配置好的网络（已经连接过的wifi信息）
/*	public List<WifiConfiguration> getConfiguration() {
		return mWifiConfigurations;
	}*/

	// 指定配置好的网络进行连接
/*	public void connetionConfiguration(int index) {
		if (index > mWifiConfigurations.size()) {
			return;
		}
		// 连接配置好指定ID的网络
		mWifiManager.enableNetwork(mWifiConfigurations.get(index).networkId,
				true);
	}*/
	// 查看扫描结果
/*	public StringBuffer lookUpScan() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < mWifiList.size(); i++) {
			sb.append("Index_" + new Integer(i + 1).toString() + ":");
			// 将ScanResult信息转换成一个字符串包
			// 其中把包括：BSSID、SSID、capabilities、frequency、level
			sb.append((mWifiList.get(i)).toString()).append("\n");
		}
		return sb;
	}
*/
	/*public String getMacAddress() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.getMacAddress();
	}

	public String getBSSID() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.getBSSID();
	}

	public int getIpAddress() {
		return (mWifiInfo == null) ? 0 : mWifiInfo.getIpAddress();
	}

	// 得到连接的ID
	public int getNetWordId() {
		return (mWifiInfo == null) ? 0 : mWifiInfo.getNetworkId();
	}

	// 得到wifiInfo的所有信息
	public String getWifiInfo() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.toString();
	}*/

	// 添加一个网络并连接
/*	public void addNetWork(WifiConfiguration configuration) {
		int wcgId = mWifiManager.addNetwork(configuration);
		mWifiManager.enableNetwork(wcgId, true);
	}*/

	// 断开指定ID的网络
	/*public void disConnectionWifi(int netId) {
		mWifiManager.disableNetwork(netId);
		mWifiManager.disconnect();
	}*/

}
