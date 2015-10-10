package com.cblue.ui.listpager.xlistview;
import java.util.ArrayList;

import com.cblue.android.R;
import com.cblue.ui.listpager.xlistview.XListView.IXListViewListener;



import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


public class Fragment01 extends Fragment implements IXListViewListener{

	private View viewFragment;
	private XListView xListView=null;
	private ArrayAdapter<String> mAdapter;
	
	private ArrayList<String> items = new ArrayList<String>();
	private Handler mHandler;
	private int start = 0;
	private static int refreshCnt = 0;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		viewFragment=inflater.inflate(R.layout.xlistview_frag01, null);
		geneItems();
		initViews();
		return viewFragment;
	}

	/**
	 *初始化item
	 */
	private void geneItems() {
		for (int i = 0; i != 5; ++i) {
			items.add("refresh cnt " + (++start));
		}
	}
	
	private void initViews(){
		xListView=(XListView) viewFragment.findViewById(R.id.xListView);
		xListView.setPullLoadEnable(true);
		mAdapter = new ArrayAdapter<String>(this.getActivity(), R.layout.xlistview_list_item, items);
		xListView.setAdapter(mAdapter);
		xListView.setXListViewListener(this);
		mHandler = new Handler();
	}

	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				start = ++refreshCnt;
				items.clear();
				geneItems();
				mAdapter = new ArrayAdapter<String>(Fragment01.this.getActivity(), R.layout.xlistview_list_item, items);
				xListView.setAdapter(mAdapter);
				onLoad();
			}
		}, 2000);
	}

	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				geneItems();
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
