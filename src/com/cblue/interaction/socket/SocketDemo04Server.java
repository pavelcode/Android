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
	
	// ���屣�����е�Socket  
   // public static List<Socket> socketList = new ArrayList<Socket>();  
  
    public static void main(String[] args) throws IOException {  
        ServerSocket server = new ServerSocket(7000);  
        while(true){  
            Socket s=server.accept();  
           // socketList.add(s);  
            //ÿ���ͻ�������֮������һ��ServerThread�߳�Ϊ�ÿͻ��˷���  
            new Thread(new ServiceThreada(s)).start();     
        }  
    }  
  

}

class ServiceThreada implements Runnable {  
	  
    // ���嵱ǰ�̴߳����Socket  
    Socket s = null;  
    // ���߳������Socket���Ӧ��������  
    BufferedReader br = null;  
  
    public ServiceThreada(Socket s) {  
        this.s = s;  
        try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        
    }  
      
    public void run() {  
  
        String content = null;  
        //����ѭ�����ϵĴ�Socket�ж�ȡ�ͻ��˷��͹��������  
        try {
			while((content=br.readLine())!=null){  
			    //����socketList�е�ÿ��Socket  
			    //����ȡ��������ÿ����Socket����һ��  
			  //  for(Socket s:AndroidService01.socketList){  
			        //OutputStream os;  
			        try {  
			        	System.out.println("�ӿͻ��õ����ݣ�"+content);
			        	OutputStream  os = s.getOutputStream();  
			            os.write(("echo:"+content+"\n").getBytes("gbk"));  
			        } catch (IOException e) {  
			            // TODO Auto-generated catch block  
			            e.printStackTrace();  
			        }  
			          
			  //  }  
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
  
    }  
  
    // �����ȡ�ͻ��˵���Ϣ  
    public String readFromClient() {  
        try {  
            return br.readLine();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
}  
