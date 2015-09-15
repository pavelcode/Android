package com.cblue.savedata.internalstorage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;

public class CacheFileTools {

	
	private Context context;
	
	public CacheFileTools(Context context){
		this.context = context;
	}
	public boolean writeCacheFile(String fileName,byte[]data)throws Exception{
		boolean flag = false;
		File cacheFile = context.getCacheDir();
		File myFile = new File(cacheFile,fileName);
		FileOutputStream mFileOutputStream = new FileOutputStream(myFile);
		mFileOutputStream.write(data);
	    flag = true;
		if(mFileOutputStream!=null){
			mFileOutputStream.close();
		}
		return flag;
	}
	
	public byte[] readCacheFile(String fileName)throws Exception{
		//data/data/package/cache
		File cacheFile = context.getCacheDir();
		//data/data/package/cache/fileName
		FileInputStream mFileInputStream = new FileInputStream(new File(cacheFile,fileName));
		// 2创建打印输出流
		ByteArrayOutputStream mByteArrayOutputStream = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		int length = 0;
		// 3 读取文件中的数据
		while ((length = mFileInputStream.read(b)) != -1) {
			// 把数据输出到输出流中
			mByteArrayOutputStream.write(b, 0, length);
			// mByteArrayOutputStream.write(buffer);
		}
		if (mByteArrayOutputStream != null) {
			mByteArrayOutputStream.close();
		}
		if (mFileInputStream != null) {
			mFileInputStream.close();
		}
		// 5 打印输出流
		return mByteArrayOutputStream.toByteArray();
		
		
	}
	
	/**
	 * 自定义文件夹保存
	 */
	public boolean writeCacheFileBySelfFolder(String fileName, byte[] data)
			throws Exception {
		boolean flag =false;
		File file = context.getCacheDir();
		FileOutputStream mFileOutputStream = null;
		// 判断文件夹是否存在
		File folder = new File(file.getAbsolutePath() + File.separator + "aaa");
		// 如果文件夹不存在，进行创建
		if (!folder.exists()) {
			folder.mkdirs();
		}
		// 得到文件夹的绝对路径，写入文件
		mFileOutputStream = new FileOutputStream(folder.getAbsolutePath()
				+ File.separator + fileName);
		mFileOutputStream.write(data, 0, data.length);
		flag = true;
		if (mFileOutputStream != null) {
            mFileOutputStream.close();
		}
		return flag;

	}
}
