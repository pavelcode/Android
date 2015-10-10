package com.cblue.ui.fragment;

import com.cblue.android.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContentFragment03 extends Fragment {
	
	public TextView mFrg_TextView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	    return inflater.inflate(R.layout.fragmentcontent03,null);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		mFrg_TextView = (TextView)getView().findViewById(R.id.frg_textview03);
	}

}
