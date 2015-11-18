package com.cblue.media.music;

import java.util.Timer;
import java.util.TimerTask;

import com.cblue.android.R;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * MediaPlay 播放 暂停 继续音乐 上一首 下一首 重置 SeekBar控制播放进度
 * 
 * reset()让播放器从Error状态，恢复到空闲状态
 * 
 * 
 * OnCompletionListener  目前没什么用
 * @author Administrator
 * 
 */
public class MediaPlayActivity0 extends Activity implements OnClickListener,
		OnCompletionListener, OnSeekBarChangeListener {

	private MediaPlayer mediaPlayer;

	private Button start_btn, stop_btn, pause_btn, previous_btn, next_btn,
			restart_btn;
	private SeekBar mSeekbar;

	private int songs[] = { R.raw.faraway, R.raw.beautiful, R.raw.night };

	private static boolean isPause = false; /* 是否正在播放 */
	private int currentPosition = 0; /* 音乐当前位置 */
	private static boolean isPull = false; /* 是否拖动进度条 */
	private static boolean isFirst = true; //是否是第一次播放
	private int currentSong = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mediaplay);

		start_btn = (Button) findViewById(R.id.media_start);
		stop_btn = (Button) findViewById(R.id.media_stop);
		pause_btn = (Button) findViewById(R.id.media_pause);
		previous_btn = (Button) findViewById(R.id.media_previous);
		next_btn = (Button) findViewById(R.id.media_next);
		restart_btn = (Button) findViewById(R.id.media_restart);
		mSeekbar = (SeekBar) findViewById(R.id.media_seekbar);

		start_btn.setOnClickListener(this);
		stop_btn.setOnClickListener(this);
		pause_btn.setOnClickListener(this);
		previous_btn.setOnClickListener(this);
		next_btn.setOnClickListener(this);
		restart_btn.setOnClickListener(this);
		mSeekbar.setOnSeekBarChangeListener(this);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		if (mediaPlayer != null) {
			if (!mediaPlayer.isPlaying()) {
				mediaPlayer.start();
			}
		}
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if (mediaPlayer != null) {
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.pause();
			}
		}
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if (mediaPlayer != null) {
			mediaPlayer.release();
		}
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.media_start:
			Log.i("aaa", "media_start");
			//防止多次点击按钮，出现重复播放
			if(isFirst){
				playMusic(songs[currentSong]);
			}else{			
				if (isPause) {
					Log.i("aaa", "media_start_pause");
					// mediaPlayer.reset();
					mediaPlayer.seekTo(currentPosition);
					mediaPlayer.start();
					isPause = false;
				} else {
					playMusic(songs[currentSong]);
				}
			}
			break;

		case R.id.media_stop:
			if (mediaPlayer != null && mediaPlayer.isPlaying()) {
				mediaPlayer.stop();
			}
			break;

		case R.id.media_pause:
			Log.i("aaa", "media_pause");
			Log.i("aaa", " mediaPlayer.isPlaying()=" + mediaPlayer.isPlaying());
			if (mediaPlayer != null && mediaPlayer.isPlaying()) {
				currentPosition = mediaPlayer.getCurrentPosition();
				mediaPlayer.pause();
				isPause = true;
			}
			break;

		case R.id.media_previous:
			Log.i("aaa", "media_previous");
			previous();
			break;

		case R.id.media_next:
			Log.i("aaa", "media_next");
			next();
			break;

		case R.id.media_restart:
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.seekTo(0);
			} else {
				mediaPlayer.reset();
				playMusic(currentSong);
			}

			break;

		default:
			break;
		}

	}

	// 0,1,2
	private void previous() {
		if (currentSong - 1 < 0) {
			currentSong = 2;
		} else {
			currentSong--;
		}
		if(mediaPlayer.isPlaying()){
			mediaPlayer.stop();
			playMusic(songs[currentSong]);
		}
		

	}

	private void next() {
		if (currentSong + 1 < 2) {
			currentSong = 0;
		} else {
			currentSong++;
		}
		if(mediaPlayer.isPlaying()){
			mediaPlayer.stop();
			playMusic(songs[currentSong]);
		}
	}

	private void playMusic(int song) {
		try {
			mediaPlayer = MediaPlayer.create(getApplicationContext(), song);
			
			mediaPlayer.setOnCompletionListener(this);
			if (!mediaPlayer.isPlaying()) {
				// mediaPlayer.prepare();
				Log.i("aaa",
						"mediaPlayer.getDuration()="
								+ mediaPlayer.getDuration());
				// 设置拖动条显示进度
				mSeekbar.setMax(mediaPlayer.getDuration());
				Timer timer = new Timer();
				TimerTask timerTask = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (isPull) {
							return;
						}
						mSeekbar.setProgress(mediaPlayer.getCurrentPosition());
					}

				};
				timer.schedule(timerTask, 0, 10);
				mediaPlayer.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		if (mp != null) {
			mediaPlayer.release();
		}

	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		isPull = true;

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		mediaPlayer.seekTo(mSeekbar.getProgress());
		isPull = false;

	}

}
