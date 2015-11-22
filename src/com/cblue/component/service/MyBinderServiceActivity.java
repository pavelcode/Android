package com.cblue.component.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cblue.android.R;
import com.cblue.component.service.MyBinderService.MyBinder;

/**
 * 绑定Service Activity与Service使用Transact进行数据交互
 * 
 * @author Administrator
 * 
 */
public class MyBinderServiceActivity extends Activity {

	private static String TAG = "MyBinderServiceActivity";
	private Button myStartBinderButton;
	private Button myStopBinderButton;
	private Button translateButton;

	private MyBinder myBinder;
	// 确定服务是否被绑定
	boolean isConnected = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service);
		myStartBinderButton = (Button) findViewById(R.id.but1);
		myStopBinderButton = (Button) findViewById(R.id.but2);
		translateButton = (Button) findViewById(R.id.but3);
		myStartBinderButton.setOnClickListener(listener);
		myStopBinderButton.setOnClickListener(listener);
		translateButton.setOnClickListener(listener);
	}

	private OnClickListener listener = new OnClickListener() {

		public void onClick(View v) {
			Intent intent = new Intent(MyBinderServiceActivity.this,
					MyBinderService.class);
			switch (v.getId()) {
			case R.id.but1:
				// 绑定服务
				isConnected = true;
				bindService(intent, myServiceConnetion,
						Context.BIND_AUTO_CREATE);

				break;

			case R.id.but2:
				// 取消绑定
				if (isConnected) {
					isConnected = false;
					Log.i(TAG, "MyBinderServiceActivity-->unbinderService");
					unbindService(myServiceConnetion);
				}
				break;

			case R.id.but3:
				// 把数据传递给Service,从Service中得到返回的数据
				Parcel data = Parcel.obtain();
				Parcel reply = Parcel.obtain();
				data.writeString("form actvity data");
				try {
					myBinder.transact(0, data, reply, 0);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String replyStr = reply.readString();
                Log.i("aaa", "replyStr="+replyStr);   
				break;
			default:
				break;
			}

		}

	};

	ServiceConnection myServiceConnetion = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			//这个方法只有Service异常销毁时（内存不足），才会被调用
			Log.i(TAG, "MyServiceConnetion-->onServiceDisconnected");

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			Log.i(TAG, "MyServiceConnetion-->onServiceConnected");
			myBinder = (MyBinder) service;
			MyBinderService myBinderService = myBinder.getBinder();
			myBinderService.myMethod();
			isConnected = true;

		}
	};

}
