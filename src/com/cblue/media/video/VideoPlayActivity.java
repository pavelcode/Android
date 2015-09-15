package com.cblue.media.video;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.VideoView;

import com.cblue.android.R;

 /**
  * 注释掉的：老罗写的，就是自己实现了一下《控制》，基本没什么用，只是这样写，就可以自定义操作
  * 
  * 4.2之前的版本，看不到视频图像
  * @author Administrator
  *
  *
  *
  */


//implements MediaPlayerControl
public class VideoPlayActivity extends Activity  {

	VideoView videoView;
	MediaController controller;
	public static final String TAG = "VideoPlayActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.media_videoview);
		videoView = (VideoView) findViewById(R.id.videoView);
		// 添加控制器
	    controller = new MediaController(this);
		videoView.setMediaController(controller);
		//试过 不设置也可以成功
		//controller.setMediaPlayer(videoView);
		String path = Environment.getExternalStorageDirectory()+File.separator+"girl.mp4";
		Log.i(TAG, path);
		videoView.setVideoPath(path);
		//videoView.setVideoURI(Uri.parse(pathString));
		//设置请求焦点
		videoView.requestFocus();   
		videoView.start();
		

	}

	/*@Override
	public void start() {
		// TODO Auto-generated method stub
		videoView.start();
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		if(videoView.isPlaying()){
			videoView.pause();
		}
		
	}

	@Override
	public int getDuration() {
		// TODO Auto-generated method stub
		return videoView.getDuration();
	}

	@Override
	public int getCurrentPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void seekTo(int pos) {
		// TODO Auto-generated method stub
		videoView.seekTo(pos);
		
	}

	@Override
	public boolean isPlaying() {
		// TODO Auto-generated method stub
		return videoView.isPlaying();
	}

	@Override
	public int getBufferPercentage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canPause() {
		// TODO Auto-generated method stub
		return videoView.canPause();
	}

	//拖动条是否能向后拖动
	@Override
	public boolean canSeekBackward() {
		// TODO Auto-generated method stub
		return videoView.canSeekBackward();
	}
	//拖动条是否能向前拖动
	@Override
	public boolean canSeekForward() {
		// TODO Auto-generated method stub
		return videoView.canSeekForward();
	}*/

}
