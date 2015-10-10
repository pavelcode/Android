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
public class ListViewScrollActivity extends Activity{

	private ListView listview;
	ArrayAdapter<String> arrayAdapter;
	//是否加载新数据
	boolean  addNewData = false;
	public static final String TAG = ListViewScrollActivity.class.getName();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		
		listview = (ListView)findViewById(R.id.listView1);
		
	    List<String> data = new ArrayList<String>();
	    for(int i=0;i<30;i++){
	    	data.add("aaa"+i);
	    }
	    
	    arrayAdapter = new ArrayAdapter<String>(ListViewScrollActivity.this, android.R.layout.simple_list_item_1, data);
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
				if(addNewData && scrollState==OnScrollListener.SCROLL_STATE_IDLE){
					//下载新数据
					Toast.makeText(ListViewScrollActivity.this, "开始下载数据", Toast.LENGTH_LONG).show();
				}
				
			}
			//滚动时一直回调，直到停止滚动时才停止回调。单击时回调一次
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				
				addNewData =(firstVisibleItem+visibleItemCount==totalItemCount);
				
				// TODO Auto-generated method stub
	/*			Log.i(TAG, "firstVisibleItem="+firstVisibleItem);
				Log.i(TAG, "visibleItemCount="+visibleItemCount);
				Log.i(TAG, "totalItemCount="+totalItemCount);*/
				
				
			}
		});
	   
	}

}
