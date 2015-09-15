package com.cblue.savedata.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;

import android.graphics.Bitmap;

/**
 * 
 *
 * 在抛出内存溢出之前，回收SoftReference中中引用的对象
 *
 */
class SoftRefManager implements ICacheManager{
	
	private static SoftRefManager manager;
	
	private HashMap<String, SoftReference<Bitmap>> map;

	private SoftRefManager() {
		map = new HashMap<String, SoftReference<Bitmap>>();
	}
	/**
	 * 静态方法，得到SoftRefManager实例化对象
	 * @return
	 */
	static SoftRefManager getManager() {
		if(manager == null){
			manager = new SoftRefManager(); 
		}
		return manager;
	}
	
	/**
	 * 根据name找到软引用指向的对象
	 * @param name
	 * @return
	 */
	public Bitmap getCacheBitmapByKey(String name){
		Bitmap bitmap = null;
		SoftReference<Bitmap> softRef = map.get(name);
		if(softRef != null){
			bitmap = softRef.get();
			if(bitmap == null){
				map.remove(name);
			}
		}
		return bitmap;
	}
	/**
	 * 将制定的name-bitmap键值对添加至集合中
	 * @param name
	 * @param bitmap
	 */
	public boolean addCacheBitmap(String name, Bitmap bitmap){
		SoftReference<Bitmap> softRef = new SoftReference<Bitmap>(bitmap);
		map.put(name, softRef);
		return true;
	}
	
	@Override
	public void clear() {
		
	}
	@Override
	public void remove(String key) {
		
	}
}