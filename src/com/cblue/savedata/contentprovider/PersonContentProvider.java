package com.cblue.savedata.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class PersonContentProvider extends ContentProvider {

	private PersonSqliteHelper personSqliteHelper;

	public static final String TAG = "PersonContentProvider";

	public static final UriMatcher S_URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
	// 与清单文件中的authorities一致
	public static final String AUTHORITY = "com.cblue.savedata.contentprovider.personcontentprovider";

	// 操作常量
	public static final int NOPARAM = 1;
	public static final int PARAM = 2;

	//需要参数的规则，不需要参数的规则
	static {
		// 添加URI匹配规则，如果匹配，返回code
		//select * from person
	    //content://com.example.contentprovider.peoplecontentprovider/person
		S_URI_MATCHER.addURI(AUTHORITY, "person", NOPARAM);
		//select * from person where _id=?
		//content://com.example.contentprovider.peoplecontentprovider/person/1
		S_URI_MATCHER.addURI(AUTHORITY, "person/#", PARAM);
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		personSqliteHelper = new PersonSqliteHelper(getContext());
		return false;
	}

	//判断URI，返回的多条数据还是单条数据
	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch (S_URI_MATCHER.match(uri)) {
		case NOPARAM:
			return "vnd.android.cursor.item/person";
		case PARAM:
			return "vnd.android.cursor.dir/person";
		}
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = personSqliteHelper.getWritableDatabase();
		long pid = 0;
		switch (S_URI_MATCHER.match(uri)) {
		case NOPARAM:
			pid = db.insert("person", "_id", values);
			//content://com.example.contentprovider.peoplecontentprovider/person/2
			Uri result = ContentUris.withAppendedId(uri, pid);
			Log.i(TAG, result.toString());
			return result;
			/*
			 * case PERSON: pid = db.insert("person", "name", values); String
			 * path = uri.toString(); return Uri.parse(path.substring(0,
			 * path.lastIndexOf('/')+1) + pid);
			 */
		default:
			throw new IllegalArgumentException("Unknow Uri:" + uri);
		}
	}

	/**
	 * 提供id进行查询
	 */
	//content://content://com.example.contentprovider.peoplecontentprovider/person/2
	//update tablename  set name='aaa' where id=? and age=?
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = personSqliteHelper.getWritableDatabase();
		int count = 0;
		String [] params;
		switch (S_URI_MATCHER.match(uri)) {
		case PARAM:
			long id = ContentUris.parseId(uri);
			//把id的值直接设定给条件中
			String where_value = " _id=" + id;
			if (selection != null && !"".equals(selection)) {
				where_value += selection;
			}
			count = db.update("person", values, where_value, selectionArgs);
			Log.i(TAG, "count="+count);
			break;

		}
		return count;
	}

	// content://com.cblue.savedata.contentprovider.PersonContentProvider/person/1
	// delete from person where _id=?
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = personSqliteHelper.getWritableDatabase();
		// 影响的行数
		int count = 0;
		switch (S_URI_MATCHER.match(uri)) {
		// 按ID进行删除
		case PARAM:
			long id = ContentUris.parseId(uri);
			String where_value = " _id =" + id;
			if (selection != null && !"".equals(selection)) {
				where_value += selection;
			}
			count = db.delete("person", where_value, selectionArgs);
			Log.i(TAG, "delete().count="+count);
			break;
		// 按其他条件进行删除
		case NOPARAM:
			//TODO 可以添加 _id in(?,?)
			count = db.delete("person", selection, selectionArgs);
			break;

		/*
		 * default: throw new IllegalArgumentException("Unknow Uri:" + uri);
		 */
		}
		return count;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		Cursor cursor = null;
		SQLiteDatabase db = personSqliteHelper.getWritableDatabase();
		switch (S_URI_MATCHER.match(uri)) {
		// 根据ID进行查询
		case PARAM:
			//content://content://com.example.contentprovider.peoplecontentprovider/person/2
			//select projection from people where _id=pid and xxx=xxx order by sortOrder
			long id = ContentUris.parseId(uri);
			String where_value = " _id =" + id;
			if (selection != null && !"".equals(selection)) {
				where_value += selection;
			}
			cursor = db.query("person", projection, selection, selectionArgs, null, null, sortOrder);
			break;
		// 根据其他条件进行查询
		case NOPARAM:
			//select projection from people where selection=selectionArgs order by sortOrder
			cursor = db.query("person", projection, selection, selectionArgs, null, null, sortOrder);
			break;
		}
		return cursor;
	}

}
