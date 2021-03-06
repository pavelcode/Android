package com.cblue.ui.listview.pager;

import java.util.ArrayList;
import java.util.List;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;



/**
 * 
 * ListView分页
 * 问题：不能重复的添加数据，需要修改
 * 使用模拟器有时候效果不好，因为要出现甩完手指，离开屏幕
 * @author Administrator
 *
 */
public class ListViewPager01Activity extends Activity{

	private ListView listview;
	ArrayAdapter<String> arrayAdapter;
	//是否加载新数据
	boolean  loadNewData = false;
	private List<String> allData = new ArrayList<String>();
	public static final String TAG = ListViewPager01Activity.class.getName();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_pager01);
		
		listview = (ListView)findViewById(R.id.listView1);
		allData = getData("");
	    arrayAdapter = new ArrayAdapter<String>(ListViewPager01Activity.this, android.R.layout.simple_list_item_1,allData);
		listview.setAdapter(arrayAdapter);
		
	    listview.setOnScrollListener(new OnScrollListener() {
			
	    	
	    	
	    	// 回调顺序如下
	    				// 第1次：scrollState = SCROLL_STATE_TOUCH_SCROLL(1) 正在滚动
	    				// 第2次：scrollState = SCROLL_STATE_FLING(2) 手指做了抛的动作（手指离开屏幕前，用力滑了一下）
	    				// 第3次：scrollState = SCROLL_STATE_IDLE(0) 停止滚动	
	    	//滚动状态发生变化时回调
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				//Log.i(TAG, "scrollState="+scrollState);
				//当数据已经显示到最下面，且滚动停止，这时候就需要下载新数据
				if(loadNewData && scrollState==OnScrollListener.SCROLL_STATE_IDLE){
					//下载新数据
					Toast.makeText(ListViewPager01Activity.this, "开始下载数据", Toast.LENGTH_LONG).show();
					// arrayAdapter = new ArrayAdapter<String>(ListView04ScrollActivity.this, android.R.layout.simple_list_item_1, getData("new "));
					//listview.setAdapter(arrayAdapter);
					Log.i("aaa", "1111"+allData.size());
					allData.addAll(getData("web"));
					arrayAdapter.notifyDataSetChanged();
					Log.i("aaa", "2222"+allData.size());
					
				}
				
			}
			
			//滚动时一直回调，直到停止滚动时才停止回调。单击时回调一次
			//scrollState 滑动状态
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				loadNewData =(firstVisibleItem+visibleItemCount==totalItemCount);
				// TODO Auto-generated method stub
	/*			Log.i(TAG, "firstVisibleItem="+firstVisibleItem);
				Log.i(TAG, "visibleItemCount="+visibleItemCount);
				Log.i(TAG, "totalItemCount="+totalItemCount);*/
				
				
			}
		});
	   
	}
	
	private List<String> getData(String param){
		 List<String> data = new ArrayList<String>();
		    for(int i=0;i<30;i++){
		    	data.add(param+" "+"data"+i);
		    }
		    return data;
	}

}
