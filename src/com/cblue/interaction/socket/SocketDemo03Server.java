package com.cblue.interaction.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketDemo03Server {
	 public static void main(String[] args) throws IOException {  
	        ServerSocket serivce = new ServerSocket(7000);  
	        while (true) {  
	            //�ȴ�ͻ�������  
	            Socket client = serivce.accept();  
	            new Thread(new AndroidRunable(client)).start();  
	        }  
	    }  
	 
}
class AndroidRunable implements Runnable {  
	  
    Socket client = null;  
  
    public AndroidRunable(Socket client) {  
        this.client = client;  
    }  
  
      
    public void run() {  
        // ��android�ͻ������hello worild  
        String line = null;   
         
        try {  
        	//��ͻ��˷�����Ϣ
        	OutputStream  output = client.getOutputStream();  
        	String str = "hello world!"; 
            output.write(str.getBytes("gbk"));  
            output.flush(); 
          
            //��ر�socket    
            client.shutdownOutput();  
            
            //��ȡ�ͻ��˵���Ϣ 
            InputStream  input = client.getInputStream();   
            BufferedReader bff = new BufferedReader(  
                    new InputStreamReader(input)); 
            while ((line = bff.readLine()) != null) {  
                System.out.print("�õ��ͻ��˵����:"+line); 
                
            }  
      
            //�ر����������  
            output.close();  
            bff.close();  
            input.close();  
            client.close();  
  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
    }  
}  