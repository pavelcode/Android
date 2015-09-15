package com.cblue.media.music;

import java.io.FileDescriptor;
import java.io.IOException;

import com.cblue.android.R;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore.Audio.Media;
import android.util.Log;

/**
 * 这个是启动service
 * MediaPlay播放音乐
 * @author Administrator
 *
 */
public class MediaPlayerService1 extends Service {
	
	
	
	MediaPlayer mediaPlayer;
	
	public static final String TAG="MediaPlayerService";

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i(TAG, "onCreate");
		if(mediaPlayer==null){
			//播放Raw下为音频
			mediaPlayer = MediaPlayer.create(this, R.raw.beautiful);

			//播放Asset下的音频
			/*try {
				//播放其他位置的文件
				mediaPlayer = new MediaPlayer();
				AssetManager mAssetManager = getAssets();
				AssetFileDescriptor mAssetFileDescriptor =  mAssetManager.openFd("beautiful.mp3");
				FileDescriptor mFileDescriptor = mAssetFileDescriptor.getFileDescriptor();
				long offset = mAssetFileDescriptor.getStartOffset();
				long length = mAssetFileDescriptor.getLength();
				mediaPlayer.setDataSource(mFileDescriptor, offset, length);
				//API中：在setDataSource之后，需要调用
				mediaPlayer.prepare(); 
			} catch (Exception e) {
				// TODO: handle exception
			}*/
			//播放外部储存的音频
	/*		try {
				mediaPlayer = new MediaPlayer();
				mediaPlayer.setDataSource("mnt/sdcard/Music/beautiful.mp3");
				mediaPlayer.prepare();
			} catch (Exception e) {
				// TODO: handle exception
			}*/
			
			//播放网络视频
			/*try {
				mediaPlayer = new MediaPlayer();
				mediaPlayer.setDataSource(this, Uri.parse("http://www.xxx.com/beautiful.mp3"));
				mediaPlayer.prepare();
			} catch (Exception e) {
				// TODO: handle exception
			}*/
			
			
			
			mediaPlayer.setLooping(true);
		}
	}

	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onStartCommand");
		mediaPlayer.start();
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		mediaPlayer.stop();
		super.onDestroy();
		
	}
	
	

	

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
