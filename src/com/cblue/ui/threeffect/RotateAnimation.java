package com.cblue.ui.threeffect;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/*Camera就像是一个摄像机，一个物体在原地不动，然后我们带着这个摄像机四处移动，呈现出来的画面就会有立体感*/


public class RotateAnimation extends Animation {
	private float fromDegree; // 旋转起始角度
	private float toDegree; // 旋转终止角度
	private float mCenterX; // 旋转中心x
	private float mCenterY; // 旋转中心y
	private Camera mCamera;

	public RotateAnimation(float fromDegree, float toDegree, float depthZ,
			float centerX, float centerY) {
		this.fromDegree = fromDegree;
		this.toDegree = toDegree;
		this.mCenterX = centerX;
		this.mCenterY = centerY;
	}

	@Override
	public void initialize(int width, int height, int parentWidth,
			int parentHeight) {
		super.initialize(width, height, parentWidth, parentHeight);
		mCamera = new Camera();
	}

	//动画具体的实现方法
	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		final float FromDegree = fromDegree;
		float degrees = FromDegree + (toDegree - fromDegree) * interpolatedTime; // 旋转角度（angle）
		final float centerX = mCenterX;
		final float centerY = mCenterY;
		final Matrix matrix = t.getMatrix();

		if (degrees <= -76.0f) {
			degrees = -90.0f;
			mCamera.save();//保存原状态
			mCamera.rotateY(degrees); // 顺时针旋转
			mCamera.getMatrix(matrix);//
			mCamera.restore();//回复原状态
		} else if (degrees >= 76.0f) {
			degrees = 90.0f;
			mCamera.save();
			mCamera.rotateY(degrees);
			mCamera.getMatrix(matrix);
			mCamera.restore();
		} else {
			mCamera.save();
			mCamera.translate(0, 0, centerX); // 位移x
			mCamera.rotateY(degrees);
			mCamera.translate(0, 0, -centerX);
			mCamera.getMatrix(matrix);
			mCamera.restore();
		}

		matrix.preTranslate(-centerX, -centerY);//是将新的变换矩阵左乘原来的矩阵，实际上定义旋转中心
		matrix.postTranslate(centerX, centerY);//是将新的变换矩阵右乘原来的矩阵
	}
}
