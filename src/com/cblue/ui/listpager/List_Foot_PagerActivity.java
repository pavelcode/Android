package com.cblue.ui.listpager;

import java.util.ArrayList;
import java.util.List;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

/**
 * 
 * List下拉分页加载
 * @author Administrator
 *
 */
public class List_Foot_PagerActivity extends Activity implements
		OnScrollListener {
	
	private ListView mListview = null;
	private PagerAdapter mAdapter;

	private List<News> mDatas;
	//ListView底部加载视图
	private View mFooterView;
	
	
	private Handler handler=new Handler();
	private boolean isLoading;//表示是否正在加载

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_foot_pager);
		//给ListView底部添加滚动条和提示信息
		mFooterView = getLayoutInflater().inflate(R.layout.list_foot_pager_loadmore, null);
		mListview = (ListView) findViewById(R.id.listview);
		mListview.addFooterView(mFooterView);// 设置列表底部视图
		
		//模拟服务端数据30条,每次加载10条
		mDatas = getDatas();
		
		//初始化,先加载10条
		List<News> news = new ArrayList<News>();
		for (int i = 0; i < 10; i++) {
			news.add(mDatas.get(i));
		}
		
		mAdapter = new PagerAdapter(news);;
		mListview.setAdapter(mAdapter);
		mListview.setOnScrollListener(this);

	}

	
	private List<News> getDatas() {
		List<News> news = new ArrayList<News>();
		for (int i = 1; i <= 50; i++) {
			News items = new News();
			items.setTitle("Title" + i);
			items.setContent("This is News Content" + i);
			news.add(items);
		}
		return news;
	}
	
	
	class PagerAdapter extends BaseAdapter {

		List<News> newsItems;

		public PagerAdapter(List<News> newsitems) {
			this.newsItems = newsitems;
		}

		public int getCount() {
			return newsItems.size();
		}

		public Object getItem(int position) {
			return newsItems.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public void addNewsItem(News newsitem) {
			newsItems.add(newsitem);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getLayoutInflater().inflate(R.layout.list_foot_pager_item,
						null);
			}
			// 新闻标题
			TextView tvTitle = (TextView) convertView
					.findViewById(R.id.newstitle);
			tvTitle.setText(newsItems.get(position).getTitle());
			// 新闻内容
			TextView tvContent = (TextView) convertView
					.findViewById(R.id.newscontent);
			tvContent.setText(newsItems.get(position).getContent());

			return convertView;
		}
	}
	

	
	/*
	 * 	firstVisibleItem表示显示在屏幕上的第一个Item位置(ListItem部分显示的也算)（下标从0开始）
	 *  visibleItemCount表示显示在屏幕上的Item总数(部分显示的ListItem也算)
	 *  totalItemCount表示ListView中的Item总数 
	 */
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		//当前显示的ListView中的Item位置和ListView中的数据个数一致，则需要加载新的数据
		if (firstVisibleItem+visibleItemCount==totalItemCount&&!isLoading) {
			//正在加载，加载完毕设置isLoading =false； 
			isLoading = true;
			//如果服务端还有数据，则继续加载更多，否则隐藏底部的加载更多
			if (totalItemCount<=mDatas.size()) {
				//等待2秒之后才加载，模拟网络等待时间为2s
				handler.postDelayed(new Runnable() {
					public void run() {
						loadMoreData();
					}
				},2000);
			}
			else{
				if (mListview.getFooterViewsCount()!=0) {
					mListview.removeFooterView(mFooterView);
				}
			}
			
		}

	}
	
	private void loadMoreData(){
		//得到适配器中数字的条数
		int count = mAdapter.getCount();
		for (int i = 0; i < 10; i++) {
			//如果数据的条数小于总条数，继续记载数据，否则数据加载完成，去除底部视图
			if (count+i<mDatas.size()) {
				mAdapter.addNewsItem(mDatas.get(count+i));
			}else{
				mListview.removeFooterView(mFooterView);
			}
		}
		//刷新UI
		mAdapter.notifyDataSetChanged();
		//数据加载完毕
		isLoading = false;
	}

	//当屏幕停止滚动时为0；当屏幕滚动且用户使用的触碰或手指还在屏幕上时为1； 由于用户的操作，屏幕产生惯性滑动时为2 
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
		
	}
	
	

	
}

class News {
	private String title;    //标题
	private String content;  //内容
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


}
