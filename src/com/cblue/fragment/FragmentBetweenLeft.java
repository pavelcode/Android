package com.cblue.fragment;

import com.cblue.android.R;
import android.R.raw;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentBetweenLeft extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragmentbetweenleft,container,false);
	}
	
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private FragmentManager mFragmentManager;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		btn1 = (Button)getView().findViewById(R.id.left_btn01);
		btn2 = (Button)getView().findViewById(R.id.left_btn02);
		btn3 = (Button)getView().findViewById(R.id.left_btn03);
		btn1.setOnClickListener(listener);
		btn2.setOnClickListener(listener);
		btn3.setOnClickListener(listener);
		mFragmentManager = getFragmentManager();
		
	}
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		
			switch (v.getId()) {
			case R.id.left_btn01:
				FragmentTransaction mFragmentTransaction1 = mFragmentManager.beginTransaction();
				FragmentBetweenRight mRightFragment1 = new FragmentBetweenRight();
				//传递数据
				Bundle bundle1 = new Bundle();
				bundle1.putString("title", "aaatitle");
				bundle1.putString("content", "aaacontent");
				mRightFragment1.setArguments(bundle1);
				
				mFragmentTransaction1.replace(R.id.rightfrag, mRightFragment1);
				mFragmentTransaction1.addToBackStack(null);
				mFragmentTransaction1.commit();
				break;
			case R.id.left_btn02:
				FragmentTransaction mFragmentTransaction2 = mFragmentManager.beginTransaction();
				FragmentBetweenRight mRightFragment2 = new FragmentBetweenRight();
				//传递数据
				Bundle bundle2 = new Bundle();
				bundle2.putString("title", "bbbtitle");
				bundle2.putString("content", "bbbcontent");
				mRightFragment2.setArguments(bundle2);
				
				mFragmentTransaction2.replace(R.id.rightfrag, mRightFragment2);
				mFragmentTransaction2.addToBackStack(null);
				mFragmentTransaction2.commit();
				break;
			case R.id.left_btn03:
				FragmentTransaction mFragmentTransaction3 = mFragmentManager.beginTransaction();
				FragmentBetweenRight mRightFragment3 = new FragmentBetweenRight();
				//传递数据
				Bundle bundle3 = new Bundle();
				bundle3.putString("title", "ccctitle");
				bundle3.putString("content", "ccccontent");
				mRightFragment3.setArguments(bundle3);
				
				mFragmentTransaction3.replace(R.id.rightfrag, mRightFragment3);
				mFragmentTransaction3.addToBackStack(null);
				mFragmentTransaction3.commit();
				break;

		
			}
			
		}
	};

}
