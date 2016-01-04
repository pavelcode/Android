package com.cblue.ui.listpager.xlistview;
import java.util.ArrayList;

import com.cblue.android.R;



import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


public class Fragment01 extends Fragment{

	private View viewFragment;
	
	private ArrayAdapter<String> mAdapter;
	
	private ArrayList<String> items = new ArrayList<String>();
	private Handler mHandler;
	private int start = 0;
	private static int refreshCnt = 0;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		viewFragment=inflater.inflate(R.layout.xlistview_frag01, null);
		
		return viewFragment;
	}


	
}
