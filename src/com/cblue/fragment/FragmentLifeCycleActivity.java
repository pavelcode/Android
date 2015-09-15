package com.cblue.fragment;



import com.cblue.android.R;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;


/**
 * Activity与Fragment生命周期情况
 * @author Administrator
 *
 */
public class FragmentLifeCycleActivity extends Activity {

	private static final String TAG= "FragmenLifeCycle";
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.fragmentlifecycle);
		Log.i(TAG, "--Activity--onCreate()");
		  FragmentManager fragmentManager = getFragmentManager();
	      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	      FragmentLifeCycle fragmentCyclife = new FragmentLifeCycle();
	      fragmentTransaction.add(R.id.lifecycle_ll, fragmentCyclife);
	      fragmentTransaction.commit();
	      
	}

	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i(TAG, "--Activity--onPause()");
	}



	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(TAG, "--Activity--onResume()");
	}



	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i(TAG, "--Activity--onStart()");
	}



	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i(TAG, "--Activity--onStop()");
	}



	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, "--Activity--onDestroy()");
	}
 
	
}


