package com.cblue.file.download.singlethread;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpStatus;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DownLoadTaskThread extends Thread {

	
	private DownLoadDBDao downLoadDBDao;
	private FileInfo fileInfo;
	private long hasFinished=0;
	private Context context;
	
	
	public DownLoadTaskThread(FileInfo fileInfo,Context context){
		this.fileInfo = fileInfo;
		this.context = context;
		downLoadDBDao = new DownLoadDBDaoImpl(context);
	}
	
	
	@Override
	public void run() {
		//判断数据库中是否有线程信息
		List<ThreadInfo> threadInfos  = downLoadDBDao.getThreads(fileInfo.getUrl());
		//Log.i("aaa", "DownLoadTaskThread  ---threadInfos="+threadInfos);
		ThreadInfo threadInfo = null;
		if(threadInfos.size()==0){
			threadInfo = new ThreadInfo(1, fileInfo.getUrl(),0, fileInfo.getFileLength(), 0);
			//线程信息插入数据库 
			  //判断数据是否存在，不存在，把数据存入
			   if(!downLoadDBDao.isExist(threadInfo.getUrl(), threadInfo.getId())){
				   downLoadDBDao.insert(threadInfo);
			   }
			   Log.i("aaa", "111DownLoadTaskThread  ---threadInfo="+threadInfo);
		}else{
			threadInfo = threadInfos.get(0);
			Log.i("aaa", "2222DownLoadTaskThread  ---threadInfo="+threadInfo);
			
		}
		//设置下载位置
		   HttpURLConnection conn = null;
		   try {
			   URL url = new URL(threadInfo.getUrl());
			   conn = (HttpURLConnection)url.openConnection();
			   conn.setRequestMethod("GET");
			   conn.setConnectTimeout(3*1000);
			   long start = threadInfo.getStartPosition()+threadInfo.getFinishedPosition();
			   Log.i("aaa", "start="+start);
			   conn.setRequestProperty("Range","byte="+start+"-"+threadInfo.getEndPosition());
			 //设定文件写入位置
			   File file = new File(DownLoadInitThread.SAVEFILE_PATH,fileInfo.getFileName());
			   RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
			   randomAccessFile.seek(start);
			   
			   hasFinished += threadInfo.getFinishedPosition();
			   Intent intent = new Intent(DownLoadService.ACTION_BROADCAST);
			    //读取数据
			   if(conn.getResponseCode()==HttpStatus.SC_OK){
				    InputStream input = conn.getInputStream();
				    byte[] data = new byte[1024];
				    int length =-1;
				    long time = System.currentTimeMillis();
				    while((length=input.read(data))!=-1){
				    	 //开始写入
				    	randomAccessFile.write(data, 0, length);
				    	hasFinished+= length;
                        threadInfo.setFinishedPosition(hasFinished);
				    	if(System.currentTimeMillis()-500>0){
				    		time = System.currentTimeMillis();
				    	  //下载进度发送给Activity
				    //	Log.i("aaa", "进度"+hasFinished*100/fileInfo.getFileLength());	
				    	intent.putExtra("finished",hasFinished*100/fileInfo.getFileLength());
				    	context.sendBroadcast(intent);
				    	}
				    	   //下载暂停时，保存下载进度
				     if(DownLoadService.isPause){
				    	 Log.i("aaa", "停止"+threadInfo.getFinishedPosition());
				    	 downLoadDBDao.update(threadInfo.getUrl(),threadInfo.getId(), threadInfo.getFinishedPosition()) ;
				    	 Log.i("aaa", "停止"+threadInfo.getId()+"----"+threadInfo.getUrl());
				    	 return;
				    	 
					   }
			
				    }
				     //下载结束，删除记录
			        Log.i("aaa", "删除");
				    downLoadDBDao.delete(threadInfo.getUrl(), threadInfo.getId());
				  
			   }
			   
			 
			  
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			//关闭所有流
			
		}
		
		
	}
}
