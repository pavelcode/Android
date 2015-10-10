package com.cblue.ui.threeffect;


import com.cblue.android.R;

import android.app.Activity;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.TextView;

/**
 * 最简单的3D效果实现
 * @author pavel
 *
 */
public class SampleEffectActivity extends Activity {
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.threeffect_simple);
		
		tv = new TextView(this);
		tv.setText("3D Rotate");
    	Rotate3d rotate = new Rotate3d();
    	rotate.setDuration(5000);
    	tv.measure(0, 0);
    	rotate.setCenter(tv.getMeasuredWidth() / 2, tv.getMeasuredHeight() / 2);
    	rotate.setFillAfter(true);
    	tv.startAnimation(rotate);
    	setContentView(tv);
		
	}
	
	class Rotate3d extends Animation {
		
	    private float mCenterX = 0;
	    private float mCenterY = 0;
	        
	    public void setCenter(float centerX, float centerY) {
	        mCenterX = centerX;
	        mCenterY = centerY;
	    }
		
	    @Override
	    protected void applyTransformation(float interpolatedTime, Transformation t) {
	        Matrix matrix = t.getMatrix();
	        Camera camera = new Camera();
	        camera.save();

	        // 设置camera动作为绕Y轴旋转
	        // 总共旋转180度，因此计算在每个补间时间点interpolatedTime的角度即为两着相乘
	        camera.rotateY(180 * interpolatedTime); 

	        // 根据camera动作产生一个matrix，赋给Transformation的matrix，以用来设置动画效果
	        camera.getMatrix(matrix);

	        camera.restore();
	        matrix.preTranslate(-mCenterX, -mCenterY);
	        matrix.postTranslate(mCenterX, mCenterY);
	    }
	}

}
