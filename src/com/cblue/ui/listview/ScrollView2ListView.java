package com.cblue.ui.listview;

import java.util.ArrayList;
import java.util.List;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * ScrollView与ListView滚动冲突
 * 思路：首先演示ScrollView嵌套ListView,出现了滑动事件冲突（ListView显示不完全）
 *      添加设置ListView高度方法，让listView能够正常显示 
 * ScrollView和ListView冲突问题
 * 在设置完ListView的Adapter后，根据ListView的子项目重新计算ListView的高度，然后把高度再作为LayoutParams设置给ListView
 * 注意:ListView的父布局必须是LinearLayout，不能是其他的，因为其他的Layout(如RelativeLayout)没有重写onMeasure()，所以会在onMeasure()时抛出异常。
 * @author pavel
 *
 */
public class ScrollView2ListView extends Activity {

	
	private ListView listView;
	private List<String> items;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scrollview2listview);
		listView = (ListView)findViewById(R.id.sv_lv);
		initData();
		listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, items));
	    setListViewHeight(listView);
	}
	
	private void initData(){
		items = new ArrayList<String>();
		for(int i=0;i<10;i++){
			items.add("item"+i);
		}
	}
	
	private void setListViewHeight(ListView listView){
		Log.i("aaa", "setListViewHeight");
		ListAdapter listAdapter =  listView.getAdapter();
		Log.i("aaa", "listAdapter="+listAdapter.getCount());
		if(listAdapter==null){
			return ;
		}
		int totolHeigth = 0;
		for(int i=0;i<listAdapter.getCount();i++){
			View itemView = listAdapter.getView(i, null, listView);
			itemView.measure(0, 0);
			totolHeigth += itemView.getMeasuredHeight();//TODO 这里不要写成getHeight()
		}
		ViewGroup.LayoutParams mLayoutParams = listView.getLayoutParams();
		mLayoutParams.height = totolHeigth+(listView.getDividerHeight()*(listView.getCount()-1));
		
		listView.setLayoutParams(mLayoutParams);
		
	}
	
	
}
