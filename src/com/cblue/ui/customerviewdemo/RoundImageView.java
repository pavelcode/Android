package com.cblue.ui.customerviewdemo;


import com.cblue.android.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 自定义圆形图片
 *   自定义属性
 *   得到自定义属性值
 *   把内容画出来  1得到图片宽高，根据比较短的长度，设定为半径
 *               2 根据自定义属性，画内外圆环到画布中
 *               3 画圆形图片到画布中（根据图片的宽高，截取正方形图片，按照圆的半径缩放图片）
 *              
 * @author pavel
 *
 */
public class RoundImageView extends ImageView {
	
	private Context mContext;
	private int border_width; //边框宽度
	private int border_inside_color;//内边框颜色
	private int border_outside_color;//外边框颜色
	
	private int defaultColor= 0xFFFFFFFF;//默认颜色
	private int defaultWidth = 0;
	private int defaultHeight = 0;
	
	private int radius = 0; //圆的半径
	
	
	
	public RoundImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
	}
	
	public RoundImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mContext = context;
		getCustomerAttribute(attrs);
				
	}
	
	public RoundImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		mContext = context;
		getCustomerAttribute(attrs);
	}
	
	private void getCustomerAttribute(AttributeSet attrs){
		TypedArray typeArray =  mContext.obtainStyledAttributes(attrs, R.styleable.round_imageview_attrs);
		//得到自定义的宽度属性
		border_width = typeArray.getDimensionPixelSize(R.styleable.round_imageview_attrs_border_width, 0);
		//得到自定义的内边框颜色属性
		border_inside_color = typeArray.getColor(R.styleable.round_imageview_attrs_border_inside_color, defaultColor);
		//得到自定义的外边框颜色属性
		border_outside_color = typeArray.getColor(R.styleable.round_imageview_attrs_border_outside_color, defaultColor);
		typeArray.recycle();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Drawable drawable = getDrawable();
		if(drawable == null){
			return ;
		}
		if(getWidth()==0||getHeight()==0){
			return;
		}
		//如果是.9图片
		if(drawable.getClass()==NinePatchDrawable.class){
			return;
		}
		//测量图片
		this.measure(0, 0);
		//得到图片的ARGB_8888格式
		Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
		Bitmap newBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
		//得到图片的宽高
		if(defaultWidth==0){
			defaultWidth = getWidth();
		}
		if(defaultHeight==0){
			defaultHeight = getHeight();
		}
		
		//圆的半径等于图片宽高的最短长度-两个边框的宽度，的一半
		//画边框，如果有内外边框，如果有内边框，如果有外边框，如果没边框
		
			//如果有外圆环和内圆环
			if(border_inside_color!=defaultColor&&border_outside_color!=defaultColor){
				radius = (defaultWidth>defaultHeight?defaultHeight:defaultWidth)/2-border_width*2;
		    	//画内圆环
			     drawCircleBoder(canvas, radius+border_width/2, border_inside_color);
			   //画外圆环
				drawCircleBoder(canvas, radius+border_width+border_width/2, border_outside_color);
			}else if(border_inside_color==defaultColor&&border_outside_color!=defaultColor){  //如果有外圆，没内圆
				 radius = (defaultWidth>defaultHeight?defaultHeight:defaultWidth)/2-border_width;
				 drawCircleBoder(canvas, radius+border_width/2, border_inside_color);
			}else if(border_inside_color!=defaultColor&&border_outside_color==defaultColor){//如果有内圆，没外圆
				 radius = (defaultWidth>defaultHeight?defaultHeight:defaultWidth)/2-border_width;
				 drawCircleBoder(canvas, radius+border_width/2, border_inside_color);
			}else{//没有圆环
				radius = (defaultWidth>defaultHeight?defaultHeight:defaultWidth)/2;
			}		
			//得到缩放后的圆形图片
			Bitmap circleBitmap = getCircleBitmap(newBitmap, radius);
			//把圆形图片画到画布上
			canvas.drawBitmap(circleBitmap, defaultWidth/2-radius, defaultHeight/2-radius, null);		
	}
	
	
	
	/**
	 * 得到圆形图片
	 * @param bitmap
	 * @param radius
	 * @return
	 */
	private Bitmap getCircleBitmap(Bitmap bitmap,int radius){
		//直径
		int diameter = radius*2;
		//图片的宽高
		int bmpWidth = bitmap.getWidth();
		int bmpHeight = bitmap.getHeight();
		//正方形图片
		Bitmap squareBitmap;
		int squareWidth = 0;
		int squareHeight =0;
		//按照原直径缩放的图片
		 Bitmap scaledBitmap;
		 //得到正方形图片
		if(bmpWidth>bmpHeight){//如果宽度大于高度,按照宽度画圆
			squareWidth = bmpHeight;
			//以图片中心点为中心，获得截取正方形的x，y坐标
			int x = (bmpWidth-bmpHeight)/2;
			int y = 0;
			squareBitmap = Bitmap.createBitmap(bitmap, x, y, squareWidth, bmpHeight);
		}else if(bmpWidth<bmpHeight){//如果高度大于宽度
			squareHeight = bmpWidth;
			//以图片中心点为中心，获得截取正方形的x，y坐标
			int x =0;
			int y = (bmpHeight-bmpWidth)/2;
			squareBitmap = Bitmap.createBitmap(bitmap, x, y,bmpWidth, squareHeight);
		}else{
			squareBitmap = bitmap;
		}
		//如果图片直径与圆的直径不一致，需要缩放图片
		if(squareBitmap.getWidth()!=diameter||squareBitmap.getHeight()!=diameter){
			//创建缩放图片   最后一个参数如果设置为false，图片就会失真
			scaledBitmap = squareBitmap.createScaledBitmap(squareBitmap, diameter, diameter, true);
		}else{
			scaledBitmap = squareBitmap;
		}
		//生成RGB8888的文件  一个像素32位的图片
		Bitmap RGBitmap = Bitmap.createBitmap(scaledBitmap.getWidth(),scaledBitmap.getHeight(), Config.ARGB_8888);
		//划定一个矩形区域
		Rect rect = new Rect(0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight());
		//创建一个画布
		Canvas canvas = new Canvas(RGBitmap);
		canvas.drawARGB(0, 0, 0, 0);
		
		
		//创建画笔
		Paint paint = new Paint();
		//消除锯齿
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		
		//画一个圆
		canvas.drawCircle(scaledBitmap.getWidth()/2, scaledBitmap.getHeight()/2, scaledBitmap.getWidth()/2, paint);
		//设置两张图片相交的模式 在内部
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		//在画布上添加图片
		canvas.drawBitmap(scaledBitmap, rect, rect, paint);
		//
		bitmap = null;
		squareBitmap = null;
		scaledBitmap = null;
		
		return RGBitmap;
	}
	
	/**
	 * 画圆环
	 * @param canvas
	 * @param radius
	 * @param color
	 */
	private void drawCircleBoder(Canvas canvas,int radius,int color){
		Paint paint = new Paint();
		//去掉锯齿
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		//设置颜色
		paint.setColor(color);
		//设置空心
		paint.setStyle(Paint.Style.STROKE);
		//设置圆环宽度
		paint.setStrokeWidth(border_width);
		canvas.drawCircle(defaultWidth/2, defaultHeight/2, radius, paint);
	}

}
