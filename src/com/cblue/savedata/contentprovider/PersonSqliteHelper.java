package com.cblue.savedata.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonSqliteHelper extends SQLiteOpenHelper {

	public static final String DBNAME = "mydb.db";
	public static final int DBVERSION = 1;

	public PersonSqliteHelper(Context context) {
		super(context, DBNAME, null,DBVERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table person(_id integer primary key autoincrement,name varchar(20),age int);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
