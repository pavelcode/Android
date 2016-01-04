package com.cblue.ui.customerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 
 * @author Administrator
 *
 */
public class CustomerTextView extends TextView {

	
	private String textValue;
	
	public CustomerTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Set 
	 *    layout_width wrap_content
	 *    layotu_height wrap_content
	 *    text = hello
	 * 
	 * @param context
	 * @param attrs
	 */
	public CustomerTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		textValue = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "text");
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		//super.onDraw(canvas);
		Paint paint = getPaint();
		paint.setColor(Color.RED);
		paint.setFakeBoldText(true);
		canvas.drawText(textValue, 0, getTextSize(), paint);
	}
	

}
