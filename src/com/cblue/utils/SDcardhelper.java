package com.cblue.utils;

import java.io.File;
import java.io.FileOutputStream;

//import org.omg.CORBA.Environment;

public class SDcardhelper {
	/**
	 * 把从网络下载到的文件，保存在SD卡内
	 * 
	 * @param url
	 *            文件的网络地址
	 * @param fileName
	 *            想到保存的文件名
	 */
	/*public static void saveFileFromURLtoSDcard(String url,String fileName){
		String state = Environment.getExternalStorageState();
		if(Environment.MEDIA_MOUNTED.equals(state)){
			// 获取文件绝对路径
			String filePath = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/" + fileName;
			File file = new File(filePath);
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
				fos.write(HttpClientHelper.loadByteFromURL(url));
				fos.flush();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}*/
}
