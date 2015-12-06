package com.cblue.interaction.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * 客户端发送数据到服务端
 * 
 * 但是如果要想从客户端再得到数据，如果只是简单的使用Demo01的方式，是不会成功的
 * @author Administrator
 *
 */
public class SocketDemo02Client extends Activity {
	
	private EditText editText;
	private Button btn;
	Socket client;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.socket_client_demo02);
		editText = (EditText)findViewById(R.id.socket_client_demo02_et);
		btn = (Button)findViewById(R.id.socket_client_demo02_btn);
	
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
					
						String value = editText.getText().toString();
						Log.i("aaa", "客户端发送的内容是："+value);
						try {
						client = new Socket("10.211.55.8",4888);
						
						BufferedReader buf = new BufferedReader(
								new InputStreamReader(client
										.getInputStream()));
						//向服务端发送信息
						OutputStream outputStream = client.getOutputStream();
						DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
						dataOutputStream.write(value.getBytes());
						dataOutputStream.flush();
						
					
						// 得到从服务端发送信息
						Log.i("aaa", "1111");
						Log.i("aaa", "input.read()="+buf.read());
						/*String line = null;  
		                String buffer="";  
		                while ((line = bff.readLine()) != null) {  
		                    buffer = line + buffer;  
		                    Log.i("aaa", "line="+line);
		                }  */
		                Log.i("aaa", "222");
						//buf.close();
						
		               // outputStream.close();
		                //client.close();
						
						
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					
					 
					 
					 
					}
				}).start();
				
			}
		});
	}
	
	
	


}
