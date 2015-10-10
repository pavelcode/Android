package com.cblue.ui.customerview;

import com.cblue.android.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class CustomerView extends View  {

	
	private Drawable mDrawable;
	private String textValue;
	public CustomerView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CustomerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.customview_attr);
		mDrawable= typedArray.getDrawable(R.styleable.customview_attr_image);
		textValue = typedArray.getString(R.styleable.customview_attr_text);
		typedArray.recycle();
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		BitmapDrawable bitmapDrawable = (BitmapDrawable)mDrawable;
		Bitmap bitmap = bitmapDrawable.getBitmap();
		Paint paint =new Paint();
		paint.setColor(Color.WHITE);
		paint.setTextSize(18);
		canvas.drawBitmap(bitmap, 0, 0, paint);
		canvas.drawText(textValue,20,bitmap.getHeight()/2, paint);
		
	}

	

	
	
	

}
