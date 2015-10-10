package com.cblue.ui.threeffect;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;


/**
 * 旋转3D效果
 * @author pavel
 *
 */
public class RotateEffectActivity extends Activity implements OnTouchListener  {
	private ViewGroup layoutmain;
	private ViewGroup layoutnext;
	private ViewGroup layoutlast;
	
	private RotateAnimation rotate3d;
	private RotateAnimation rotate3d2;
	private RotateAnimation rotate3d3;
	private int mCenterX ;		
	private int mCenterY ;		
	private float degree = (float) 0.0;
	private int currentTab = 0;
	private float perDegree;
	private VelocityTracker mVelocityTracker;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threeffect_rotate);
        initMain();
        DisplayMetrics dm = new DisplayMetrics();
		dm = getResources().getDisplayMetrics();
		mCenterX = dm.widthPixels / 2;
		mCenterY = dm.heightPixels / 2;
		perDegree = (float) (90.0 / dm.widthPixels);
	}
	private void initMain(){
		
        layoutnext = (ViewGroup) findViewById(R.id.layout_next);
        layoutnext.setOnTouchListener(this);
        
        layoutlast = (ViewGroup) findViewById(R.id.layout_last);
        layoutlast.setOnTouchListener(this);

		layoutmain = (ViewGroup)findViewById(R.id.layout_main);
		layoutmain.setOnTouchListener(this);
	}
	
	


	private int mLastMotionX;
	//根据手指的状态以及滑动的速率进行相应的操作
	public boolean onTouch(View arg0, MotionEvent event) {
		int x = (int) event.getX();
		if (mVelocityTracker == null) {
			mVelocityTracker = VelocityTracker.obtain();//获得VelocityTracker类实例
			}
			mVelocityTracker.addMovement(event);//将事件加入到VelocityTracker类实例中
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mLastMotionX = x;
			break;
		case MotionEvent.ACTION_MOVE:
			//初始化速率的单位（第一个参数1000表示一秒（1000毫秒）时间单位内运动了多少个像素，第二个1000表示速率的最大值）
			mVelocityTracker.computeCurrentVelocity(1000, 1000); 
			Log.i("test","velocityTraker :"+mVelocityTracker.getXVelocity());
			int dx = x - mLastMotionX;
			if(dx != 0){
				doRotate(dx);
				if(degree > 90){			
					degree = 0;		
					break;
				}
			}else{
				return false;
			}
			mLastMotionX = x;
			break;
		case MotionEvent.ACTION_UP:
			//设置units的值为1000，意思为一秒时间内运动了多少个像素
			mVelocityTracker.computeCurrentVelocity(1000); 
			float VelocityX = mVelocityTracker.getXVelocity();
			Log.i("test","velocityTraker2:"+mVelocityTracker.getXVelocity());
			if(VelocityX > 500 || VelocityX < -500 ){
				endRotateByVelocity();
			}else{
				endRotate();
			}
			   releaseVelocityTracker();  
               break;  
 
           case MotionEvent.ACTION_CANCEL: 
        	   System.out.println("状态----------->>>>取消");
               releaseVelocityTracker();  
               break;  
		}
		return true;
	}
	
	//VelocityTracker进行释放
	private void releaseVelocityTracker() {
		if(null != mVelocityTracker) {  
            mVelocityTracker.clear();  
            mVelocityTracker.recycle();  
            mVelocityTracker = null;  
        }  
		
	}
	
	//根据不同的旋转速率进行相应的操作（手指抬起，VelocityX > 500 || VelocityX < -500 时调用）
	private void endRotateByVelocity(){
		if(degree > 0){
			rotate3d = new RotateAnimation(degree , 90 , 0, mCenterX, mCenterY);
			rotate3d3 = new RotateAnimation( - 90 + degree,0,0, mCenterX, mCenterY);
			rotate3d.setDuration(300);
			rotate3d3.setDuration(300);
			if(currentTab == 0){
				layoutmain.startAnimation(rotate3d);
				layoutlast.startAnimation(rotate3d3);	
			}else if(currentTab == 2){
				layoutlast.startAnimation(rotate3d);
				layoutnext.startAnimation(rotate3d3);
			}else if(currentTab == 1){
				layoutnext.startAnimation(rotate3d);
				layoutmain.startAnimation(rotate3d3);
			}
			
			currentTab =(currentTab - 1)%3;
			if(currentTab < 0){
				currentTab = 2;
			}
		}else if(degree < 0){
			rotate3d = new RotateAnimation(degree , -90 , 0, mCenterX, mCenterY);
			rotate3d2 = new RotateAnimation( 90 + degree,0,0, mCenterX, mCenterY);
			rotate3d.setDuration(300);
			rotate3d2.setDuration(300);
			if(currentTab == 0){
				layoutmain.startAnimation(rotate3d);
				layoutnext.startAnimation(rotate3d2);	
			}else if(currentTab == 1){
				layoutnext.startAnimation(rotate3d);
				layoutlast.startAnimation(rotate3d2);
			}else if(currentTab == 2){
				layoutlast.startAnimation(rotate3d);
				layoutmain.startAnimation(rotate3d2);
			}
			
			currentTab = (currentTab + 1)%3;
		}
		
		
		System.out.println(">>>>>>>>degree:"+degree +" currentTab:" + currentTab);
		setViewVisibile();
		degree = 0;
	
	}
	//根据不同的旋转速率进行相应的操作（手指抬起时、500> VelocityX > -500 时调用）
	private void endRotate() {
		if(degree > 45){
			rotate3d = new RotateAnimation(degree , 90 , 0, mCenterX, mCenterY);
			rotate3d3 = new RotateAnimation( - 90 + degree,0,0, mCenterX, mCenterY);
			rotate3d.setDuration(300);
			rotate3d3.setDuration(300);
			if(currentTab == 0){
				layoutmain.startAnimation(rotate3d);
				layoutlast.startAnimation(rotate3d3);	
			}else if(currentTab == 2){
				layoutlast.startAnimation(rotate3d);
				layoutnext.startAnimation(rotate3d3);
			}else if(currentTab == 1){
				layoutnext.startAnimation(rotate3d);
				layoutmain.startAnimation(rotate3d3);
			}
			
			currentTab =(currentTab - 1)%3;
			if(currentTab < 0){
				currentTab = 2;
			}
		}else if(degree < -45){
			rotate3d = new RotateAnimation(degree , -90 , 0, mCenterX, mCenterY);
			rotate3d2 = new RotateAnimation( 90 + degree,0,0, mCenterX, mCenterY);
			rotate3d.setDuration(300);
			rotate3d2.setDuration(300);
			if(currentTab == 0){
				layoutmain.startAnimation(rotate3d);
				layoutnext.startAnimation(rotate3d2);	
			}else if(currentTab == 1){
				layoutnext.startAnimation(rotate3d);
				layoutlast.startAnimation(rotate3d2);
			}else if(currentTab == 2){
				layoutlast.startAnimation(rotate3d);
				layoutmain.startAnimation(rotate3d2);
			}
			
			currentTab = (currentTab + 1)%3;
		}else{
			rotate3d = new RotateAnimation( degree , 0 , 0, mCenterX, mCenterY);
			rotate3d2 = new RotateAnimation(  90 + degree,90,0, mCenterX, mCenterY);
			rotate3d3 = new RotateAnimation(  - 90 + degree,- 90,0, mCenterX, mCenterY);
			rotate3d.setDuration(500);
			rotate3d2.setDuration(500);
			rotate3d3.setDuration(500);
			if(currentTab == 0){
				layoutmain.startAnimation(rotate3d);
				layoutnext.startAnimation(rotate3d2);
				layoutlast.startAnimation(rotate3d3);
			}else if(currentTab == 1){
				layoutnext.startAnimation(rotate3d);
				layoutlast.startAnimation(rotate3d2);
				layoutmain.startAnimation(rotate3d3);
			}else if(currentTab == 2){
				layoutlast.startAnimation(rotate3d);
				layoutmain.startAnimation(rotate3d2);
				layoutnext.startAnimation(rotate3d3);
			}
		}
		
		
		System.out.println(">>>>>>>>degree:"+degree +" currentTab:" + currentTab);
		setViewVisibile();
		degree = 0;
	}
	//设置页面的隐藏和显示
	private void setViewVisibile() {
		if(currentTab == 0){
			layoutmain.setVisibility(View.VISIBLE);
			layoutnext.setVisibility(View.GONE);
			layoutlast.setVisibility(View.GONE);
		}else if(currentTab == 1){
			layoutmain.setVisibility(View.GONE);
			layoutnext.setVisibility(View.VISIBLE);
			layoutlast.setVisibility(View.GONE);
		}else if(currentTab == 2){
			layoutmain.setVisibility(View.GONE);
			layoutnext.setVisibility(View.GONE);
			layoutlast.setVisibility(View.VISIBLE);
		}
	}
	//根据x轴坐标的变化设置手指移动时的动画
	private void doRotate(int dx) {
		float xd = degree;
		layoutnext.setVisibility(View.VISIBLE);
		layoutmain.setVisibility(View.VISIBLE);
		layoutlast.setVisibility(View.VISIBLE);
		
		degree += perDegree*dx;
		System.out.println(">>>>>>>>>degree:" + degree );
		rotate3d = new RotateAnimation(xd , degree , 0, mCenterX, mCenterY);
		rotate3d2 = new RotateAnimation( 90 + xd,  90+degree,0, mCenterX, mCenterY);
		rotate3d3 = new RotateAnimation(-90+xd, -90+degree,0, mCenterX, mCenterY);	
		if(currentTab == 0){
			layoutmain.startAnimation(rotate3d);
			layoutnext.startAnimation(rotate3d2);
			layoutlast.startAnimation(rotate3d3);
		}else if(currentTab == 1){
			layoutmain.startAnimation(rotate3d3);
			layoutnext.startAnimation(rotate3d);
			layoutlast.startAnimation(rotate3d2);
		}else if(currentTab == 2){
			layoutmain.startAnimation(rotate3d2);
			layoutnext.startAnimation(rotate3d3);
			layoutlast.startAnimation(rotate3d);
		}
		rotate3d.setFillAfter(true);
		rotate3d2.setFillAfter(true);
		rotate3d3.setFillAfter(true);
	}
}
