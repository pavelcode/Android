package com.cblue.ui.listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cblue.android.R;
import com.cblue.component.activity.LoginActivity;

/**
 * 
 * ListView优化
 * 
 * @author Administrator
 * 
 */
public class ListViewOptimizeActivity extends Activity implements
		OnScrollListener {

	
	List<String> data;
	ListView listView;
	MyAdapter myAdapter;
	//加载更多布局
	View moreView;
	ProgressBar moreProgressBar;
	
	public static final String TAG = ListViewOptimizeActivity.class
			.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		listView = (ListView) findViewById(R.id.listView1);
		myAdapter = new MyAdapter(ListViewOptimizeActivity.this,
				getData());
		
		//初始化底部布局
		moreView = getLayoutInflater().inflate(R.layout.listview_more,null);
		moreProgressBar = (ProgressBar)moreView.findViewById(R.id.more_progressBar);
		moreProgressBar.setVisibility(ListView.VISIBLE);
		//底部加上moreView，注意要放在setAdapter方法前
		//listView.addHeaderView(v);
		listView.addFooterView(moreView);

		listView.setOnScrollListener(this);
		listView.setAdapter(myAdapter);
	}

	
	
	
	// 模拟数据
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		for (int i = 0; i < 30; i++) {
			map = new HashMap<String, Object>();
			map.put("img", R.drawable.icon);
			map.put("msg", "ListView " + i);
			list.add(map);
		}
		return list;
	}

	public class MyAdapter extends BaseAdapter {

		private Context context;
		private List<Map<String, Object>> data;
		ViewHolder viewHolder;

		public MyAdapter(Context context, List<Map<String, Object>> data) {
			this.context = context;
			this.data = data;
		}

		//
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
			// 第一种方式：使用new方法创建控件对象
			/*
			 * Log.i(TAG, "对象被创建了");
			 * TextView tv = new TextView(context);
			 * tv.setText(data.get(position)); 
			 * return tv;
			 */
			// 第二种方式：使用布局阀设置控件对象
			/*
			 * View view =
			 * LayoutInflater.from(context).inflate(R.layout.listview_show,null); 
			 * TextView tv = (TextView)view.findViewById(R.id.lvtx1);
			 * tv.setText(data.get(position)); 
			 //TODO 返回的是布局文件的view，不是某控件
			 * return tv;
			 */

			// 获取LayoutInflater服务的第二种方式
			// LayoutInflater inflate = (LayoutInflater)
			// getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			// 使用convertView,节省Item的View对象的创建
			final int tempPosition = position;
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(
						R.layout.listviewitem_show2, null);
				viewHolder = new ViewHolder();
				viewHolder.imageView = (ImageView) convertView
						.findViewById(R.id.listview_imageview);
				viewHolder.textView = (TextView) convertView
						.findViewById(R.id.listview_textview);
				viewHolder.button = (Button) convertView
						.findViewById(R.id.listview_button);
				convertView.setTag(viewHolder);
				Log.i(TAG, "从LayoutInflater得到View" + position);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
				Log.i(TAG, "直接得到View" + position);
			}
			viewHolder.imageView.setImageResource((Integer) getData().get(
					position).get("img"));
			viewHolder.textView.setText((String) getData().get(position).get(
					"msg"));
			viewHolder.button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(ListViewOptimizeActivity.this,
							"你点击了按钮" + tempPosition, Toast.LENGTH_LONG).show();
				}
			});
			return convertView;
		}

	}

	// 封装每一行所要展现的控件
	static class ViewHolder {
		public ImageView imageView;
		public TextView textView;
		public Button button;
	}


	//最后可见的item索引
	int lastVisibleIndex = 0;

    //是否需要加载数据
    boolean  loadData = false;
    /**
	 * 滚动时一直回调，直到停止滚动时才停止回调。单击时回调一次。
	 */
	// firstVisibleItem：当前能看见的第一个列表项ID（从0开始）
	// visibleItemCount：当前能看见的列表项个数（小半个也算）
	// totalItemCount：列表项共数
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		//Item的索引是从0开始的，所以总数要减去一

		lastVisibleIndex=firstVisibleItem+visibleItemCount-1;
		Log.i(TAG, lastVisibleIndex+"*****"+(totalItemCount-1));
		/* 
         * 当列表正处于滑动状态且滑动到列表底部时，执行数据加载
         * */  
         if(lastVisibleIndex == totalItemCount - 1){
        	   Log.i(TAG, "1可以加载数据");
        	   loadData = true;
         }
		//当列表正处于滑动状态且滑动到列表底部时，执行
		/*if(ListViewOptimizeActivity.this.scroll)
		 Toast.makeText(this, lastVisibleIndex+"数据！"+(totalItemCount-1), Toast.LENGTH_LONG).show();*/
		// 所有的条目已经和最大条数相等，则移除底部的View
		/*  if (lastVisibleIndex == totalItemCount)
		  {
			  moreProgressBar.setVisibility(ListView.GONE);
		      Toast.makeText(this, "数据全部加载完成，没有更多数据！", Toast.LENGTH_LONG).show();
		
		  }*/

	}
    /**
     * 滚动停止时回调
     */
	//view 报告滑动状态的视图
	//scrollState 滑动状态
	// 回调顺序如下
	// 第1次：scrollState = SCROLL_STATE_TOUCH_SCROLL(1) 正在滚动
	// 第2次：scrollState = SCROLL_STATE_FLING(2) 手指做了抛的动作（手指离开屏幕前，用力滑了一下）
	// 第3次：scrollState = SCROLL_STATE_IDLE(0) 停止滚动
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

		
		if(loadData&&scrollState == OnScrollListener.SCROLL_STATE_IDLE){
			Log.i(TAG, "2可以加载数据");
			moreProgressBar.setVisibility(ListView.VISIBLE);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//
			myAdapter.notifyDataSetChanged();
			//moreProgressBar.setVisibility(ListView.GONE);
		}
		// 滑到底部后自动加载，判断listview已经停止滚动并且最后可视的条目等于adapter的条目
		/*if (scrollState == OnScrollListener.SCROLL_STATE_IDLE && lastVisibleIndex == myAdapter.getCount()){
			Toast.makeText(this, lastVisibleIndex+"数据"+myAdapter.getCount(), Toast.LENGTH_LONG).show();
			//moreProgressBar.setVisibility(ListView.VISIBLE);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//加载新数据
			
		}
*/
	}

}
