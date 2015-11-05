package com.cblue.ui.listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.cblue.android.R;

/**
 * 最简单实现ListView的方式
 * 直接继承ListActivity，实现ListView
 * @author Administrator
 *
 */
public class ListView01ListActivity extends ListActivity {

		ListView listView;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			/*
			 * setContentView(R.layout.activity_main); 
			 * 得到ListView listView = (ListView) findViewById(R.id.lv);
			 */
			listView = getListView();
			//使用系统定义的TextView的布局，看不出来内容，可以自定义一个TextView的布局，设置textcolor=#000000
			setListAdapter(new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, new String[] { "zhangsan",
					"lisi", "wangwu" }));
			listView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "position="+position, 1).show();
				}
			});

	}
}
