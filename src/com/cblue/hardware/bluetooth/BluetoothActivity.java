package com.cblue.hardware.bluetooth;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.cblue.android.R;
import com.cblue.hardware.bluetooth.BluetoothService.AcceptThread;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 实现功能：
 *        判断设备是否有蓝牙
 *        打开蓝牙，设置蓝牙开启时间
 *        查找蓝牙设备  配对的 没配对的
 *        配对蓝牙设备
 *        连接蓝牙，发送信息
 * 添加权限
 *  <uses-permission android:name="android.permission.BLUETOOTH" />
    <uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />
    清单activity中添加
     android:windowSoftInputMode="adjustUnspecified|stateHidden" 
     不弹出软键盘
 * @author Administrator
 *
 */
public class BluetoothActivity extends Activity implements OnClickListener,OnItemClickListener {

	
	private Button hasBluetoothBtn,openBluetoothBtn,scanBluetoothBtn;
	private ListView pairedBluetoothLV,unpairedBluetoothLV;
	
/*	private StringBuffer pairdBluetoothDevicesStringBuffer; //配对的蓝牙设备在适配器中的字符串
	private StringBuffer unpairdBluetoothDevicesStringBuffer;//未配对的蓝牙设备在适配器中的字符串
*/    private ArrayAdapter<String> pairedBluetoothAdapter,unpairedBluetoothAdapter;
	
    private List<BluetoothDevice> pairdBluetoothDevices;  //配对的蓝牙设备
	private List<BluetoothDevice> unpairdBluetoothDevices; //未配对的蓝牙设备
	
    
	private BluetoothAdapter bluetoothAdapter;
	
