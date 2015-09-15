package com.cblue.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cblue.savadata.sqlite.DBOpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBUtil {
	
	
	private DBOpenHelper helper;
	private SQLiteDatabase db;

	public DBUtil(Context context)
	{
		helper = new DBOpenHelper(context);
		db = helper.getWritableDatabase();
	}
	
	/**
	 * 插入  修改 删除 
	 * @param sql
	 * @param bindArgs
	 * @return
	 */
	public boolean changeData(String sql,String[]bindArgs){
		boolean flag = false;
		try {
			db.execSQL(sql, bindArgs);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(db!=null){
				db.close();
			}
		}
         return flag;		
	}
	
	/**
	 * 查询数据库单条记录
	 * @param sql
	 * @param selectArgs
	 * @return
	 */
	public Map<String, Object> queryOneRecordBySQL(String sql,String [] selectArgs){
		  Map<String, Object> map = new HashMap<String, Object>();
		  Cursor cursor = db.rawQuery(sql, selectArgs);
		  int count = cursor.getColumnCount();
		  while(cursor.moveToNext()){
			  for(int i=0;i<count;i++){
				  String col_name = cursor.getColumnName(i);
				  String col_value = cursor.getString(cursor.getColumnIndex(col_name));
				  if(col_value==null){
					  col_value="";
				  }
				  map.put(col_name, col_value);
			  }
		  }
		  
		  return map;
	}
	

	
	public List<Map<String, Object>> queryMutilRecordBySQL(String sql,String [] selectArgs){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		 Cursor cursor = db.rawQuery(sql, selectArgs);
		  int count = cursor.getColumnCount();
		  while(cursor.moveToNext()){
			  Map<String,Object> map = new HashMap<String, Object>();
			  for(int i=0;i<count;i++){
				  String col_name = cursor.getColumnName(i);
				  String col_value = cursor.getString(cursor.getColumnIndex(col_name));
				  //Map中的Object对象是不能加入null的，为了容错，转化为空字符串
				  if(col_value==null){
					  col_value="";
				  }
				  map.put(col_name, col_value);
			  }
			  list.add(map);
		  }
		
		return list;
	}

}
