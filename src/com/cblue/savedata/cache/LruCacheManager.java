package com.cblue.savedata.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * 
 *
 * LruCache缓存，由于指定了该缓存的最大值，可以方便管理内存中的缓存图片，
 * 关键的方法，为sizeOf() 和   entryRemoved()
 *
 */
class LruCacheManager implements ICacheManager{
	private static LruCacheManager manager;

	private static LruCache<String, Bitmap> lruCache;

	private LruCacheManager() {}

	/**
	 * 静态方法，得到SDCardCacheManager实例化对象
	 * 跟SoftRefManager不同的写法
	 * @return
	 */
	static LruCacheManager getManager(LruCache<String, Bitmap> cache) {
		if (manager == null) {
			manager = new LruCacheManager();
			lruCache = cache;
		}
		return manager;
	}

	public Bitmap getCacheBitmapByKey(String name) {
		return lruCache.get(name);
	}

	public boolean addCacheBitmap(String name, Bitmap bitmap) {
		if (name != null && bitmap != null) {
			lruCache.put(name, bitmap);
			return true;
        }
		return false;
	}

	@Override
	public void clear() {

	}

	@Override
	public void remove(String key) {

	}
}
