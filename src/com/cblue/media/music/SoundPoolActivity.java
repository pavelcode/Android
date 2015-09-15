package com.cblue.media.music;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cblue.android.R;


/**
 * 问题：SoundPool的load()和play()不能放在一个方法里，因为音频的加载需要一段时间
 * @author Administrator
 *
 */
public class SoundPoolActivity extends Activity {

	private Button soundpool_btn;
	private SoundPool mSoundPool;
	private int soundId1,soundId2,soundId3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.media_soundpool);
		soundpool_btn = (Button) findViewById(R.id.soundpool_btn1);
		
		/**
		 * maxStream —— 同时播放的流的最大数量 streamType ——
		 * 流的类型，一般为STREAM_MUSIC(具体在AudioManager类中列出) srcQuality ——
		 * 采样率转化质量，当前无效果，使用0作为默认值
		 */
		// 最多容纳3个音频流
		mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
		// 加载音乐
		soundId1 = mSoundPool.load(SoundPoolActivity.this, R.raw.m1, 1);
		soundId2 = mSoundPool.load(SoundPoolActivity.this, R.raw.m2, 1);
		soundId3 = mSoundPool.load(SoundPoolActivity.this, R.raw.m3, 1);
		soundpool_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("aaa", "------");
				mSoundPool.play(soundId1, 1, 1, 0, 0, 1);
	      		mSoundPool.play(soundId2, 1, 1, 1, 1, 1);
				mSoundPool.play(soundId3, 1, 1, 1, 1, 1);
				Log.i("aaa", "------");
			}
		});
	}
}
		

	
