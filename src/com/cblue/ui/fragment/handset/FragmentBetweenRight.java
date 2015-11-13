package com.cblue.ui.fragment.handset;

import com.cblue.android.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentBetweenRight extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragmentbetweenright_handset, container, false);
		return view;
	}

	private TextView titleTextView;
	private TextView contentTextView;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		titleTextView = (TextView) getView().findViewById(R.id.right_tv1);
		contentTextView = (TextView) getView().findViewById(R.id.right_tv2);
		Bundle bundle = getArguments();
		if (bundle != null) {

			titleTextView.setText(bundle.getString("title"));
			contentTextView.setText(bundle.getString("content"));
		}

	}
}
