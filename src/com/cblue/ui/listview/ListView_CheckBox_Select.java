package com.cblue.ui.listview;

import java.util.ArrayList;
import java.util.HashMap;

import com.cblue.android.R;
import com.cblue.ui.listview.MyAdapter.ViewHolder;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;



/**
 * 
 * ListView中单选框实现多选，单选，反选
 * 
 * @author Administrator
 * 
 */
public class ListView_CheckBox_Select extends Activity {

	private ListView mListView;
	private MyAdapter mAdapter;
	// 保存ListView中显示的数据
	private ArrayList<String> list = null;

	private Button btnSelectAll; // 全选按钮
	private Button btnDeselectAll; // 反选按钮
	private Button btnCancel; // 取消按钮

	// ListView选中的item数量
	private int checkNum;

	private TextView showSelectInfo;// 显示选择信息

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_checkbox_select);

		showSelectInfo = (TextView) findViewById(R.id.listview_checkbox_select_info);

		mListView = (ListView) findViewById(R.id.listview_checkbox_select_lv);
		btnSelectAll = (Button) findViewById(R.id.listview_checkbox_select_btn_selectall);
		btnDeselectAll = (Button) findViewById(R.id.listview_checkbox_select_btn_deselectall);
		btnCancel = (Button) findViewById(R.id.listview_checkbox_select_btn_cancelselect);

		// 为Adapter初始化测试数据
		initDate();
		// 给ListView绑定Adapter
		if (list != null) {
			mAdapter = new MyAdapter(list, this);
			mListView.setAdapter(mAdapter);
		}

		
		OnClickListener btnListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.listview_checkbox_select_btn_selectall:
					// 遍历list的长度，将MyAdapter中的map值全部设为true
					for (int i = 0; i < list.size(); i++) {
						MyAdapter.getIsSelected().put(i, true);
					}
					checkNum = list.size(); // 数量设为list的长度
					dataChanged(); // 刷新listview和TextView的显示
					break;
				case R.id.listview_checkbox_select_btn_deselectall:
					// 遍历list的长度，将已选的设为未选，未选的设为已选
					for (int i = 0; i < list.size(); i++) {
						if (MyAdapter.getIsSelected().get(i)) {
							MyAdapter.getIsSelected().put(i, false);
							checkNum--;
						} else {
							MyAdapter.getIsSelected().put(i, true);
							checkNum++;
						}
					}
					// 刷新listview和TextView的显示
					dataChanged();
					break;
				case R.id.listview_checkbox_select_btn_cancelselect:
					// 遍历list的长度，将已选的按钮设为未选
					for (int i = 0; i < list.size(); i++) {
						if (MyAdapter.getIsSelected().get(i)) {
							MyAdapter.getIsSelected().put(i, false);
							checkNum--;// 数量减1
						}
					}
					// 刷新listview和TextView的显示
					dataChanged();
					break;

				}

			}
		};
		

		// 全选按钮的回调接口
		btnSelectAll.setOnClickListener(btnListener);
		// 反选按钮的回调接口
		btnDeselectAll.setOnClickListener(btnListener);
		// 取消按钮的回调接口
		btnCancel.setOnClickListener(btnListener);
		
		
		OnItemClickListener itemListener = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ViewHolder holder = (ViewHolder) view.getTag();
				// 改变CheckBox的状态
				holder.cb.toggle();
				// 将CheckBox的选中状况记录下来
				MyAdapter.getIsSelected().put(position, holder.cb.isChecked());
				// 调整选定条目
				if (holder.cb.isChecked() == true) {
					checkNum++;
				} else {
					checkNum--;
				}
				// 用TextView显示
				showSelectInfo.setText("已选中" + checkNum + "项");
			
			}
		};
	
		// 绑定ListView的Item监听器
		mListView.setOnItemClickListener(itemListener);

	}

	// 初始化数据
	private void initDate() {
		list = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			list.add("data" + " " + i);
		}
	}

	// 刷新listview和TextView的显示
	private void dataChanged() {
		// 通知listView刷新
		mAdapter.notifyDataSetChanged();
		// TextView显示最新的选中数目
		showSelectInfo.setText("已选中" + checkNum + "项");
	};

}

class MyAdapter extends BaseAdapter {
	// 填充数据的list
	private ArrayList<String> list;
	// 用来控制CheckBox的选中状况(使用HashMap是因为键值不能重复)
	private static HashMap<Integer, Boolean> isSelected;
	// 上下文
	private Context context;
	// 用来导入布局
	private LayoutInflater inflater = null;

	// 构造器
	public MyAdapter(ArrayList<String> list, Context context) {
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
		isSelected = new HashMap<Integer, Boolean>();
		// 初始化数据
		initDate();
	}

	// 初始化isSelected的数据
	private void initDate() {
		for (int i = 0; i < list.size(); i++) {
			getIsSelected().put(i, false);
		}
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			// 获得ViewHolder对象
			holder = new ViewHolder();

			convertView = inflater.inflate(R.layout.listview_checkbox_select_item, null);
			holder.tv = (TextView) convertView.findViewById(R.id.listview_checkbox_select_item_tv);
			holder.cb = (CheckBox) convertView.findViewById(R.id.listview_checkbox_select_item_cb);
			// 为view设置标签
			convertView.setTag(holder);
		} else {
			// 取出holder
			holder = (ViewHolder) convertView.getTag();
		}
		// 设置list中TextView的显示
		holder.tv.setText(list.get(position));
		// 根据isSelected来设置checkbox的选中状况
		holder.cb.setChecked(getIsSelected().get(position));
		return convertView;
	}
	
	public static class ViewHolder {
		TextView tv;
		CheckBox cb;
	}

	//得到单选框是否被选中的记录集合
	public static HashMap<Integer, Boolean> getIsSelected() {
		return isSelected;
	}
    //给适配器设置是单选框是否被选中的记录集合
	public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
		MyAdapter.isSelected = isSelected;
	}

	
}
