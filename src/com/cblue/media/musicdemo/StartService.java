package com.cblue.media.musicdemo;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class StartService extends Service {

	
	public static final String SEND_DURATION="DURATION";
	public static final String SEND_CURRENTPOSITION="CURRRENTPOSITION";
	
	private MediaPlayer mediaPlayer;
	private Context context;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		context = getApplicationContext();
	}
	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
		 int flag = intent.getIntExtra("flag", -1);
		 switch (flag) {
		case 1:
			int currentSong = intent.getIntExtra("currentSongPosition",-1);
			mediaPlayer = MediaPlayer.create(getApplicationContext(),(Integer) Contacts.songs.get(currentSong).get("path"));
			int duration = mediaPlayer.getDuration();
			mediaPlayer.start();
			
			Intent broadcastIntent = new Intent();
			broadcastIntent.setAction(SEND_DURATION);
			broadcastIntent.putExtra("duration", duration);
			context.sendBroadcast(broadcastIntent);
			Log.i("aaa", "send broacast duration="+duration);
			
			
			new Timer().schedule(new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					intent.setAction(SEND_CURRENTPOSITION);
					intent.putExtra("currentposition", mediaPlayer.getCurrentPosition());
					context.sendBroadcast(intent);
					
				}
			}, 0, 2000);
			
			break;
		case 2:
			mediaPlayer.pause();	
			break;
		case 3:
			mediaPlayer.stop();
			break;

		default:
			break;
		}
		return super.onStartCommand(intent, flags, startId);
	}

}
