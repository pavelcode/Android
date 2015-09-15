package com.cblue.savedata.externalstorage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class SDCardFileTools {

	public static final String TAG = SDCardFileTools.class.getSimpleName();

	private Context context;

	public SDCardFileTools(Context context) {
		this.context = context;
	}

	public boolean writeSDCardFile(String fileName, byte[] data)
			throws Exception {
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
			mFileOutputStream.write(data, 0, data.length);
			flag = true;
			if (mFileOutputStream != null) {
				mFileOutputStream.close();
			}

		}
		return flag;
	}

	public byte[] readSDCardFile(String fileName) throws Exception {
		// 判断SD卡的状态
		String state = Environment.getExternalStorageState();
		FileInputStream mFileInputStream;
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			// 得到当前目录的根路径
			File root = Environment.getExternalStorageDirectory();
			Log.i(TAG, "根路径" + root.getAbsolutePath());
			File file = new File(root, fileName);
			if (file.exists()) {
				ByteArrayOutputStream mByteArrayOutputStream = new ByteArrayOutputStream();
				mFileInputStream = new FileInputStream(file);
				byte[] b = new byte[1024];
				int length = 0;
				while ((length = mFileInputStream.read(b)) != -1) {
					mByteArrayOutputStream.write(b, 0, length);
				}
				/*if (mByteArrayOutputStream != null) {
					mByteArrayOutputStream.close();
				}*/
				if (mFileInputStream != null) {
					mFileInputStream.close();
				}
				return mByteArrayOutputStream.toByteArray();
			}

		}
		return null;

	}

	/**
	 * 根据文件的后缀名，把文件保存在不同的文件夹中
	 * 
	 * @param fileName
	 * @param data
	 * @return
	 */
	public boolean saveFileBySuffix(String fileName, byte[] data)
			throws Exception {
		boolean flag = false;
		//保存文件的根目录对象
		File saveFileRoot = null;
		if(fileName!=null){
		// 判断SD卡是否挂载
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			// aaa.mp3 判断不同文件的后缀，得到不同的文件目录对象
			if (fileName.toLowerCase().endsWith("mp3")) {
				saveFileRoot = Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			} else if (fileName.toLowerCase().endsWith("mp4")) {
				saveFileRoot = Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
			} else if (fileName.toLowerCase().endsWith("jpg")
					|| fileName.toLowerCase().endsWith("png")
					|| fileName.toLowerCase().endsWith("gif")) {
				saveFileRoot = Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			} else {

				saveFileRoot = Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			}
			// 写入文件
			FileOutputStream mFileOutputStream = new FileOutputStream(new File(
					saveFileRoot, fileName));

			mFileOutputStream.write(data, 0, data.length);
			flag = true;
			if (mFileOutputStream != null) {
				mFileOutputStream.close();
			}

		}

		}
		return flag;

	}

	/**
	 * 在SD卡中，删除自定义目录一个文件
	 * 
	 * @param folder
	 *            文件所在的目录
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public boolean deleteFileFromSDCard(String folder, String fileName) {
		boolean flag = false;
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			// 得到当前的根路径   mnt/sdcard
			File root = Environment.getExternalStorageDirectory();
			// 得到删除的文件对象   mnt/sdcard/Download/aa.txt
			File file = new File(root.getAbsolutePath()
					+ java.io.File.separator + folder + java.io.File.separator
					+ fileName);
			// 判断要删除的文件是否存在
			if (file.exists()) {
				flag = file.delete();
				flag = true;
			}
		}
		return flag;

	}

}
