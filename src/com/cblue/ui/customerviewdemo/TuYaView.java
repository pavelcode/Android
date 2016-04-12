package com.cblue.ui.customerviewdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 
 * 自定义View实现的涂鸦效果
 * 1 首先在构造函数中得到屏幕的大小  根据屏幕大小创建一个图片，把图片放到画板中，并准备好笔
 * 2 当我们按下屏幕的时候，把路径指定到坐标点上。当我们移动手指的时候，开始画连线。当我们手指抬起的时候，把这个线画到图片上
 * 3 把整个图片，使用画笔画到画板上
 * 
 * @author pavel
 *
 */
public class TuYaView extends View {
	
	//定义屏幕的宽 高
	private int screenWidth,screenHeight;
	//创建一个和屏幕大小一致的图片
	private Bitmap mBitmap;
	//画板对象
	private Canvas canvas;
	//画笔对象
	private Paint mPaint;
	//每一条画笔的路径
	private Path path;
	//临时的坐标点
	private float mX,mY;
	

	public TuYaView(Context context,int Width,int Height) {
		super(context);
		this.screenWidth = Width;
		this.screenHeight = Height;
        //创建一张图片对象
		mBitmap = Bitmap.createBitmap(this.screenWidth, this.screenHeight, Bitmap.Config.ARGB_8888);
		//创建一个画板
		canvas = new Canvas(mBitmap);
		//创建一个真实画笔
		mPaint = new Paint();
		mPaint.setAntiAlias(true);//画笔的平滑效果
		mPaint.setStyle(Paint.Style.STROKE); //实线
		mPaint.setStrokeWidth(5);//画笔宽度
	
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		 float x = event.getX();
		 float y = event.getY();
		 Log.i("aaa", "onTouchEvent");
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			//每次按下的时候创建一个路径
			path = new Path();
			start_move(x,y);
			invalidate();
			break;

		case MotionEvent.ACTION_MOVE:
			moving(x, y);
			invalidate();
			break;
			
		case MotionEvent.ACTION_UP:
			move_end();
			invalidate();
			break;
		}
		return true;
	}
	
	/**
	 * 开始移动
	 */
	private void start_move(float x,float y){
		//路径移动到手指按下的位置
		path.moveTo(x, y);
		mX = x;
		mY = y;
	}
	
	private void moving(float x,float y){
		//移动后，两点间距离
		float dx = Math.abs(x-mX);
		float dy = Math.abs(mY-y);
		//如果横坐标的距离或纵坐标的距离大于4的话，画出连接线
		if(dx>=4||dy>=4){
			path.lineTo(x,y);
			mX = x;
			mY = y;
		}
		
	}
	
	private void move_end(){
		path.lineTo(mX, mY);
		//在画板上使用画笔画出路径
		canvas.drawPath(path, mPaint);
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Log.i("aaa", "onDraw");
		//把整张图画到画板上
		canvas.drawBitmap(mBitmap, 0, 0, mPaint);
	}

}
