package com.cblue.media.musicdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/**
 * 使用ViewPager实现播放歌曲的滑动切换，没有成功
 * @author pavel
 *
 */
public class PlayPagerActivity extends Activity implements OnPageChangeListener {

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		
	}/*
	
	private ViewPager viewPager;
	private List<View> views;
	
	private Button play, pause, stop;
	
	private int currentSongPosition =0;
	
	private TextView name, time01, time02;
	private SeekBar seekBar;
	
	private Map<String,Object> song;
	String songName;
	private int currentViewPosition=0;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.playpager);
		initData();
		
		Intent intent = getIntent();
		currentSongPosition = intent.getIntExtra("position", -1);
		Log.i("aaa", "currentSongPosition="+currentSongPosition);
		songName = (String) Contacts.songs.get(currentSongPosition).get("name");
		Log.i("aaa", songName);
		viewPager = (ViewPager)findViewById(R.id.vp);
		viewPager.setAdapter(mPagerAdapter);
		viewPager.setOnPageChangeListener(this);
		viewPager.setCurrentItem(0);
		
		//注册广播
		IntentFilter intentFilter =new IntentFilter();
		intentFilter.addAction(StartService.SEND_DURATION);
		intentFilter.addAction(StartService.SEND_CURRENTPOSITION);
		registerReceiver(receiver,intentFilter);
	     Log.i("aaa", "注册广播");
		
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
	}
	
	private void initData(){
		views = new ArrayList<View>();
		LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
		for(int i=0;i<Contacts.songs.size();i++){
		views.add(inflater.inflate(R.layout.play, null));
		}
	}
	
	PagerAdapter mPagerAdapter = new PagerAdapter() {
		
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return (arg0==arg1);
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return views.size();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			//super.destroyItem(container, position, object);
			container.removeView(views.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			container.addView(views.get(position),0);
			//song = Contacts.songs.get(position);
			//if(position==currentViewPosition){
			Log.i("aaa", "position==="+position);
			final Intent intent = new Intent(PlayPagerActivity.this,StartService.class);
			
			play = (Button) container.findViewById(R.id.btn_play);
			pause = (Button) container.findViewById(R.id.btn_pause);
			stop = (Button) container.findViewById(R.id.btn_stop);
			
			name = (TextView) container.findViewById(R.id.play_tv);
			
			time01 =(TextView)container.findViewById(R.id.time01);
			time02=(TextView)container.findViewById(R.id.time02);
			
			seekBar =(SeekBar)container.findViewById(R.id.play_sb);
			
			play.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub	
					intent.putExtra("flag", 1);
					intent.putExtra("currentSongPosition", currentSongPosition);
					startService(intent);
				}
			});
			
			pause.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					intent.putExtra("flag", 2);
					startService(intent);	
				}
			});

			stop.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					intent.putExtra("flag", 3);
					startService(intent);	
				}
			});
			name.setText(songName);	
			//}
			return views.get(position);
		}
	};

	
	 BroadcastReceiver receiver = new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				Log.i("aaa", "接收到广播");
				String action = intent.getAction();
				Log.i("aaa", "action="+action);
				if(StartService.SEND_DURATION.equals(action)){
					int duration = intent.getIntExtra("duration", -1);
					Log.i("aaa", "duration="+duration);
					time02.setText(milliSecon2Minute(duration));
					seekBar.setMax(duration);
				}else if(StartService.SEND_CURRENTPOSITION.equals(action)){
					int currentPosition = intent.getIntExtra("currentposition", -1);
					Log.i("aaa", "时间："+milliSecon2Minute(currentPosition));
					time01.setText(milliSecon2Minute(currentPosition));
					seekBar.setProgress(currentPosition);
				}
				
			}
		};
		
		private String milliSecon2Minute(int milliSecon){
			int allSecond = milliSecon/1000;
			int minute = allSecond/60;
			int second = allSecond%60;
			return minute+":"+second;
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			currentViewPosition = arg0;
			Log.i("aaa", "arg0="+arg0);
			Intent intent = new Intent(PlayPagerActivity.this,StartService.class);
			intent.putExtra("flag", 2);
			startService(intent);	
			
		}

		
		
	
	
	
	
*/}
