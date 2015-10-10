package com.cblue.ui.fragment;

import com.cblue.android.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;


public class FragmentListDetail extends Fragment
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragmentlistdetail, container, false);
		TextView textView = (TextView)view.findViewById(R.id.list_frag_tv);
		Bundle bundle = this.getArguments();
		String contentString = bundle.getString("content");
		textView.setText(contentString);
		return view;
	}

}
