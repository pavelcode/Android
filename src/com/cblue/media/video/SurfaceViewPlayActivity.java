package com.cblue.media.video;

import java.io.File;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cblue.android.R;

/**
 * 使用4.2版本可以显示图像
 * MediaPlayer+SurfaceHolder播放视频
 * MediaPlayer播放声音
 * SurfaceHolder播放图像
 * 编写思路：首先得到控件和按钮对象，当点击播放按钮的时候，创建一个MediaPalyer对象，设定属性（音频类型,显示对象,数据源）
 * 设置显示对象，需要一个SurfaceHolder对象，如果要得到SurfaceHolder对象，需要使用SurfaceView得到SurfaceHolder的接口
 * 设置SurfaceHolder的属性（回调函数SurfaceHolder.Callback,屏幕大小,视频类型）
 * 
 * 
 * @author Administrator
 *
 */
public class SurfaceViewPlayActivity extends Activity implements SurfaceHolder.Callback {

	Button surface_btn1, surface_btn2, surface_btn3, surface_btn4;

	MediaPlayer mediaPlayer;
	SurfaceView mSurfaceView;
	SurfaceHolder mSurfaceHolder;
	
	// MediaPlayer是否暂停
	private boolean isPaused = false;
	// MediaPlayer是否被释放
	private boolean isReleased = false; 

	public static final String TAG = "SurfaceViewPlayActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.media_surfaceview);

		mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
		surface_btn1 = (Button) findViewById(R.id.surface_btn1);
		surface_btn2 = (Button) findViewById(R.id.surface_btn2);
		surface_btn3 = (Button) findViewById(R.id.surface_btn3);
		surface_btn4 = (Button) findViewById(R.id.surface_btn4);

		/* 设置系统PixelFormae为UNKNOW */
		//getWindow().setFormat(PixelFormat.UNKNOWN);
		// SurfaceHolder是SurfaceView的控制接口
		mSurfaceHolder = mSurfaceView.getHolder();
		// 设置播放时打开屏幕
		// mSurfaceHolder.setKeepScreenOn(true);
		// 这个类实现了SurfaceHolder.Callback接口,完成回调
		mSurfaceHolder.addCallback(this);
		// 显示的分辨率,不设置为视频默认
		mSurfaceHolder.setFixedSize(320, 240);
		// Surface类型 设置surfaceview不维护自己的缓冲区，而是等待屏幕的渲染引擎将内容推送到用户面前
		// deprecated in API level 11. this is ignored, this value is set
		// automatically when needed.
		mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		OnClickListener listener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				// 开始
				case R.id.surface_btn1:
					play();
					break;
				// 暂停
				case R.id.surface_btn2:
				          if (mediaPlayer != null) {
				        	//如果没有被释放，也没有暂停，就暂停，否则就开始  
				            if(!isReleased) {
				              if(!isPaused) {
				            	  mediaPlayer.pause();
				            	  isPaused = true;
				              } else{
				            	  mediaPlayer.start();
				                  isPaused = false;
				              }
				            }
				          }
				          
					break;
				// 重置
				case R.id.surface_btn3:
					//如果没有没有被释放,就跳转到开始位置
				          if(!isReleased){
				            if (mediaPlayer != null)
				            { 
				            	mediaPlayer.seekTo(0);
				            }
				          }
					break;
				// 停止
				case R.id.surface_btn4:
					if (mediaPlayer != null)
			          {
						//如果没有被释放，就停止，并释放
			            if(!isReleased)
			            {
			            	mediaPlayer.stop();
			            	mediaPlayer.release();
			                isReleased = true;
			            }   
			          }
					break;

				}

			}
		};

		surface_btn1.setOnClickListener(listener);
		surface_btn2.setOnClickListener(listener);
		surface_btn3.setOnClickListener(listener);
		surface_btn4.setOnClickListener(listener);
	}

	private void play() {
		try {
			mediaPlayer = new MediaPlayer();
			File file = new File( Environment.getExternalStorageDirectory()+File.separator+"girl.mp4");
            Log.i(TAG, file.getAbsolutePath());
			// 重置为初始状态
			// mediaPlayer.reset();
			// 设置播放的音乐流类型
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			
			// 把视频图像输出到SurfaceView中
			mediaPlayer.setDisplay(mSurfaceHolder);
			// 设置需要播放的视频 播放声音
			mediaPlayer.setDataSource(file.getAbsolutePath());
			// 缓冲
			mediaPlayer.prepare();
			// 播放
			mediaPlayer.start();
		} catch (Exception e) {
			Log.e(TAG, e.toString());
			e.printStackTrace();
		}
	}

	// 图像被创建
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.i(TAG, "surfaceCreated");
	
	}

	// 图像改变
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		Log.i(TAG, "surfaceChanged");

	}

	// 图像销毁
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		// Activity销毁时停止播放，释放资源。不做这个操作，即使退出还是能听到视频播放的声音
		Log.i(TAG, "surfaceDestroyed");
		
	}
	
}
