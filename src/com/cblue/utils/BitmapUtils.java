package com.cblue.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.BitmapFactory;
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
	
	/**
	 * 一个测试方法：查看图片的大小，以防止图片内存溢出，图片大小优化
	 * @param filePath
	 * @return
	 */
	public static String getImageInfo(String filePath){
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;//对图片解码的时候不分配内存
		BitmapFactory.decodeFile(filePath, opts);
		int imageWidth = opts.outWidth;
		int imageHeight = opts.outHeight;
		String imageType = opts.outMimeType;
		return "imageWidth="+imageWidth+";imageWidth="+imageHeight+";imageType="+imageType;
	}
	
	
	
	/**
	 * 计算图片的缩放比率
	 * @param opts
	 * @param reqWidth 期望图片的宽度
	 * @param reqHeight
	 * @return
	 */
	public static int calculateInSampleSize(BitmapFactory.Options opts,int reqWidth,int reqHeight){
		final int width = opts.outWidth; /*图片真正的宽度*/
		final int height = opts.outHeight;
		int inSampleSize =1;
		if(width>reqWidth||height>reqHeight){
			final int halfWidth = width/2;  
			final int halfHeight = height/2;
			while((halfWidth/inSampleSize)>reqWidth&&(halfHeight/inSampleSize)>reqHeight){
				inSampleSize*=2;
			}
		}
		return inSampleSize;
	}
	
	
	/**
	 * 得到图片的缩略图
	 * @param filePath
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static Bitmap decodeSimpleBitmap(String filePath,int reqWidth,int reqHeight){
		//获得图片的大小
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, opts);
		
		//计算图片的缩放比例
		opts.inSampleSize = calculateInSampleSize(opts, reqWidth, reqHeight);
		
		//获得缩略图
		opts.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(filePath, opts);
	}
	


	
	

}
