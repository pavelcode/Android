package com.cblue.savedata.internalstorage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;
import android.util.Log;

public class InternalFileTools {

	public static final String TAG = "InternalFileTools";

	private Context context;

	public InternalFileTools(Context context) {
		this.context = context;
	}

	/**
	 * 内部存储写入文件
	 * 
	 * @param fileName
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean writeInternalFile(String fileName, byte[] data)
			throws Exception {
		boolean flag = false;
		FileOutputStream mFileOutputStream = null;
		mFileOutputStream = context.openFileOutput(fileName,
				Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
		mFileOutputStream.write(data, 0, data.length);
		flag = true;
		if (mFileOutputStream != null) {
			mFileOutputStream.close();

		}
		return flag;
	}
	/**
	 * 读取内部存储器中的文件
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public byte[] readInternalFile(String fileName) throws Exception {
		FileInputStream mFileInputStream = null;
		mFileInputStream = context.openFileInput(fileName);
		ByteArrayOutputStream mArrayOutputStream = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		int length = 0;
		while ((length = mFileInputStream.read(b)) != -1) {
			mArrayOutputStream.write(b, 0, length);
		}
		if (mFileInputStream != null) {
			mFileInputStream.close();
		}
		if (mArrayOutputStream != null) {
			mArrayOutputStream.close();
		}
		return mArrayOutputStream.toByteArray();
	}

	/**
	 * 自定义文件夹保存
	 */
	public boolean writeInternalFileBySelfFolder(String fileName, byte[] data)
			throws Exception {
		boolean flag = false;
		// 得到内部储存器的根目录
		FileOutputStream mFileOutputStream = null;
		//data/data/package/files
		File file = context.getFilesDir();
		// 创建文件夹
		File folder = new File(file.getAbsolutePath() + File.separator + "aaa");
		// 判断文件夹是否存在,如果文件夹不存在，进行创建
		/*
		 * if(Folder.exists()){ Folder.delete(); }
		 */
		if (!folder.exists()) {
			folder.mkdirs();
		}
		Log.i(TAG, folder.getAbsolutePath() + java.io.File.separator + fileName);
		// 写入文件
		// mFileOutputStream =
		// context.(Folder.getAbsolutePath()+java.io.File.separator+fileName,Context.MODE_WORLD_WRITEABLE);
		// 上面的方式写入不了自定义文件夹
		// 得到文件夹的绝对路径，写入文件
		// TODO 注意 不是通过上下文得到输出对象
		mFileOutputStream = new FileOutputStream(new File(
				folder.getAbsolutePath() + java.io.File.separator + fileName));
		mFileOutputStream.write(data, 0, data.length);
		flag = true;
		if (mFileOutputStream != null) {
			mFileOutputStream.close();
		}
		return flag;

	}

	/**
	 * 手机没有经过root，data文件夹列不出
	 * 这个方法有问题，只能列出指定文件夹下的所有目录
	 */
	public void listFile(String folderName) {
		File rootFolder = new File(folderName);
		File[] files = rootFolder.listFiles();
		for (File file1 : files) {
			Log.i(TAG, file1.toString());
		}
	}

	/**
	 * data目录列不出来，只能是mnt目录 递归调用显示文件夹下的所有文件
	 * 
	 * @param file
	 */
	public void listAllFile(File file) {
		if (file.isDirectory()) {
			File listFiles[] = file.listFiles();
			if (listFiles != null) {
				for (File listFile : listFiles) {
					Log.i(TAG, listFile.toString());
					listAllFile(listFile);
				}
			}
		} else {
			Log.i(TAG, file.toString());
		}

	}
}
