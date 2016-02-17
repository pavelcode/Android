package com.cblue.ui.fragment.handset;


import com.cblue.android.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * 
 * @author Administrator
 *
 */
public class ContentFragment01 extends Fragment {
	

	public ContentFragment01(){
		Log.i("aaa", "ContentFragment01初始化");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view  = inflater.inflate(R.layout.fragmentcontent01_handset,container,false);
		Button button = (Button)view.findViewById(R.id.frg_button01);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "Fragment中的按钮被点击了", 1).show();
			}
		});
	    return view;
	}
	
	
}
