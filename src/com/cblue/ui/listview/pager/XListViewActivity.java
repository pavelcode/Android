package com.cblue.ui.listview.pager;
import java.util.ArrayList;

import com.cblue.android.R;
import com.cblue.ui.listview.pager.XListView.IXListViewListener;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;

/**
 * 
 * XListView实现的下拉刷新，上拉加载
 * 著名github项目XListView
 * git地址
 * source code: https://github.com/Maxwin-z/XListView-Android
 * 原理： 
 * XListView继承ListView。
 * 下拉刷新组件是ListView的一个Header。在ListView创建时就将这个自定义View塞进去，默认情况是看不到的，所以这个HeaderView的高度初始设置为0。
 * 上拉载入更多组件是Footer，为了确保这个footer在最后（可能会添加多个自定义footer），在用户调用setAdatper的时候再把这个footer塞进去。
 * 覆写ListView的onTouchEvent方法，处理各种情况。
 * 用户松手，启动mScroller，将header、footer回滚到所需状态。
 * 添加了用户下拉、上拉移动delay的效果。
 * 提供了两个接口：
 * a) IXListViewListener:  触发下拉刷新、上拉载入更多
 * b) OnXScrollListener: 这个和原生的OnScrollListener一样，但是在mScroller回滚时，也会触发这里的事件
 * 
 * 
 *
 * @author Administrator
 *
 */
public class XListViewActivity extends Activity implements IXListViewListener{

	
	private XListView xListView=null;
	private ArrayAdapter<String> mAdapter;
	
	private ArrayList<String> items = new ArrayList<String>();
	ArrayList<String> temp_items = new ArrayList<String>();
	private Handler mHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xlistview_activity);
		geneItems("");  //模拟从本地数据库加载数据
		temp_items = items;
		initViews();
	}

	/**
	 *初始化item
	 */
	private void geneItems(String param) {
		for (int i = 0; i != 5; ++i) {
			items.add(param+" data");
		}
	}
	
	private void initViews(){
		xListView=(XListView) findViewById(R.id.xListView);
		xListView.setPullLoadEnable(true);
		mAdapter = new ArrayAdapter<String>(this, R.layout.xlistview_activity_list_item, items);
		xListView.setAdapter(mAdapter);
		xListView.setXListViewListener(this);
		mHandler = new Handler();
	}

	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				items.clear();
				//模拟从网络下载的数据
				geneItems("refresh");
				//模拟从数据库获得的数据
				geneItems("");
				mAdapter.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
	}

	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				geneItems("load");
				mAdapter.notifyDataSetChanged();
				onLoad();
			}
		}, 2000);
	}
	
	

	private void onLoad() {
		xListView.stopRefresh();
		xListView.stopLoadMore();
		xListView.setRefreshTime("刚刚");
	}
	
}
