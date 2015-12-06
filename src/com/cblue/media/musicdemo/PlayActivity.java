package com.cblue.media.musicdemo;

import java.net.InterfaceAddress;

import com.cblue.android.R;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class PlayActivity extends Activity implements OnClickListener {

	private TextView name, time01, time02;
	private SeekBar seekBar;
	private Button play, pause, stop;
	private int currentSongPosition =0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.musicdemo_play);

		name = (TextView) findViewById(R.id.play_tv);
		play = (Button) findViewById(R.id.btn_play);
		pause = (Button) findViewById(R.id.btn_pause);
		stop = (Button) findViewById(R.id.btn_stop);
		
		time01 =(TextView)findViewById(R.id.time01);
		time02=(TextView)findViewById(R.id.time02);
		
		seekBar =(SeekBar)findViewById(R.id.play_sb);
		
		play.setOnClickListener(this);
		pause.setOnClickListener(this);
	    stop.setOnClickListener(this);

		Intent intent = getIntent();
		currentSongPosition = intent.getIntExtra("position", -1);
		String songnName = (String) Contacts.songs.get(currentSongPosition).get("name");
		name.setText(songnName);
		
		
		//注册广播
		IntentFilter intentFilter =new IntentFilter();
		intentFilter.addAction(StartService.SEND_DURATION);
		intentFilter.addAction(StartService.SEND_CURRENTPOSITION);
		registerReceiver(receiver,intentFilter);
	     Log.i("aaa", "注册广播");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(PlayActivity.this,StartService.class);
		switch (v.getId()) {
		case R.id.btn_play:
			
			intent.putExtra("flag", 1);
			intent.putExtra("currentSongPosition", currentSongPosition);
			startService(intent);
			break;
			
		case R.id.btn_pause:
			intent.putExtra("flag", 2);
			startService(intent);	
					break;
					
					
		case R.id.btn_stop:
			intent.putExtra("flag", 3);
			startService(intent);	
			break;

		default:
			break;
		}
	}
	
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
	
	
	
	
	
}
