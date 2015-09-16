package com.cblue.file.download.singlethread;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpStatus;

import android.os.Environment;
import android.os.Message;
import android.util.Log;



/**
 * 连接网络，得到文件长度
 * 本地创建文件
 * @author pavel
 *
 */

public class DownLoadInitThread extends Thread {
	
	private FileInfo fileInfo;
	public static final String SAVEFILE_PATH= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
	private DownLoadHandler mDownLoadHandler;
	public DownLoadInitThread(FileInfo fileInfo,DownLoadHandler downLoadHandler){
		this.fileInfo =fileInfo;
		this.mDownLoadHandler = downLoadHandler;
	}
	
	@Override
	public void run() {
		
		 HttpURLConnection conn = null;
		 RandomAccessFile mRandomAccessFile = null;
		try {
			 URL url = new URL(fileInfo.getUrl());
			 conn = (HttpURLConnection)url.openConnection();
			 conn.setRequestMethod("GET");
			 conn.setReadTimeout(3*1000);
			 long fileLength =-1;
			 if(conn.getResponseCode()==HttpStatus.SC_OK){
				 fileLength = conn.getContentLength();
			 }
			 //创建文件
			 if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
				 File saveDir = new File(SAVEFILE_PATH);
				 if(!saveDir.exists()){
					 saveDir.mkdir();
				 }
				// Log.i("aaa", "saveDir="+saveDir.getAbsolutePath());
				 File saveFile = new File(saveDir,fileInfo.getFileName());
				 mRandomAccessFile = new RandomAccessFile(saveFile, "rwd");
				 mRandomAccessFile.setLength(fileLength);
				 fileInfo.setFileLength(fileLength);
				 //传递信息
				 mDownLoadHandler.obtainMessage(DownLoadHandler.ACTION_HANDLER,fileInfo).sendToTarget();
			 }
			
			 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				mRandomAccessFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn.disconnect();
		}
	   
		
		
	}

}
