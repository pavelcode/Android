package com.cblue.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

public class ScreenUtils {
	
	/**
	 * 返回屏幕的宽度和高度 return the width and height of screen
	 * 
	 * @param activity
	 * @return
	 */
	public static int[] getScreenWidthHeight(Activity activity) {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay()
				.getMetrics(displayMetrics);
		return new int[] { displayMetrics.widthPixels,
				displayMetrics.heightPixels };
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dipToPx(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int pxToDip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

}
