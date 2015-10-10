package com.cblue.ui.control;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;

import com.cblue.android.R;
import com.cblue.component.activity.LoginActivity;

public class ExpandableListViewActivity extends Activity {

	ExpandableListView expandableListView;
	List<String> parentList;
	List<List<String>> sonList;
	public static final String TAG = "ExpandableListViewActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.control_expandablelistview);
		expandableListView = (ExpandableListView) findViewById(R.id.expandableList);
		// 添加数据
		initData();
		// 创建适配器
		MyAdapter myAdapter = new MyAdapter(ExpandableListViewActivity.this,parentList,sonList);
		// 设置适配器
		Log.i(TAG, "expandableListView=" + expandableListView);
		Log.i(TAG, "myAdapter=" + myAdapter);
		expandableListView.setAdapter(myAdapter);
		expandableListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(
						ExpandableListViewActivity.this,
						"groupPosition=" + groupPosition + ";childPosition="
								+ childPosition, Toast.LENGTH_SHORT).show();
				return true;
			}

		});
	}

	public void initData() {
		parentList = new ArrayList<String>();
		sonList = new ArrayList<List<String>>();
		addInfo("北京市",new String[]{"海淀区","朝阳区"});
		addInfo("深圳市",new String[]{"罗湖区","宝安区"});
	}

	public void addInfo(String parent, String son[]) {
		parentList.add(parent);
		List<String> itemList = new ArrayList<String>();
		for (int i = 0; i < son.length; i++) {
			Log.i(TAG, "son[" + i + "]=" + son[i]);
			itemList.add(son[i]);
		}
		sonList.add(itemList);
	}

}

class MyAdapter extends BaseExpandableListAdapter {

	public static final String TAG = "MyAdapter";
	Context context;
	List<String> parentList;
	List<List<String>> sonList;

	public MyAdapter(Context context, List<String> parentList,
			List<List<String>> sonList) {
		this.context = context;
		this.parentList = parentList;
		this.sonList = sonList;

	
	}

	// 获取组在给定的位置编号
	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	// 获取在给定的组的儿童的ID
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return parentList.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return sonList.get(groupPosition).get(childPosition);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return parentList.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO 注意这里得到的子节点的长度
		Log.i(TAG, "sonlist.size=" + sonList.get(groupPosition).size());
		return sonList.get(groupPosition).size();
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView textView = null;
		if (convertView == null) {
			textView = new TextView(context);
		} else {
			textView = (TextView) convertView;
		}
		// TODO 在最后的位置在设置值，不用在if语句中设定值
		textView.setText(parentList.get(groupPosition));
		// 添加TextView的属性
		return textView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView textView = null;
		if (convertView == null) {
			textView = new TextView(context);
		} else {
			textView = (TextView) convertView;
		}
		// 添加TextView的属性
		// TODO 在最后的位置在设置值，不用在if语句中设定值
		textView.setText(sonList.get(groupPosition).get(childPosition));
		return textView;
	}

	//是否指定分组视图及其子视图的ID对应的后台数据改变也会保持该ID.
	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	// 是否可以选中指定位置上的子元素
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}
}
