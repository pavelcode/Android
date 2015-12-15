package com.cblue.interaction.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketDemo02Server {


	/**
	 * 1 首先接收到客户端向服务端发送数据
	 * 2 但是使用例子1代码，服务端向客户端发送数据 没有成功
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = null;
		Socket client = null;
		boolean flag = true;
		
		ServerSocket server = new ServerSocket(8888);
		while (flag) {
			client = server.accept();
			bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String data = bufferedReader.readLine();  
			System.out.println("得到客户端数据："+data);
		}
		
		bufferedReader.close();
		client.close();
		server.close();

	}

}
