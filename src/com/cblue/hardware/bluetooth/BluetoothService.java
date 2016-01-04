package com.cblue.hardware.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

/**
 * 
 * 从客户端到服务端，先连接，后通信
 * 蓝牙的服务类 启动服务端开始监听 客户端开始写内容
 * 
 * @author Administrator
 * 
 */
public class BluetoothService {
	
	
     private BluetoothAdapter bluetoothAdapter;
     private static final UUID my_UUID = UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a66");
     private AcceptThread mAcceptThread;
     private ConnectThread mConnectThread;
     
     private OutputStream output;
     private Context context;
     
     private Handler handler;  /*把发送过来的数据传递给主界面*/
     
     public BluetoothService(Context context,Handler handler){
    	 this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    	 this.context = context;
    	 this.handler = handler;
     }

     public AcceptThread getAcceptThread(){
    	 return mAcceptThread;
     }
     
     public ConnectThread getConnectThread(BluetoothDevice bluetoothDevice){
    	 mConnectThread =  new ConnectThread(bluetoothDevice);
    	 return mConnectThread;
     }
     /**
     * 启动服务端线程
     */
    public synchronized void start(){
    	//TODO 这个还没有试验
    	if(mAcceptThread!=null){
    		mAcceptThread.cancel();
    		mAcceptThread = null;
    	}
    	 mAcceptThread = new AcceptThread();
    	 mAcceptThread.start();
    	 
    	/*if(mConnectThread!=null){
    		mConnectThread.cancle();
    	}*/
    	
     }

    
    /**
	 * 客户端连接服务端
	 * @author Administrator
	 *
	 */
	public class ConnectThread extends Thread{
		
		private BluetoothDevice bluetoothDevice;
		private BluetoothSocket bluetoothSocket;
		public ConnectThread(BluetoothDevice bluetoothDevice){	
		   this.bluetoothDevice = bluetoothDevice;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			 Looper.prepare();
			 try {
				 bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(my_UUID);
				 bluetoothSocket.connect();
				 output = bluetoothSocket.getOutputStream();
				
				 Toast.makeText(context, "连接成功", 1).show();
				// Log.i("aaa", "连接成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					 Toast.makeText(context, "连接失败", 1).show();
					//Log.i("aaa", "连接失败");
					e.printStackTrace();
				}
			 //重新创建服务端的连接
			 if(mAcceptThread!=null){
				 Log.i("aaa", "重新打开服务端");
				 mAcceptThread.start();
			 }
			 Looper.loop();
		}
		
		public void cancle(){
			try {
				bluetoothSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 服务端启动监听
	 * @author Administrator
	 *
	 */
	public class AcceptThread extends Thread {
		
		private BluetoothServerSocket mServerSocket;
		private BluetoothSocket mSocket;
		private InputStream input;
		//private OutputStream output;
		
		public AcceptThread(){
			//加密的蓝牙操作
			try {
				mServerSocket = bluetoothAdapter.listenUsingRfcommWithServiceRecord("BluetoothSecure", my_UUID);     	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				mSocket = mServerSocket.accept();
				input = mSocket.getInputStream();
				//output = mSocket.getOutputStream();
				Log.i("aaa", "output初始化");
				byte[] data = new byte[1024];
				int bytes ;
				while(true){
				   bytes = input.read(data);
				   String result = new String(data,0,bytes);
				   Message msg = Message.obtain();
				   msg.obj = result;
				   handler.sendMessage(msg);
				   Log.i("aaa", "收到数据:"+result);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		public void write(byte[] data){
			try {
				Log.i("aaa", "发送内容");
				output.write(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.i("aaa", "发送失败"+e.toString());
				e.printStackTrace();
			}
		}
		
		public void cancel(){
			if(mSocket!=null){
				try {
					mSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
}
