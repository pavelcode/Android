package com.cblue.savedata.cache;



import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

public class AppCacheManager implements ICacheManager {
	
	
	private final static String TAG = AppCacheManager.class.getSimpleName();
	
	private static ICacheManager manager;
	/**
	 * 在使用sdcard时，应该首先判断sdcard是否存在且挂载可用，如果使用只需要判断 if(sdManager != null); 
	 */
	private static SDCardCacheManager sdManager;
	private static SoftRefManager refManager;
	private static LruCacheManager lruManager;
	
	private AppCacheManager() {
	}

	public static ICacheManager getManager() {
		if (manager == null) {
			manager = new AppCacheManager();

			sdManager = SDCardCacheManager.getManager();
			refManager = SoftRefManager.getManager();
			lruManager = LruCacheManager.getManager(new LruCacheList(CacheConfig.LRU_MAXSIZE));
			// 相应manager缓存管理对象
		}
		return manager;
	}

	private static class LruCacheList extends LruCache<String, Bitmap> {
		public LruCacheList(int maxSize) {
			super(maxSize);
		}

		@Override
		protected int sizeOf(String key, Bitmap value) {
			return value.getRowBytes() * value.getHeight();
		}

		@Override
		protected void entryRemoved(boolean evicted, String key,
				Bitmap oldValue, Bitmap newValue) {
			if (evicted) {
				Log.i(TAG, "缓存被挤出:"+key);
				//如果sdCard存在，写入sdCard
				if(sdManager != null){	
					sdManager.addCacheBitmap(key, oldValue);
				}
				// 2.添加至软引用集合
				refManager.addCacheBitmap(key, oldValue);
			}
		}
	}

	/**
	 * 将key-obj键值对存入
	 */
	@Override
	public boolean addCacheBitmap(String key, Bitmap bitmap) {
		if (key == null || bitmap == null) {
			return false;
		}
		if(sdManager != null){	//如果sdcard存在可用，存入sdcard
			sdManager.addCacheBitmap(key, bitmap);
		}
		lruManager.addCacheBitmap(key, bitmap);
		return true;
	}

	/**
	 * 得到缓存对象根据key,如果返回为null需要访问网络获取数据
	 */
	@Override
	public Bitmap getCacheBitmapByKey(String key) {
		if(key == null){
			return null;
		}
		Bitmap bitmap = lruManager.getCacheBitmapByKey(key);
		if (bitmap == null) {
			bitmap = refManager.getCacheBitmapByKey(key);
			if (bitmap==null && sdManager!=null) {	//从sdcard中取数据
				bitmap = sdManager.getCacheBitmapByKey(key);
			}
		}
		if (bitmap != null) {
			lruManager.addCacheBitmap(key, bitmap);
		}
		return bitmap;
	}

	@Override
	public void clear() {
		lruManager.clear();
		refManager.clear();
		
		if(sdManager != null){
			sdManager.clear();
		}
	}

	@Override
	public void remove(String key) {
		lruManager.remove(key);
		refManager.remove(key);
		
		if(sdManager != null){
			sdManager.remove(key);
		}
	}
}
