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
	        ServerSocket serivce = new ServerSocket(7888);  
	        while (true) {  
	            Socket client = serivce.accept();  
	          //向客户端发送数据
	        	OutputStream  output = client.getOutputStream();  
	        	String str = "hello world!"; 
	            output.write(str.getBytes("gbk"));  
	            output.flush(); 
	          
	            //关闭socket的输出流   
	            client.shutdownOutput();
	            
	            //得到客户端发送的数据
	            InputStream  input = client.getInputStream();   
	            BufferedReader bff = new BufferedReader(  
	                    new InputStreamReader(input)); 
	            String line = null;
	            while ((line = bff.readLine()) != null) {  
	                System.out.print("得到客户端发送的内容:"+line); 
	                
	            }  
	            //关闭流
	            //output.close();  
	            bff.close();  
	            input.close();  
	            client.close();    
	        }  
	    }  
	 
}
 