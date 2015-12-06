package com.cblue.interaction.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * 简单的连接客户端，得到服务端发送给客户端数据
 * 
 * 注意 1.添加网络权限 2. android4.4之后，就必须使用线程访问网络操作
 * 
 * @author pavel
 * 
 */
public class SocketDemo01Client extends Activity {

	private Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.socket_client_demo01);
		btn = (Button) findViewById(R.id.socket_client_demo01_btn);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							// TODO Auto-generated method stub
							Socket client = new Socket("10.211.55.8", 8888);
							// 得到从服务端接收信息
							BufferedReader buf = new BufferedReader(
									new InputStreamReader(client
											.getInputStream()));
							Log.i("aaa",buf.readLine());
							buf.close();
							client.close();
						} catch (Exception e) {

						}
					}
				}).start();
			}
		});

	}

}
