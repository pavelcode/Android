package com.cblue.ui.listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cblue.android.R;

/**
 * 
 * 直接继承ListActivity，实现ListView
 * @author Administrator
 *
 */
public class ListViewListActivity extends ListActivity {

		ListView listView;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
		
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < 10; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("image", R.drawable.ic_launcher);
				map.put("aaa", "content" + i);
				list.add(map);
			}
			/*
			 * setContentView(R.layout.activity_main); 
			 * 得到ListView listView = (ListView) findViewById(R.id.lv);
			 */
			listView = getListView();

			setListAdapter(new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, new String[] { "zhangsan",
					"lisi", "wangwu" }));

		

	}
}
