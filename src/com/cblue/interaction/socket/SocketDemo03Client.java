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


public class SocketDemo03Client extends Activity {
	
	 
	Button btn;  
    EditText et;  
    
    String str;  
    
    Socket client = null;  
 
  
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
                Log.i("aaa", "��ͻ��˷��͵���Ϣ��"+str);
                //�����߳� ����������ͺͽ�����Ϣ  
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
                //���ӷ����� ���������ӳ�ʱΪ5��  
                client = new Socket("10.211.55.8", 7000);          
                //��ȡ���������  
                OutputStream output = client.getOutputStream();  
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));  
               
                //��ȡ������������Ϣ  
                String line = null;  
                StringBuffer stringBuffer =new StringBuffer();
                while ((line = bufferedReader.readLine()) != null) { 
                	stringBuffer.append(line);
                }  
                Log.i("aaa", "�õ�����˷��͵���Ϣ:"+stringBuffer.toString());
                //�������������Ϣ  
               // ou.write("android �ͻ���".getBytes("gbk"));  
                output.write(content.getBytes("gbk"));
                output.flush();  
                
                //�ر���  
                bufferedReader.close();  
                output.close();  
                client.close();  
            } catch (SocketTimeoutException aa) {  
                //���ӳ�ʱ 
                Log.i("aaa","����������ʧ�ܣ����������Ƿ��");  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  

}
