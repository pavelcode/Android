package com.cblue.file.download.singlethread;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DownLoadDBHelper extends SQLiteOpenHelper {

	private static final String DBNAME = "download.db";
	private static final int DBVERSION = 2;

	public DownLoadDBHelper(Context context) {
		super(context, DBNAME, null, DBVERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table thread_info(_id integer primary key autoincrement,thread_id int,url text,start_position int,end_position int,finished_position int)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
