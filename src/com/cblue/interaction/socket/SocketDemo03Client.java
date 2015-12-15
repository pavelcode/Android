package com.cblue.interaction.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

import com.cblue.android.R;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * 能正常接收到客户端和服务端的交互 但是服务端数据需要先发送，这跟我们需要根据发送的内容反馈不符
 * 不要讲
 * @author pavel
 *
 */
public class SocketDemo03Client extends Activity {
	
	 
	private Button btn;  
    private EditText et;  
    
    private String str;  
    private Socket client = null;  
 
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.socket_client_demo03);  
        btn = (Button) findViewById(R.id.socket_client_demo03_btn);  
        et = (EditText) findViewById(R.id.socket_client_demo03_et);  
        btn.setOnClickListener(new OnClickListener() {  
  
            @Override  
            public void onClick(View v) {  
                str = et.getText().toString();  
                Log.i("aaa", "发送给客户端的数据是："+str); 
                new MyThread(str).start();  
            }  
        });  
  
    }  
  
    class MyThread extends Thread {  
  
        public String content;  
  
        public MyThread(String content) {  
        	this.content = content;  
        }  
  
        @Override  
        public void run() {  
  
            try {   
                client = new Socket("169.254.232.143", 7888);          
                
              //读取服务端发送的数据
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));  
                String line = null;  
                StringBuffer stringBuffer =new StringBuffer();
                while ((line = bufferedReader.readLine()) != null) { 
                	stringBuffer.append(line);
                }  
                Log.i("aaa", "得到服务端发送的数据:"+stringBuffer.toString());
                
                
              //向服务端发送数据
                OutputStream output = client.getOutputStream();  
                output.write(content.getBytes("gbk"));
                output.flush();  
                //关闭流 
                bufferedReader.close();  
                output.close();  
                client.close();  
            } catch (SocketTimeoutException aa) {  
                Log.i("aaa","Socket连接超时");  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  

}
