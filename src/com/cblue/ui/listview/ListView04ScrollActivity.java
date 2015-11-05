package com.cblue.ui.listview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.cblue.android.R;

/**
 * 
 * ListView分页
 * @author Administrator
 *
 */
public class ListView04ScrollActivity extends Activity{

	private ListView listview;
	ArrayAdapter<String> arrayAdapter;
	//是否加载新数据
	boolean  loadNewData = false;
	private List<String> allData = new ArrayList<String>();
	public static final String TAG = ListView04ScrollActivity.class.getName();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		
		listview = (ListView)findViewById(R.id.listView1);
		
	   
	    
	    arrayAdapter = new ArrayAdapter<String>(ListView04ScrollActivity.this, android.R.layout.simple_list_item_1,getData(""));
		listview.setAdapter(arrayAdapter);
		
	    listview.setOnScrollListener(new OnScrollListener() {
			
	    	//1 下拉，显示提示头部界面(HeaderView)，这个过程提示用户"下拉刷新"
	        //2 下拉到一定程度，超出了刷新最基本的下拉界限，我们认为达到了刷新的条件，提示用户可以"松手刷新"了，效果上允许用户继续下拉
	        //3 用户松手，可能用户下拉远远不止提示头部界面，所以这一步，先反弹回仅显示提示头部界面，然后提示用户"正在加载"。
	        //4  加载完成后，隐藏提示头部界面。*/
	    	//滚动状态发生变化时回调
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				//Log.i(TAG, "scrollState="+scrollState);
				//当数据已经显示到最下面，且滚动停止，这时候就需要下载新数据
				if(loadNewData && scrollState==OnScrollListener.SCROLL_STATE_IDLE){
					//下载新数据
					Toast.makeText(ListView04ScrollActivity.this, "开始下载数据", Toast.LENGTH_LONG).show();
					// arrayAdapter = new ArrayAdapter<String>(ListView04ScrollActivity.this, android.R.layout.simple_list_item_1, getData("new "));
					//listview.setAdapter(arrayAdapter);
					allData.addAll(getData("web"));
					arrayAdapter.notifyDataSetChanged();
				}
				
			}
			
			//滚动时一直回调，直到停止滚动时才停止回调。单击时回调一次
			//scrollState 滑动状态
			// 回调顺序如下
			// 第1次：scrollState = SCROLL_STATE_TOUCH_SCROLL(1) 正在滚动
			// 第2次：scrollState = SCROLL_STATE_FLING(2) 手指做了抛的动作（手指离开屏幕前，用力滑了一下）
			// 第3次：scrollState = SCROLL_STATE_IDLE(0) 停止滚动
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
