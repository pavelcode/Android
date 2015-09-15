package com.cblue.fragment;



import com.cblue.android.R;

import android.app.Fragment;
import android.os.Bundle;
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
	

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view  = inflater.inflate(R.layout.fragmentcontent01,container,false);
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
