package com.cblue.savedata.contentprovider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Log;

public class TestPersonContentProvider extends AndroidTestCase {

	public static final String TAG = TestPersonContentProvider.class
			.getSimpleName();

	public void testInsert() {

		ContentResolver contentResolver = getContext().getContentResolver();
		// TODO URI前面一定要加上content 后面一定要加匹配符
		Uri uri = Uri
				.parse("content://com.cblue.savedata.contentprovider.PersonContentProvider/persons");
		ContentValues values = new ContentValues();
		values.put("name", "lisi");
		values.put("age", 30);
   		Log.i(TAG,contentResolver.insert(uri, values).toString());

		
	}
	/*public void testInsert2() {
		
		ContentResolver contentResolver = getContext().getContentResolver();
		// TODO URI前面一定要加上content 后面一定要加匹配符
		Uri uri = Uri
				.parse("content://com.cblue.savedata.contentprovider.PersonContentProvider/person/2");
		ContentValues values = new ContentValues();
		values.put("name", "xxx");
		values.put("age", 20);
		Log.i("-->"+TAG,contentResolver.insert(uri, values).toString());
		
		
	}*/
	
	/**
	 * 根据ID删除
	 */
	public void testDeleteById(){
		
		ContentResolver contentResolver = getContext().getContentResolver();
		// TODO URI前面一定要加上content 后面一定要加匹配符
		Uri uri = Uri.parse("content://com.cblue.savedata.contentprovider.PersonContentProvider/person");
        Uri newUri =  ContentUris.withAppendedId(uri, 1);
        contentResolver.delete(newUri, null, null);
	}
	
	/**
	 * 根据条件进行查询
	 */
	public void testDeleteByName(){
		
		ContentResolver contentResolver = getContext().getContentResolver();
		// TODO URI前面一定要加上content 后面一定要加匹配符
		Uri uri = Uri.parse("content://com.cblue.savedata.contentprovider.PersonContentProvider/persons");
		 contentResolver.delete(uri, " name=?", new String[]{"zhangsan"});
	}
	
	public void testUpdateById(){
		ContentResolver contentResolver = getContext().getContentResolver();
		Uri uri = Uri.parse("content://com.cblue.savedata.contentprovider.PersonContentProvider/person");
        Uri newUri =  ContentUris.withAppendedId(uri, 3);
        ContentValues values =  new ContentValues();
        values.put("name","wangwu");
        contentResolver.update(newUri, values, null, null);
   
	}
	
	public void testQueryById(){
		ContentResolver contentResolver = getContext().getContentResolver();
		Uri uri = Uri.parse("content://com.cblue.savedata.contentprovider.PersonContentProvider/person");
        Uri newUri =  ContentUris.withAppendedId(uri, 1);
        Cursor cursor = contentResolver.query(newUri,new String[]{"_id","name","age"} , null, null, null);
        while(cursor.moveToNext()){
        	String name = cursor.getString(cursor.getColumnIndex("name"));
        	int age = cursor.getInt(cursor.getColumnIndex("age"));
        	Log.i(TAG, "name="+name+";age="+age);
        }
	}
	
	public void testQueryByName(){
		ContentResolver contentResolver = getContext().getContentResolver();
		Uri uri = Uri.parse("content://com.cblue.savedata.contentprovider.PersonContentProvider/persons");
        //Uri newUri =  ContentUris.withAppendedId(uri, 1);
        Cursor cursor = contentResolver.query(uri,new String[]{"_id","name","age"} ," name = ?", new String[]{"lisi"}, " age asc");
        while(cursor.moveToNext()){
        	String name = cursor.getString(cursor.getColumnIndex("name"));
        	int age = cursor.getInt(cursor.getColumnIndex("age"));
        	Log.i(TAG, "name="+name+";age="+age);
        }
	}
	

}
