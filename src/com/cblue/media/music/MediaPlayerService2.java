package com.cblue.media.music;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.cblue.android.R;

/**
 * 使用绑定Service,这个还没有做好
 * @author Administrator
 *
 */
public class MediaPlayerService2 extends Service {

	private static final String TAG = "MediaPlayerService";

	private MediaPlayer mediaPlayer;
	private boolean isStop = false;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.d(TAG, "onCreate");
		mediaPlayer = MediaPlayer.create(this, R.raw.beautiful);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onBind");

		return new MyBinder();
	}

	class MyBinder extends Binder {

		public MediaPlayer getMediaPlayer() {
			return mediaPlayer;
		}

		public void initMusic() {
			init();
		}

		public void playMusic() {

			play();
		}

		public void pauseMusic() {

			pause();
		}

		public void stopMusic() {

			stop();
		}
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onStartCommand");
		init();
		play();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onUnbind");
		return super.onUnbind(intent);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d(TAG, "onDestroy");
		stop();
		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}

	public void init() {
		try {

			mediaPlayer.setLooping(true);
			mediaPlayer.prepare();
			mediaPlayer.start();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void play() {
		if (isStop) {
			try {
				mediaPlayer.prepare();
				mediaPlayer.seekTo(0);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		mediaPlayer.start();
		isStop = false;
	}

	public void pause() {
		if (mediaPlayer.isPlaying()) {
			mediaPlayer.pause();
		}
	}

	public void stop() {
		mediaPlayer.stop();
		isStop = true;
	}

}
