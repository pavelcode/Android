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
	 * 娓氬鐡�
	 * 閸楁洜鍤庣粙瀣杽閻滅檴CHO  瀵帮拷顓归幋椋庮伂閸欐垿锟介弫鐗堝祦閻ㄥ嫭妞傞崐娆欑礉閸掝偅濡窹rintStream閸愭瑦鍨歅rintWriter
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = null;
		//PrintStream out = null;
		Socket client = null;
		PrintWriter out = null;
		boolean flag = true;
		
		
		ServerSocket server = new ServerSocket(4888);
		while (flag) {
			//瀵版鍩屾潏鎾冲弳閻ㄥ嫭鏆熼幑锟�
			client = server.accept();
			bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String data = bufferedReader.readLine();  //鐎硅妲楅崙娲晩閻ㄥ嫬婀撮弬锟�			System.out.println("data="+data);
			
			if ("bye".equalsIgnoreCase(data)) {
                 flag = false;
			}
			
			OutputStream  os = client.getOutputStream();  
            os.write(("echo:"+data+"\n").getBytes("gbk"));  
			//os.flush();
		    
		}
		//濞夈劍鍓伴崗鎶芥４閻ㄥ嫪缍呯純顔兼躬while娑斿顧�
		/*out.close();
		bufferedReader.close();
		client.close();
		server.close();*/

	}

}
