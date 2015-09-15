package com.cblue.media.music;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.cblue.android.R;
import com.cblue.component.activity.LoginActivity;

/**
 * 是不是绑定服务更好点
 * @author Administrator
 *
 */
public class MediaPlayerActivity extends Activity {


	Button playMusicButton;
	Button stopMusicButton;
	SeekBar musicVolume;

	public static final String TAG = "MediaPlayerActivity";

	Intent intent;

	// 媒体管理器
	AudioManager audioManager;
	// 最大音量
	int maxVolume;
	// 当前音量
	int currentVolume;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.media_mediaplay);
		
		playMusicButton = (Button) findViewById(R.id.playmusic_btn);
		stopMusicButton = (Button) findViewById(R.id.stopmusic_btn);
		musicVolume = (SeekBar) findViewById(R.id.music_volumn);
		
		playMusicButton.setOnClickListener(listener);
		stopMusicButton.setOnClickListener(listener);
		musicVolume.setOnSeekBarChangeListener(seebarkListener);
		
		// 音频管理器
		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);// 获得最大音量
		currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);// 获得当前音量
		musicVolume.setMax(maxVolume);
		musicVolume.setProgress(currentVolume);

	}

	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//定义在这里，在退出程序的时候，在进入，点击停止的时候，出现intent的空异常了
			intent = new Intent(MediaPlayerActivity.this,
					MediaPlayerService1.class);
			
			switch (v.getId()) {
			case R.id.playmusic_btn:
				// 启动服务
				startService(intent);
				// 绑定服务
				break;

			case R.id.stopmusic_btn:
				stopService(intent);
				break;

			}

		}

	};

	OnSeekBarChangeListener seebarkListener = new OnSeekBarChangeListener() {

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// TODO Auto-generated method stub
			audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress,
					AudioManager.FLAG_ALLOW_RINGER_MODES);

		}
	};

}
