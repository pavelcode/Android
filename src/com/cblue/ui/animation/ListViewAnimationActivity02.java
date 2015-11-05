package com.cblue.ui.animation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cblue.android.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;



/**
 * 
 * ListView优化
 * 使用scrollListener监听list滚动到最后加载数据，显示进度条
 * 
 * @author Administrator
 * 
 */
public class ListViewAnimationActivity02 extends Activity {

	List<String> data;
	ListView listView;
	MyAdapter myAdapter;
	//加载更多布局
	View moreView;
	ProgressBar moreProgressBar;
	
	public static final String TAG = ListViewAnimationActivity02.class
			.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listviewanimation);
		listView = (ListView) findViewById(R.id.lv_animation);
		myAdapter = new MyAdapter(getApplicationContext(),getData());
		listView.setAdapter(myAdapter);
	}
	
	// 模拟数据
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		for (int i = 0; i < 30; i++) {
			map = new HashMap<String, Object>();
			map.put("img", R.drawable.ic_launcher);
			map.put("msg", "ListView " + i);
			list.add(map);
		}
		return list;
	}

	public class MyAdapter extends BaseAdapter {

		private Context context;
		private List<Map<String, Object>> data;
		private Animation left_in,right_in; 
		ViewHolder viewHolder;

		public MyAdapter(Context context, List<Map<String, Object>> data) {
			this.context = context;
			this.data = data;
			left_in=AnimationUtils.loadAnimation(context, R.anim.left_in);  
            right_in=AnimationUtils.loadAnimation(context, R.anim.right_in);  
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(
						R.layout.listviewanimation_item02, null);
				viewHolder = new ViewHolder();
				viewHolder.imageView = (ImageView) convertView
						.findViewById(R.id.listanimation02_iv);
				viewHolder.textView = (TextView) convertView
						.findViewById(R.id.listanimation02_tv);
				convertView.setTag(viewHolder);
				;
				
				 if (position % 2 == 0) {  
	                    left_in.setDuration(2000);  
	                    convertView.setAnimation(left_in);  
	                } else {  
	                    right_in.setDuration(2000);  
	                    convertView.setAnimation(right_in);  
	                }  
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			viewHolder.imageView.setImageResource((Integer) getData().get(
					position).get("img"));
			viewHolder.textView.setText((String) getData().get(position).get(
					"msg"));
			
			return convertView;
		}

	}

	// 封装每一行所要展现的控件
	static class ViewHolder {
		public ImageView imageView;
		public TextView textView;	
	}

}
