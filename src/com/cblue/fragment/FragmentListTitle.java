package com.cblue.fragment;

import com.cblue.android.R;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;




/**
 * 1 ListFragment的使用
 * @author Administrator
 *
 */

public class FragmentListTitle extends ListFragment{

public void onActivityCreated(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onActivityCreated(savedInstanceState);
	setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, FragmentListData.title));
}

@Override
public void onListItemClick(ListView l, View v, int position, long id) {
	// TODO Auto-generated method stub
	super.onListItemClick(l, v, position, id);
	getListView().setItemChecked(position, true);
	FragmentManager mFragmentManager = getFragmentManager();
	FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
	FragmentListDetail contentFragment = new FragmentListDetail();
	mFragmentTransaction.replace(R.id.list_detail,contentFragment);
	mFragmentTransaction.addToBackStack(null);
	
	Bundle mBundle = new Bundle();
	mBundle.putString("content",FragmentListData.content[position]);
	contentFragment.setArguments(mBundle);
	
	mFragmentTransaction.commit();
}

}
