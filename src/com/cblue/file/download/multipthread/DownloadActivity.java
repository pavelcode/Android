package com.cblue.file.download.multipthread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * 多线程断点续传 举例：10M文件，三个线程下载 
 * 首先实现多线程下载
 * 1 线程计数 0，1，2 
 * 2 文件的总大小 
 * 3 每条线程下载文件的大小=总长度/线程数 
 * 4某条线程下载文件的开始位置 每条线程下载文件的大小*线程的计数+每条线程下载文件的大小 
 *                结束位置 每条线程下载文件的大小*（线程的计数+1）-1
 *                   最后一个线程的结束位置 文件的总大小
 * 其次实现断点续传 （迅雷中，一般有两个文件，一个下载到本地的临时文件，一个是记录下载进度的文件。这里使用一个线程一个文件，是为了让代码思路更加清晰）
 * 把当前下载的位置保存在一个文件中
 * 1 每次写入的位置保存到临时文件中    
 * 2 进入下载，判断是否有临时文件，得到临时文件中的进度
 * 最后删除掉临时文件(如果直接让某个线程执行完成之后，删除文件，会出现重新进入，原本是已经下载完了，这里判断文件不存在，会重新下载)
 * 1 使用变量标识，当变量等于线程个数的时候，说明所有线程都已经下载完毕，然后使用循环删除文件
 * 
 *   
 * @author pavel
 * 
 */
public class DownloadActivity extends Activity {

	/********
	 * 1 获取文件大小 2 在客户端创建一个跟文件大小一致的临时文件
	 */

	private static int threadCount = 3; /* 线程个数 */
	private int downloadSize = 0; /* 每个线程下载文件的大小 */

	private static File saveFile = null; /* 保存的文件 */
	private static int threadFinished =0 ;  /*已完成的线程个数*/
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		try {
			int fileLength = getFileLengthFromNet("");
			boolean flag = createFileByFileLength("", fileLength, "");
			if (flag) {
				//得到每个线程应该下载的文件大小
				downloadSize = fileLength / threadCount;
                //使用每个线程下载
				for (int i = 0; i < threadCount; i++) {
					int startIndex = i * downloadSize;
					int endIndex = (i + 1) * downloadSize-1;
					// 当结束的位置大于文件的总长度的时候，说明文件已经到头了，设置文件的最后位置为文件的结束位置
					if (endIndex > fileLength) {
						endIndex = fileLength;
					}
					Log.i("aaa", "startIndex=" + startIndex + "===endIndex="
							+ endIndex);
					new MyThread(i,startIndex, endIndex).start();
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * 根据得到的网络文件的长度创建本地文件
	 * 
	 * @param fileName
	 *            文件名字
	 * @param fileLength
	 *            文件长度
	 * @param savePath
	 *            保存路径
	 * @return 是否创建成功
	 */
	private boolean createFileByFileLength(String fileName, int fileLength,
			String savePath) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			saveFile = new File(savePath, fileName);
			RandomAccessFile tempRandomAccessFile = new RandomAccessFile(
					saveFile, "rwd");
			tempRandomAccessFile.setLength(fileLength);
			tempRandomAccessFile.close();
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return flag;
	}

	
	
	/**
	 * 从网络上获得文件的长度
	 * 
	 * @param string
	 *            网络文件的URL
	 */
	private int getFileLengthFromNet(String urlStr) {
		// TODO Auto-generated method stub
		return 0;
	}

	class MyThread extends Thread {

	    private int threadId;
		private int startIndex;
		private int endIndex;

		public MyThread(int threadId,int startIndex, int endIndex) {
			this.threadId = threadId;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}

		/**
		 * 线程保存当前的下载进度
		 * @param fileName
		 * @param savaPath
		 * @param data 当前的下载进度
		 */
		private  void writeThreadFile(String fileName,String savaPath,int data){
			try {
				File currentIndexFile = new File(savaPath,fileName);
				RandomAccessFile currentIndexRandomAccessFile = new RandomAccessFile(currentIndexFile, "rwd");
				currentIndexRandomAccessFile.write((data+"").getBytes());
				currentIndexRandomAccessFile.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		/**
		 * 线程读取保存的下载进度
		 * @param fileName
		 * @param savaPath
		 * @return
		 */
		private int  readThreadFile(String fileName,File savaPath){
			try {
				File currentIndexFile = new File(savaPath,fileName);
				if(currentIndexFile.exists()){
				   FileInputStream fileInputStream = new FileInputStream(currentIndexFile);
				   BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
				   int threadStratIndex = Integer.parseInt(bufferedReader.readLine());
				   bufferedReader.close();
				   return threadStratIndex;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			return 0;
		}
		
		/**
		 * 删除线程的保存进度文件
		 * 在删除线程进度文件的时候，如果一个线程下载完毕之后就删除，就在断点续传的时候出现问题。
		 * 当一个线程下载完毕之后，删掉进度文件，这时候，如果重新下载，这个线程文件没有了，就会重新下载。
		 * 所以，必须保证所有线程都下载完毕之后，在一次删除所有文件。
		 * @param fileName
		 * @param savaPath
		 * @return
		 */
		private boolean deleteThreadFile(String fileName,File savaPath){
			boolean flag = false;
			File currentIndexFile = new File(savaPath,fileName);
			if(currentIndexFile.exists()){
				currentIndexFile.delete();
			}
			return flag;
		}
		
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				//判断线程下载文件是否存在，如果存在,给出已下载进度
				int loadedFileIndex = readThreadFile(threadId+".txt",saveFile);
				if(loadedFileIndex>0){
					startIndex =loadedFileIndex;
				}
		
				URL url = new URL("");
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				connection.setRequestMethod("GET");
				connection.setConnectTimeout(5 * 1000);
				connection.setReadTimeout(5 * 1000);
				// 请求的数据的范围
				connection.setRequestProperty("Range", "bytes=" + startIndex
						+ "-" + endIndex);
				 // 206 代表请求部分数据成功
				if (connection.getResponseCode() == 206) {
					InputStream input = connection.getInputStream();
					RandomAccessFile writeRandomAccessFile = new RandomAccessFile(
							DownloadActivity.saveFile, "rwd");
					// 写文件的开始位置
					writeRandomAccessFile.seek(startIndex);

					int total = 0;//当前线程下载数据量
					int length = 0;
					int currentIndex = startIndex; //当前已下载的位置
					byte[] data = new byte[1024];
					while ((length = input.read()) != -1) {
						writeRandomAccessFile.write(data, 0, length);
						total += length;
						Log.i("aaa", threadId+"已经下载了"+total);
						//得到当前文件的位置
						currentIndex +=total;
						writeThreadFile(threadId+".txt","", currentIndex); 
						
					}
					if (writeRandomAccessFile != null) {
						writeRandomAccessFile.close();
					}
					Log.i("aaa", "线程"+threadId+"已经下载完成");
					//每个线程完成之后，都会累加，当所有线程都执行完毕之后，就删除所有临时文件
					DownloadActivity.threadFinished++;
					
					synchronized (DownloadActivity.this) {
						if(DownloadActivity.threadFinished==DownloadActivity.threadCount){
							for(int i=0;i<threadCount;i++){
								deleteThreadFile(i+".txt", new File(""));
							}
						}
					}
					
					
					
					
				}

			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}

}
