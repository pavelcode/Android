package com.cblue.interaction.socket;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现Socket服务端发送数据给客户端
 * @author pavel
 *
 */
public class SocketDemo01Server {


	public static void main(String[] args) throws Exception {
		//创建Socket服务端，端口是7888
		ServerSocket server = new ServerSocket(8888);  
		//等待连接的客户端
		Socket client = server.accept();
		//得到客户端的输出流
		OutputStream out = client.getOutputStream();
		PrintWriter printWrite = new PrintWriter(out);
		printWrite.print("hello");
		printWrite.close();
		out.close();
		client.close();
		server.close();

	}

}
