package com.cblue.thread.loadpic;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;


import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

/**
 * 图片的异步加载
 * 
 * @author Administrator
 * 
 */
public class AsyncImageLoader {

	// 图片的保存缓存
	private HashMap<String, SoftReference<Drawable>> imageCache;

	public AsyncImageLoader() {
		imageCache = new HashMap<String, SoftReference<Drawable>>();
	}

	// 定义一个回调接口
	public interface ImageCallBack {
		// 得到图片
		public void obtainImage(String url, Drawable drawable);
	}

	// 得到图片对象（首先从缓存中得到，如果没有使用回调下载图片）
	public Drawable getImage(final String imageUrl,
			final ImageCallBack imageCallBack) {
		Drawable drawable = null;
		// 首先判断缓存中是否有图片对象
		if (imageCache.containsKey(imageUrl)) {
			drawable = imageCache.get(imageUrl).get();
			if (drawable != null) {
				return drawable;
			}
		}

		// 保存图片到缓存
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				Drawable drawable = (Drawable) msg.obj;
				// 加入到缓存之中
				imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
				imageCallBack.obtainImage(imageUrl, drawable);
				super.handleMessage(msg);
			}
		};

		// 线程下载图片
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Drawable drawable = setDrawablePicInfo(imageUrl);
				Message message = Message.obtain();
				message.obj = drawable;
				handler.sendMessage(message);
			}
		}).start();

		return drawable;

	}

	// 下载网络图片
	public Drawable setDrawablePicInfo(String imageUrl) {
		if (imageUrl != null) {
			Drawable drawable = null;
			InputStream input = null;
			try {
				input = HttpUtils.getHttpByJDK(imageUrl);
				if (input != null) {
					drawable = Drawable.createFromStream(input, "");
					return drawable;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;

	}

}
