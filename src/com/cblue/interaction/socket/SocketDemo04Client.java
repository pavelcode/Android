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


public class SocketDemo04Client extends Activity {
	
    EditText et;   
    Button btn;  
    private TextView tv;
    Handler handler;  
    // �����������ͨ�ŵ����߳�  
    MyClientThread clientThread;  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.socket_client_demo04);  
        et = (EditText) findViewById(R.id.socket_client_demo04_et);  
        btn = (Button) findViewById(R.id.socket_client_demo04_btn); 
        tv=(TextView)findViewById(R.id.socket_client_demo04_tv);
        
        
        handler = new Handler() {  
            @Override  
            public void handleMessage(Message msg) {  
                // �����Ϣ�������߳�  
                if (msg.what == 0x123) {  
                    // ����ȡ������׷����ʾ���ı�����  
                    tv.append("\n" + msg.obj.toString());  
                }  
            }  
        };  
        
     // �ͻ�������ClientThread�̴߳����������ӡ���ȡ���Է����������  
        clientThread = new MyClientThread(handler);  
        new Thread(clientThread).start();  
        
        btn.setOnClickListener(new OnClickListener() {  
      	  
            @Override  
            public void onClick(View v) {  
                try {  
                    // ���û����°�ť֮�󣬽��û��������ݷ�װ��Message  
                    // Ȼ���͸����߳�Handler  
                    Message msg = new Message();  
                    msg.what = 0x345;  
                    msg.obj = et.getText().toString();  
                    clientThread.revHandler.sendMessage(msg);  
                    et.setText("");  
  
                } catch (Exception e) {  
  
                }  
            }  
        });  
        
        
    }  

}
class MyClientThread implements Runnable {  
    private Socket s;  
    
    // ������UI�̷߳�����Ϣ��Handler����  
    Handler handler;  
    // �������UI�̵߳�Handler����  
    Handler revHandler;  
    
    // ���̴߳���Socket����õ����������  
    BufferedReader br = null; 
    OutputStream os = null;  
  
    public MyClientThread(Handler handler) {  
        this.handler = handler;  
    }  
  
    @Override  
    public void run() {  
        try {  
           
        	s = new Socket("10.211.55.8",7000);
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));  
            os = s.getOutputStream();  
            // ����һ�����߳�����ȡ��������Ӧ�����  
            new Thread() {  
                @Override  
                public void run() {  
                    String content = null;  
                    // ���ϵĶ�ȡSocket������������  
                    try {  
                        while ((content = br.readLine()) != null) {  
                            // ÿ����ȡ�����Է����������֮�󣬷��͵���Ϣ֪ͨ����  
                            // ������ʾ�����  
                            Message msg = new Message();  
                            msg.what = 0x123;  
                            msg.obj = content;  
                            handler.sendMessage(msg);  
                        }  
                    } catch (IOException io) {  
                        io.printStackTrace();  
                    }  
                }  
  
            }.start();  
            // Ϊ��ǰ�̳߳�ʼ��Looper  
            Looper.prepare();  
            
            // ����revHandler����  
            revHandler = new Handler() {  
                @Override  
                public void handleMessage(Message msg) {  
                    // ���յ�UI�̵߳����û���������  
                    if (msg.what == 0x345) {  
                        // ���û����ı������������д������  
                        try {  
                            os.write((msg.obj.toString() + "\r\n")  
                                    .getBytes("gbk"));  
                        } catch (Exception e) {  
                            e.printStackTrace();  
                        }  
                    }  
                }  
  
            };   
            // ����Looper  
            Looper.loop();  
  
        } catch (SocketTimeoutException e) {  
        	 Log.i("aaa","����������ʧ�ܣ����������Ƿ��");  
        } catch (IOException io) {  
            io.printStackTrace();  
        }  
  
    }  
}
