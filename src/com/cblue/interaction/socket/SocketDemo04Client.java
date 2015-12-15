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
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * 
 * 首先启动一个线程
 * 线程中使用内部Handler
 * 当我们点击按钮的时候，发送信息给内部Handler，执行写操作
 * 线程中创建子线程，进行读操作，读到内容，发送给主线程的Handler
 * 
 * @author pavel
 *
 */
public class SocketDemo04Client extends Activity {
	
    private EditText et;   
    private Button btn;  
    private TextView tv;
    
    Handler receiveHandler;  
    MyClientThread clientThread;  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.socket_client_demo04);  
        et = (EditText) findViewById(R.id.socket_client_demo04_et);  
        btn = (Button) findViewById(R.id.socket_client_demo04_btn); 
        tv=(TextView)findViewById(R.id.socket_client_demo04_tv);
        
        
        receiveHandler = new Handler() {  
            @Override  
            public void handleMessage(Message msg) {  
                //得到客户端发送给服务端的数据
                if (msg.what == 0x123) {  
                    tv.append("\n" + msg.obj.toString());  
                }  
            }  
        };  
        
     //启动线程进行读取操作
        clientThread = new MyClientThread(receiveHandler);  
        new Thread(clientThread).start();  
        
        //点击按钮把本地信息发送给服务端
        btn.setOnClickListener(new OnClickListener() {  
      	  
            @Override  
            public void onClick(View v) {  
                try {
                	
                    Message msg = new Message();  
                    msg.what = 0x345;  
                    msg.obj = et.getText().toString();  
                    clientThread.sendHandler.sendMessage(msg);  
                    et.setText("");  
  
                } catch (Exception e) {  
  
                }  
            }  
        });  
        
        
    }  

}



class MyClientThread implements Runnable {  
	
    private Socket client;  
     
    //接收消息的Handler
    Handler receiveHandler;  
    
    //发送消息的Handler
    Handler sendHandler;  
     
    
    BufferedReader bufferedReader = null; 
    OutputStream outputStream = null;  
  
    public MyClientThread(Handler receiveHandler) {  
        this.receiveHandler = receiveHandler;  
    }  
  
    @Override  
    public void run() {  
        try {  
        	client = new Socket("169.254.68.122",7000);
        	bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));  
        	outputStream = client.getOutputStream();  
           
        	//创建一个线程去不断的读取服务端发送的信息，一旦读取成功，就发送消息显示在界面中
            new Thread() {  
                @Override  
                public void run() {  
                    String content = null;    
                    try {  
                        while ((content = bufferedReader.readLine()) != null) {
                            Message msg = new Message();  
                            msg.what = 0x123;  
                            msg.obj = content;  
                            receiveHandler.sendMessage(msg);  
                        }  
                    } catch (IOException io) {  
                        io.printStackTrace();  
                    }  
                }  
  
            }.start();  
            
         
            /**
             * 线程中使用消息队列
             * 
             */
            Looper.prepare();  
            
            //接收客户端的信息，发送给服务端
            sendHandler = new Handler() {  
                @Override  
                public void handleMessage(Message msg) {  
                    if (msg.what == 0x345) { 
                        try {  
                        	outputStream.write((msg.obj.toString() + "\r\n")  //必须加上 \r\n
                                    .getBytes("gbk"));  
                        } catch (Exception e) {  
                            e.printStackTrace();  
                        }  
                    }  
                }  
  
            };   
            
            Looper.loop();  
            
  
        } catch (SocketTimeoutException e) {  
        	 Log.i("aaa","Socket连接超时");   
        } catch (IOException io) {  
            io.printStackTrace();  
        }  
  
    }  
}
