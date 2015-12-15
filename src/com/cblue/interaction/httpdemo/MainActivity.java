package com.cblue.interaction.httpdemo;

import java.util.List;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;


/**
 * 实现了数据下载，解析，保存数据，从数据库查询数据，并ListView展示出现
 * ListView中图片展示直接使用图片转化为BitMap展示，会出现图片错位问题
 * @author pavel
 *
 */
public class MainActivity extends Activity {

	private ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lunch_activity_main);
		listView = (ListView)findViewById(R.id.lv);
		LunchDao dao = new LunchDao(getApplicationContext());
		List<Lunch> lunchs = dao.queryAll();
		LunchAdpater adapter = new LunchAdpater(lunchs,getApplicationContext());
		listView.setAdapter(adapter);
	}

	
}