	private EditText msg_et;
	private Button msg_btn;
	private TextView msg_tv;
	private BluetoothService bluetoothService; //蓝牙连接服务
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bluetooth_activity);
		initView();
	}
	
	/* (non-Javadoc)
	 * 放在Resume的好处是每次都打开activity，都会启动服务端
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//启动服务端
		if(bluetoothService!=null){
			bluetoothService.start();
		}
		
	}

	private void initView() {
		// TODO Auto-generated method stub
		hasBluetoothBtn = (Button)findViewById(R.id.hasBluetooth);
		openBluetoothBtn = (Button)findViewById(R.id.openBluetooth);
		scanBluetoothBtn =(Button)findViewById(R.id.scanBluetooth);
		hasBluetoothBtn.setOnClickListener(this);
		openBluetoothBtn.setOnClickListener(this);
		scanBluetoothBtn.setOnClickListener(this);
		
		pairedBluetoothLV = (ListView)findViewById(R.id.pairedBluetooth);
		pairedBluetoothAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.bluetooth_listview_item);
		pairedBluetoothLV.setAdapter(pairedBluetoothAdapter);
		pairedBluetoothLV.setOnItemClickListener(this);
		
		unpairedBluetoothLV = (ListView)findViewById(R.id.unpairedBluetooth);
		unpairedBluetoothAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.bluetooth_listview_item);
		unpairedBluetoothLV.setAdapter(unpairedBluetoothAdapter);	
		unpairedBluetoothLV.setOnItemClickListener(this);
		

		
		pairdBluetoothDevices = new ArrayList<BluetoothDevice>();
		unpairdBluetoothDevices = new ArrayList<BluetoothDevice>();
		
		bluetoothService = new BluetoothService(getApplicationContext(),handler);
		//发送蓝牙信息
		msg_et = (EditText)findViewById(R.id.msg_et);
		msg_btn = (Button)findViewById(R.id.msg_btn);
		msg_tv =(TextView)findViewById(R.id.msg_tv);
		msg_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String msg = msg_et.getText().toString();
				AcceptThread at = bluetoothService.getAcceptThread();
				at.write(msg.getBytes());
				
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.hasBluetooth:
			//判断当前设备是否支持蓝牙
			bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
			if(bluetoothAdapter!=null){
				Toast.makeText(getApplicationContext(), "本设备支持蓝牙", 1).show();
			}else{
				Toast.makeText(getApplicationContext(), "本设备不支持蓝牙", 1).show();
			}
			break;

		case R.id.openBluetooth:
			//打开蓝牙设备
			if(bluetoothAdapter!=null){
				//如果蓝牙设备未打开，打开蓝牙
				if(!bluetoothAdapter.isEnabled()){
					bluetoothAdapter.enable();
				}
				//设置蓝牙被发现的时间
				Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
				intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
				startActivity(intent);
				
			}else{
				Toast.makeText(getApplicationContext(), "首先查看本设备是否支持蓝牙", 1).show();
			}
			break;
			
		case R.id.scanBluetooth:
			//扫描蓝牙
			if(bluetoothAdapter!=null){
				//如果蓝牙设备正在查找,取消蓝牙搜索，重新开始搜索
				if(bluetoothAdapter.isDiscovering()){
					bluetoothAdapter.cancelDiscovery();
				}
				//把已经配对的蓝牙加载出来
				Set<BluetoothDevice> bluetoothDevices = bluetoothAdapter.getBondedDevices();
				if(bluetoothDevices.size()>0){
					//把适配器中内容清空
					pairedBluetoothAdapter.clear();
					for(BluetoothDevice bluetoothDevice:bluetoothDevices){
						//保存已配对的蓝牙设备
						pairdBluetoothDevices.add(bluetoothDevice);
						//给适配器中添加未配对的蓝牙设备
						pairedBluetoothAdapter.add(bluetoothDevice.getName()+"\n"+bluetoothDevice.getAddress()+"\n");
					}
					
				}else{
					pairedBluetoothAdapter.add("没有配对过蓝牙");
				}
				//开始搜索蓝牙
				bluetoothAdapter.startDiscovery();
				//注册广播，查找新设备
				//发现设备的广播
				IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
				registerReceiver(receiver, filter);
				//搜索完成的广播
				filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
				registerReceiver(receiver, filter);
				
				
			}else{
				Toast.makeText(getApplicationContext(), "首先打开本设备的蓝牙", 1).show();
			}
			break;
			
			
		}
		
	}
	
	
	 private final BroadcastReceiver receiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action =  intent.getAction();
			boolean flag = false; //标志位，判断是否有数据
			//如果发现新的蓝牙设备
			if(BluetoothDevice.ACTION_FOUND.equals(action)){
				BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				//把搜索到的蓝牙设备进行配对
				if(bluetoothDevice.getBondState()!=BluetoothDevice.BOND_BONDED){
					flag = true;
					//保存未配对的蓝牙设备
					unpairdBluetoothDevices.add(bluetoothDevice);
					//给Adapter添加未配对的蓝牙设备信息
					unpairedBluetoothAdapter.add(bluetoothDevice.getName()+"\n"+bluetoothDevice.getAddress()+"\n");
				}
			}else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
				//扫描完毕
				 if(!flag){
					 unpairedBluetoothAdapter.add("未发现新设备");
				 }
			}
		}
	};
	
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	};
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		if(bluetoothAdapter!=null){
			bluetoothAdapter.cancelDiscovery();
			switch (parent.getId()) {
			case R.id.unpairedBluetooth:
				 Log.i("aaa", "未配对的蓝牙设备");
				 pairAndConnectDevice(unpairdBluetoothDevices.get(position));
				break;
			case R.id.pairedBluetooth:
				 Log.i("aaa", "配对的蓝牙设备");
				 pairAndConnectDevice(pairdBluetoothDevices.get(position));
				break;

			default:
				break;
			}
            
			
		}
		
	}
	
	/**
	 * 配对并连接设备
	 */
	private void pairAndConnectDevice(BluetoothDevice bluetoothDevice){
		//得到连接状态
		int connectState = bluetoothDevice.getBondState();
		switch (connectState) {
		//如果设备未配对，先配对
		case BluetoothDevice.BOND_NONE:
			try {
				Method method = BluetoothDevice.class.getMethod("createBond");
				method.invoke(bluetoothDevice);
				Toast.makeText(getApplicationContext(), "配对成功", 1).show();
			} catch (Exception e) {
				// TODO: handle exception
				Toast.makeText(getApplicationContext(), "配对失败", 1).show();
			}
			break;	
        //如果设备已配对，连接其他设备
		case BluetoothDevice.BOND_BONDED:
			Toast.makeText(getApplicationContext(), "开始连接设备", 1).show();
			//启动蓝牙服务
		     if(bluetoothService!=null){
		    	synchronized (bluetoothDevice) {
		    		bluetoothService.getConnectThread(bluetoothDevice).start();
				}
		    	
		     }
			
			break;
		}
		
	}
	
	
	Handler handler = new Handler(){
    	public void handleMessage(android.os.Message msg) {
    		msg_tv.setText("");
    		String info = (String)msg.obj;
    		Log.i("aaa", "info="+info);
    		msg_tv.setText(info);
    	};
    };
	
}




	
