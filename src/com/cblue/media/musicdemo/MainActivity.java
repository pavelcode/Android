package com.cblue.media.musicdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cblue.android.R;


import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * 
 * 首先是一个音乐列表
 * 当点击某一项的音乐的时候，就会跳转到音乐播放的界面
 * 点击按钮启动服务，实现音乐的播放，暂停，停止
 * 使用广播把音乐的总时间，当前进度发送到音乐播放界面，显示音乐播放的总时间，当前时间，当前执行的进度
 * 
 * @author pavel
 *
 */
public class MainActivity extends Activity {
	
	private ListView listView;
	private SimpleAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.musicdemo_activity_main);
		listView = (ListView)findViewById(R.id.lv);
		Contacts.initData();
		adapter = new SimpleAdapter(getApplicationContext(),Contacts.songs, R.layout.musicdemo_list_item, new String[]{"image","name"}, new int[]{R.id.iv,R.id.tv});
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
			//	Intent intent = new Intent(MainActivity.this,PlayActivity.class);
				Intent intent = new Intent(MainActivity.this,PlayPagerActivity.class);
				intent.putExtra("position",arg2);
				startActivity(intent);
				
			}
		});	
	
	
	}
	
	
	
	
	
	

	

}
