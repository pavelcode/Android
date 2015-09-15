package com.cblue.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

public class BitmapUtils {
	
	
	/** 
	* 图片变为圆角 
	* @param bitmap:传入的bitmap 
	* @param pixels：圆角的度数，值越大，圆角越大 
	* @return bitmap:加入圆角的bitmap 
	*/ 
	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) 
	{ 
	if(bitmap == null) 
	return null; 
	Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888); 
	Canvas canvas = new Canvas(output); 
	final int color = 0xff424242; 
	final Paint paint = new Paint(); 
	final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()); 
	final RectF rectF = new RectF(rect); 
	final float roundPx = pixels; 
	paint.setAntiAlias(true); 
	canvas.drawARGB(0, 0, 0, 0); 
	paint.setColor(color); 
	canvas.drawRoundRect(rectF, roundPx, roundPx, paint); 
	paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN)); 
	canvas.drawBitmap(bitmap, rect, rect, paint); 
	return output; 
	} 

	
	

}
