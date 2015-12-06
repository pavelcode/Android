package com.cblue.interaction.socket;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketDemo01Server {

	/**
	 * 渚嬪瓙1
	 * @param 缃戠粶缂栫▼
	 * cmd/telnet  
	 * 杩炴帴 open IP鍦板潃  绔彛锛氬悜瀹㈡埛绔櫒绔紶閫掓暟鎹�
	 */
	public static void main(String[] args) throws Exception {
		// 鍒涘缓涓�釜鏈嶅姟绔紝鐩戝惉鍦�888绔彛
		ServerSocket server = new ServerSocket(7888);  
		//涓�釜Socket浠ｈ〃涓�釜杩炴帴瀵硅薄锛�绛夊緟瀹㈡埛绔繛鎺�
		Socket client = server.accept();
		//寰楀埌鍚戝鎴风杈撳嚭娴�
		OutputStream out = client.getOutputStream();
		PrintWriter printWrite = new PrintWriter(out);
		printWrite.print("hello");
		printWrite.close();
		out.close();
		client.close();
		server.close();

	}

}
