package com.cblue.savadata.sqlite;

import android.app.Activity;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.cblue.android.R;

/**
 * 使用CursorAdapatet完成数据库的数据加载
 * @author Administrator
 *
 */
public class CursorAdapaterListActivity extends Activity  {
	
	public static final String TAG="CursorAdapaterListActivity";

	ListView listView;
	static StudentDAO2 studentDAO2;
	Cursor cursor;
	CursorAdapter cursorAdapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		listView = (ListView) findViewById(R.id.listView1);
		//得到数据的Cursor对象
		studentDAO2 = new StudentDAO2(this);
		cursor = studentDAO2.getCursorScrollData(0, 10);
		//创建CursorAdapter
		 cursorAdapter = new SimpleCursorAdapter(this,
		 R.layout.listviewperson,cursor, new String[]{"name","age"}, new
		 int[]{R.id.person_name,R.id.person_age},
		 SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		 listView.setAdapter(cursorAdapter);
	}

	
	
}
