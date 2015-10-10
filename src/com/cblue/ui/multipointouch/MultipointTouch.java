package com.cblue.ui.multipointouch;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

/**
 * 
D1 给FrameLayout添加touch事件监听，分辨事件的类型
D2 当我们测试D1的时候，发现了只打印了ACTION_DOWN事件，这是因为返回值，当我们返回true的时候，才会在这里处理所有的操作
D3 打印出坐标点
D4 图片根据手指的位置而移动
D5 得到触摸屏幕的手指数，得到触摸屏幕多根手指的位置
D6图片的缩放效果
 * @author pavel
 *
 */
public class MultipointTouch extends Activity {

	public static final String TAG = MultipointTouch.class.getSimpleName();
	private RelativeLayout mRelativeLayout;
	private ImageView mImageView;

	private float currentDistance;
	private float lastDistance = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.multipointouch);
		mRelativeLayout = (RelativeLayout) findViewById(R.id.multipoint_touch_rl);
		mImageView = (ImageView) findViewById(R.id.multipoint_touch_iv);

		mRelativeLayout.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					Log.i(TAG, "ACTION_DOWN---------");
					break;
				case MotionEvent.ACTION_MOVE:
					Log.i(TAG, "ACTION_MOVE---------");
					float x = event.getX();
					float y = event.getY();
					// 获得触摸点的坐标
					Log.i(TAG, String.format("x:%f,y:%f", x, y));
					// 根据触摸点，修改图片的位置
					RelativeLayout.LayoutParams mLayoutParams = (LayoutParams) mImageView
							.getLayoutParams();
					mLayoutParams.leftMargin = (int) x;
					mLayoutParams.topMargin = (int) y;
					mImageView.setLayoutParams(mLayoutParams);
					// 获得触摸点的个数
					int pointCount = event.getPointerCount();
					Log.i(TAG, "pointCount=" + pointCount);
					// 获得多个触摸点的坐标
					if (pointCount > 1) {
						Log.i(TAG,
								String.format("x:%f,y:%f", event.getX(0),
										event.getY(0), event.getX(1),
										event.getY(1)));
						// 触摸点缩放图片
						// 第一根手指的触摸点与第二根手指的触摸点的X轴距离
						float xDistance = event.getX(0) - event.getX(1);
						// 第一根手指的触摸点与第二根手指的触摸点的y轴距离
						float yDistance = event.getY(0) - event.getY(1);
						// 根据勾股定理，获得两根手指的距离
						currentDistance = (float) Math.sqrt(xDistance
								* xDistance + yDistance * yDistance);
						// 获得手指移动后，两根手指的距离
						// 如果手指没有移动，那么lastDistance是默认值，就设置这个结果的距离等于当前手指间的距离
						if (lastDistance < 0) {
							lastDistance = currentDistance;
						} else if (currentDistance - lastDistance > 5) {
							// 如果当前的距离大于结果的距离，那么说明手指距离变大，这是一个放大的操作
							Log.i(TAG, "图片放大");
							lastDistance = currentDistance;
							//修改图片大小，方法本身的1.1倍
							RelativeLayout.LayoutParams mLayoutParams1 = (LayoutParams) mImageView
									.getLayoutParams();
							mLayoutParams1.width = (int)(1.1f*mImageView.getWidth());
							mLayoutParams1.topMargin = (int) (1.1f*mImageView.getHeight());
							mImageView.setLayoutParams(mLayoutParams1);

						} else if (currentDistance - lastDistance < 5) {
							Log.i(TAG, "图片缩小");
							lastDistance = currentDistance;
							//修改图片大小，方法本身的1.1倍
							RelativeLayout.LayoutParams mLayoutParams2 = (LayoutParams) mImageView
									.getLayoutParams();
							mLayoutParams2.width = (int)(0.9f*mImageView.getWidth());
							mLayoutParams2.topMargin = (int) (0.9f*mImageView.getHeight());
							mImageView.setLayoutParams(mLayoutParams2);
						}

					}

					break;
				case MotionEvent.ACTION_UP:
					Log.i(TAG, "ACTION_UP---------");
					break;
				}

				return true;
			}
		});
	}

}
