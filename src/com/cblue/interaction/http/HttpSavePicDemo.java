package com.cblue.interaction.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Environment;
import android.util.Log;

/**
 * 保存图片到SDCard
 * 记得添加SDCard的写权限
 * @author pavel
 *
 */
public class HttpSavePicDemo {
	
	
	
	/**
	 * 得到图片的输入流
	 * @param urlStr
	 * @return
	 */
	public static InputStream getPic(String urlStr){
		
		HttpURLConnection conn = null;
		try
		{
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setConnectTimeout(5*1000);
			conn.setRequestMethod("GET");
			conn.connect();
			
			int responseCode = conn.getResponseCode();
			if (responseCode == 200)
			{
				InputStream inputStream = conn.getInputStream();
				return inputStream;
			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		/*finally
		{
			conn.disconnect();
		}*/
		return null;
		
	}
	
	/**
	 * 把图片保存在SDCard中
	 * @param input
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static boolean savePicInSDCard(InputStream input,String fileName)throws Exception {
			boolean flag = false;
			// 判断SD卡的状态
			String state = Environment.getExternalStorageState();
			FileOutputStream mFileOutputStream;
			// SD卡是否挂载成功
			if (Environment.MEDIA_MOUNTED.equals(state)) {
				// 得到SD卡的根目录 /storage/sdcard
				File root = Environment.getExternalStorageDirectory();
				Log.i("SDCardFileTools", root.toString());
				// 创建写入文件对象
				File file = new File(root, fileName);
				mFileOutputStream = new FileOutputStream(file);
			    byte data[] = new byte[1024];
			    int len=0;
				while((len=input.read(data))!=-1){
					mFileOutputStream.write(data, 0, len);
				}
				flag = true;
				if (mFileOutputStream != null) {
					mFileOutputStream.close();
				}

			}
			return flag;
		}
	

}
