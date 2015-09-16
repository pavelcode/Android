package com.cblue.file.download.singlethread;

import com.cblue.android.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * 单线程的断点下载
 * Activity点击按钮，启动服务，线程根据得到文件大小创建文件。成功后发送消息，启动线程，创建数据库记录，并实现文件的的读写。
 * 当点击停止按钮的时候，把当前文件的进度保存到数据库。当启动的时候，得到读取文件当前的进度，继续进行读写。
 * 把进度通过广播发送给Activity，Activity接收到后，更新进度条。
 * @author pavel
 *
 */
public class DownLoadActivity extends Activity implements OnClickListener {

	
	private TextView download_fileName_TV;
	private ProgressBar download_pb;
	private Button download_btn;
	private Button download_stop_btn;
	private FileInfo fileInfo;
	private Intent intent;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.download);
		download_fileName_TV = (TextView)findViewById(R.id.download_filename);
		download_pb = (ProgressBar)findViewById(R.id.download_fileprogressBar);
		download_pb.setMax(100);
		download_btn =(Button)findViewById(R.id.download_btn);
		download_stop_btn =(Button)findViewById(R.id.download_stop_btn);
		download_btn.setOnClickListener(this);
		download_stop_btn.setOnClickListener(this);
		fileInfo = new FileInfo(1,"http://bcscdn.baidu.com/netdisk/BaiduYun_3.9.5.exe","BaiduYun_3.9.5.exe",0,0);
		download_fileName_TV.setText(fileInfo.getFileName());
		//注册广播
		IntentFilter intentFilter = new IntentFilter(DownLoadService.ACTION_BROADCAST);
		registerReceiver(receiver, intentFilter);
		
		
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.download_btn:
			intent = new Intent(DownLoadActivity.this,DownLoadService.class);
			intent.setAction(DownLoadService.ACTION_START_DOWNLOAD);
			intent.putExtra("fileInfo",fileInfo);
			startService(intent);
			
			break;
		case R.id.download_stop_btn:
			intent = new Intent(DownLoadActivity.this,DownLoadService.class);
			intent.setAction(DownLoadService.ACTION_STOP_DOWNLOAD);
			intent.putExtra("fileInfo", fileInfo);
			startService(intent);
			break;


		default:
			break;
		}
		
	}
	
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	};

	BroadcastReceiver receiver =  new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if(intent.getAction().equals(DownLoadService.ACTION_BROADCAST)){
				int finished = (int)intent.getLongExtra("finished", 0);
			//	Log.i("aaa", "finished="+finished);
				download_pb.setProgress(finished);
				
			}
				
			
		}
	
	};


}
