package com.cblue.interaction.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketDemo04Server {
	
	public static void main(String[] args) throws IOException {  
        ServerSocket server = new ServerSocket(7000);  
        while(true){  
            Socket client=server.accept();
            new Thread(new ServiceThreada(client)).start();     
        }  
    }  
}

class ServiceThreada implements Runnable {  
	   
    Socket client = null; 
    BufferedReader br = null;  
  
    public ServiceThreada(Socket client) {  
        this.client = client;  
        try {
			br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}  
        
    }  
      
    public void run() {  
  
        String content = null;  
        OutputStream  os = null;
        try {
			while((content=br.readLine())!=null){  
			      System.out.println("得到客户端的内容："+content);
			      //把服务端的内容发送给客户端
			      os = client.getOutputStream();  
			      os.write(("echo:"+content+"\n").getBytes("gbk"));   //\n必须添加
			      os.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(os!=null){
				try {
					os.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
		}
    }  
  
}  
