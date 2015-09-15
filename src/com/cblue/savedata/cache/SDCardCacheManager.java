package com.cblue.savedata.cache;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

/**
 *      管理SDCard image的缓存
 */
class SDCardCacheManager implements ICacheManager {
	private final static String TAG = "SDCardCacheManager";

	private List<CacheEntry> cachedList;
	private long currentSize; // 当前缓存总大小

	private static SDCardCacheManager manager;
	private static File file; // 缓存文件目录
	private static long CAPACITY; // 缓存文件总大小

	private static int LEVEL = 1;
	private static final int LEVEL_SAFE = 1;
	private static final int LEVEL_MAY_NOTSAFE = 2;
	private static final int LEVEL_NOTSAFE = 3;

	/**
	 * 静态方法，得到SDCardCacheManager实例化对象
	 * 
	 * @return
	 */
	static SDCardCacheManager getManager() {
		if (manager == null) {
			if (!Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				Log.e(TAG, "SDCard不存在或者尚未申请sdcard读写权限!");//error
			} else {
				Log.i(TAG,"sdcard:" + CacheConfig.EXT_PATH);
				file = new File(CacheConfig.CACHE_DIR);
				CAPACITY = CacheConfig.CAPACITY;
				manager = new SDCardCacheManager();
			}
		}
		return manager;
	}

	/**
	 * 私有化的构造函数，完成缓存文件的遍历，初始化集合
	 */
	private SDCardCacheManager() {
		cachedList = new ArrayList<CacheEntry>();
		// updateExternalLevel();
		if (!file.exists()) {
			file.mkdir();
			return;
		}
		for (File f : file.listFiles()) { // 循环遍历图片
			if (f.isFile()) {
				CacheEntry cachedFile = new CacheEntry(f.getName(), f.length(),
						f.lastModified());
				currentSize += f.length();
				cachedList.add(cachedFile);
			}
		}
	}

	/**
	 * 将指定的key-obj键值对保存至SDCard
	 */
	@Override
	public boolean addCacheBitmap(String key, Bitmap bitmap) {
		if (LEVEL > LEVEL_MAY_NOTSAFE) {
			Log.w(TAG, "外设存储不足， 暂不保存缓存");
			return false;
		}
		//File f = new File(file, key);
		File f = new File(key);
		if (f.exists()) { // 如果该缓存文件已经存在
			return true;
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(f);
			bitmap.compress(CompressFormat.PNG, 100, fos);
			currentSize += f.length();
			Log.i(TAG, "保存至SDCard:"+key);
			CacheEntry cachedFile = new CacheEntry(key, f.length(),f.lastModified());
			cachedList.add(cachedFile);

			if (LEVEL > LEVEL_SAFE) {
				updateExternalLevel();
			}
			if (currentSize > CAPACITY) {
				clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
					fos = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	/**
	 * 读取缓存目录中，指定name的bitmap对象
	 * 
	 * @param name
	 * @return
	 */
	@Override
	public Bitmap getCacheBitmapByKey(String key) {
		Bitmap bitmap = null;
		for (CacheEntry cachedFile : cachedList) {
			if (cachedFile.getName().equals(key)) {
				File f = new File(file, key);
				if (f.exists()) {
					f.setLastModified(System.currentTimeMillis()); // 更新file时间
					bitmap = BitmapFactory.decodeFile(f.getAbsolutePath());

					Log.i(TAG, "SDCard读取:"+key);
				}
				return bitmap;
			}
		}
		return bitmap;
	}

	@Override
	public void remove(String key) {

	}

	/**
	 * 清理缓存，当缓存容量超过指定CAPACITY时执行
	 */
	@Override
	public void clear() {
		Collections.sort(cachedList);
		int end = cachedList.size();
		int start = end * 2 / 3; // 清理最久未被使用的1/3
		ListIterator<CacheEntry> it = cachedList.listIterator(start);
		while (it.hasNext()) {
			CacheEntry cachedFile = it.next();
			Log.i(TAG, "缓存空间不足，清理文件:" + cachedFile.getName());
			new File(file, cachedFile.getName()).delete();
			currentSize -= cachedFile.getLength();
			it.remove();
		}
	}
	/**
	 * 根据key得到缓存文件对象
	 */
	public Object getCacheFileByKey(String key) {
		Object obj = null;
		for (CacheEntry cachedFile : cachedList) {
			if (cachedFile.getName().equals(key)) {
				return cachedFile;
			}
		}
		return obj;
	}

	/**
	 * 更新外部存储剩余容量等级<br/>
	 * LEVEL_SAFE = 1 <br/>
	 * LEVEL_MAY_NOTSAFE = 2 <br/>
	 * LEVEL_NOTSAFE = 3<br/>
	 */
	//TODO:该方法存在bug，待更新
	public void updateExternalLevel() {
		//StatFs是文件系统状态（SDCard空间大小和使用情况）
		StatFs statFs = new StatFs(CacheConfig.EXT_PATH);
		int availBlock = statFs.getAvailableBlocks();
		int blockSize = statFs.getBlockSize();
		long availByte = availBlock * blockSize;

		Log.i(TAG, "availByte = " + availByte);
        //可用空间大于30M（安全） 大于5M小于30M（空间低） 否则（无空间）
		if (availBlock * blockSize > 1024 * 1024 * 30) { // 30M
			LEVEL = LEVEL_SAFE;
		} else if (availBlock * blockSize > 1024 * 1024 * 5) { // 5M
			LEVEL = LEVEL_MAY_NOTSAFE;
		} else {
			LEVEL = LEVEL_NOTSAFE;
		}
	}
}
