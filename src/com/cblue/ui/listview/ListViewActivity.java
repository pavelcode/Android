package com.cblue.ui.listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.cblue.android.R;

/**
 * 
 * ArrayAdapter的使用
 * SimpleAdapter
 * 
 * 1.资源文件显示ListView
 * 2.arrayAdpater创建简单效果，给listView加上分割线，分割线高度
 * 3.ListActivity实现ListView
 * @author Administrator
 * 
 */

public class ListViewActivity extends Activity {

	private ListView listview;
	ArrayAdapter<String> arrayAdapter;
	SimpleAdapter simpleAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		//得到ListView
		listview = (ListView) findViewById(R.id.listView1);
		//添加数据
		String[] content = { "aaa", "bbb", "ccc" };
		
	   /**
	    * 使用ArrayAdapter
	    * 创建适配器，添加适配器ArrayAdapter
	    */

		 //arrayAdapter = new  ArrayAdapter<String>(ListViewActivity.this,android.R.layout.simple_list_item_1,content);

		/**
		 * 设置多选
		 */
		 /*listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		 arrayAdapter = new ArrayAdapter<String>(ListViewActivity.this,android.R.layout.simple_list_item_multiple_choice,content);*/
		/**
		 * 设置单选
		 */
		/*listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		arrayAdapter = new ArrayAdapter<String>(ListViewActivity.this,android.R.layout.simple_list_item_single_choice, content);*/
		
		//把适配器放入到listView
		//listview.setAdapter(arrayAdapter);
		
		//添加item的点击事件
	/*	listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,int position, long id) {
				// TODO 得到适配器选项
				Toast.makeText(ListViewActivity.this,arrayAdapter.getItem(position), Toast.LENGTH_LONG).show();

			}
		});*/
		 
		 
	    /**
	     * 使用SimpleAdpater
	     * 可以实现图文混排
	     */
		
		//创建数据
		final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 10; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", R.drawable.ic_launcher);
			map.put("content", "content" + i);
			list.add(map);
		}
		 //创建适配器
		 simpleAdapter = new SimpleAdapter(this, list,
				 R.layout.listviewitem_show, new String[] {"content","image"}, new int[]{R.id.listview_textview,R.id.listview_imageview});
		 		 
		 //给listview设置适配器
		 listview.setAdapter(simpleAdapter);
		 listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(ListViewActivity.this, list.get(position).get("content").toString(), Toast.LENGTH_LONG).show();
			}
		});
		

		
	}

}
