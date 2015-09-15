package com.cblue.savadata.sqlite;

import android.app.Activity;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

/**
 * 使用Loader来查询数据库
 * 只写了代码，需要测试
 * @author Administrator
 *
 */
public class LoaderActivity extends Activity implements LoaderCallbacks<Cursor>{
    
	
	LoaderManager loaderManager = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		loaderManager = getLoaderManager();
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		loaderManager.initLoader(id, args, this);
		return null;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// TODO Auto-generated method stub
		
	}
}
