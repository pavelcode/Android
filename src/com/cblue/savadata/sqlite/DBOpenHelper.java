package com.cblue.savadata.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBOpenHelper extends SQLiteOpenHelper {
	private static final int VERSION = 1;
	private static final String DBNAME = "data.db";
	private static final String STUDENT = "t_student";
	public static final String TAG = "DBOpenHelper";

	public DBOpenHelper(Context context) {
		super(context, DBNAME, null, VERSION);
	}

	// 数据库第一次被创建时onCreate会被调用
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(TAG, "onCreate()");
		// autoincrement 必须放在后面 id必须是integer
		db.execSQL("CREATE TABLE IF NOT EXISTS "
				+ STUDENT
				+ " (_id integer  primary key autoincrement,name varchar(20),age integer)");

	}

	// 如果DATABASE_VERSION值被改为2,系统发现现有数据库版本不同,即会调用onUpgrade
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i(TAG, "UpGrade!");
		Log.i(TAG, "oldVersion=" + oldVersion);
		Log.i(TAG, "newVersion=" + newVersion);
		if (newVersion > oldVersion) {
			db.execSQL("alter table " + STUDENT + " add classname varchar(10);");
		}
		// String tempTable = "temp_student";
		// db.execSQL("alter table "+STUDENT +" rename to "+tempTable);
		// db.execSQL("create table "+STUDENT+" (sid integer primary key,name varchar(20),age integer,sex varchar(4))");
		// String sql =
		// "insert into "+STUDENT+" (name,age,sex) select name,age,'��' from "+tempTable;
		// db.execSQL(sql);
	}

}
