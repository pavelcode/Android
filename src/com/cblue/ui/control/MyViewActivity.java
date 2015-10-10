package com.cblue.ui.control;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;

import com.cblue.android.R;

public class MyViewActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myview);
		FrameLayout frameLayout = (FrameLayout)findViewById(R.id.myviewFL);
		final Robit robit = new Robit(MyViewActivity.this);
	    robit.setOnTouchListener(new OnTouchListener()
		{
			public boolean onTouch(View v, MotionEvent event)
			{
				robit.setX_point(event.getX());
				robit.setY_point(event.getY());
				robit.invalidate();  //�ػ�robit
				return true;
			}
		});
	  frameLayout.addView(robit);
	}
}


class Robit extends View{
	
	private float x_point;
	public float getX_point()
	{
		return x_point;
	}

	public void setX_point(float x_point)
	{
		this.x_point = x_point;
	}

	public float getY_point()
	{
		return y_point;
	}

	public void setY_point(float y_point)
	{
		this.y_point = y_point;
	}

	private float y_point;

	public Robit(Context context)
	{
		super(context);
		x_point=100;
		y_point=100;
	}

	@Override
	public void draw(Canvas canvas)
	{
		super.draw(canvas);
		Paint paint = new Paint();
		Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.button_normal_green);
		canvas.drawBitmap(bitmap, x_point, y_point, paint);
	    if(bitmap.isRecycled())
	    {
	    	bitmap.recycle();
	    }
	}
	
	
}