package com.cblue.savedata.cache;

import android.graphics.Bitmap;
import android.os.Environment;

/**
 * 缓存的管理接口
 * @author Administrator
 *
 */
public interface ICacheManager {
	
	//添加图片缓存
	public boolean addCacheBitmap(String key, Bitmap bitmap);
    //得到图片缓存
	public Bitmap getCacheBitmapByKey(String key);
    //清空缓存
	public void clear();
    //移除缓存
	public void remove(String key);
	
	/**
	 * 缓存相关变量
	 * 
	 */
	public static class CacheConfig {
		/**
		 * SDCard的绝对路径
		 */
		public final static String EXT_PATH = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		/**
		 * LRU缓存最大容量
		 */
		public final static int LRU_MAXSIZE = 8 * 1024 * 1024;
		/**
		 * SDCard最大容量
		 */
		public final static int CAPACITY = 80 * 1024 * 1024;// 80M
		/**
		 * SDCard的cache绝对路径
		 */
		public final static String CACHE_DIR = EXT_PATH + "/cache";

		/**
		 * 图片的最大宽高
		 */
		public final static int IMAGE_MAX_HEIGHT = 1024;
		public final static int IMAGE_MAX_WIDTH = 1024;
	}

	/**
	 * 缓存对象的排序
	 * @author Administrator
	 *
	 */
	public static class CacheEntry implements Comparable<CacheEntry> {
		private String name;
		private long length;
		private long modTime;

		public CacheEntry() {
		}

		public CacheEntry(String name, long length, long modTime) {
			this.name = name;
			this.length = length;
			this.modTime = modTime;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public long getLength() {
			return length;
		}

		public void setLength(long length) {
			this.length = length;
		}

		public long getModTime() {
			return modTime;
		}

		public void setModTime(long modTime) {
			this.modTime = modTime;
		}

		@Override
		public int compareTo(CacheEntry another) {
			//按时间先后进行排序
			if (this.modTime > another.modTime)
				return -1;
			return 1;
		}
	}
}
