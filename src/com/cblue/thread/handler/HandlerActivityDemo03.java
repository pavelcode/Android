package com.cblue.thread.handler;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 
 * Handler下载图片，显示出来
 * @author Administrator
 *
 */
public class HandlerActivityDemo03 extends Activity {
	
	public static final String PIC_PATH = "";
	public static final int DOWNLOADFLAG = 1;
	private ImageView imageView;
	private Button button ;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	//下载图片
	public byte[] downLoadPic(String picpath){
		byte[]  b = null;
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(PIC_PATH);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if(httpResponse.getStatusLine().getStatusCode()==200){
				b = EntityUtils.toByteArray(httpResponse.getEntity());
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return b;
	}
	//启动一个线程,进行加载
	public class MyThread implements Runnable{

		@Override
		public void run() {
			
			byte[] imageByte = downLoadPic(PIC_PATH);
			
			Message message = Message.obtain();
			message.obj = imageByte;
			message.what = DOWNLOADFLAG;
			MyHandler myHandler = new MyHandler();
			myHandler.sendMessage(message);
			
		}
	}
	//处理消息,更新UI
	public class MyHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.what==DOWNLOADFLAG){
			  byte data[] = (byte[]) msg.obj;
			  Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			  imageView.setImageBitmap(bitmap);
			}
		}
	}

}
