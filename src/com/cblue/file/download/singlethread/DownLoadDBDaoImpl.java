package com.cblue.file.download.singlethread;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * 注意：每次操作完数据库都需要关闭
 *      sqlite的事务
 * @author pavel
 *
 */
public class DownLoadDBDaoImpl implements DownLoadDBDao {
	
	private DownLoadDBHelper mDbHelper;
	
	public DownLoadDBDaoImpl(Context context){
		mDbHelper = new DownLoadDBHelper(context);
	}

	@Override
	public void insert(ThreadInfo threadInfo) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		db.execSQL("insert into thread_info(thread_id,url,start_position,end_position,finished_position) values(?,?,?,?,?)",new Object[]{threadInfo.getId(),threadInfo.getUrl(),threadInfo.getStartPosition(),threadInfo.getEndPosition(),threadInfo.getFinishedPosition()});
		db.close();
	}
	
	/**
	 * 当对多个数据进行操作，添加事务
	 * @param threadInfos
	 */
	public void insert(List<ThreadInfo> threadInfos){
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		db.beginTransaction();
		try {
			for(ThreadInfo threadInfo:threadInfos){
			db.execSQL("insert into thread_info(thread_id,url,start_position,end_position,finished_position) values(?,?,?,?,?)",new Object[]{threadInfo.getId(),threadInfo.getUrl(),threadInfo.getStartPosition(),threadInfo.getEndPosition(),threadInfo.getFinishedPosition()});
	        db.setTransactionSuccessful();
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}finally{
			db.endTransaction();
		}
		db.close();
	}

	@Override
	public void delete(String url, int thread_id) {
		// TODO Auto-generated method stub
       SQLiteDatabase db = mDbHelper.getWritableDatabase();
       db.execSQL("delete from thread_info where url=? and thread_id =?",new Object[]{url,thread_id});
       db.close();
	}

	@Override
	public void update(String url, int thread_id, long finishedPosition) {
		// TODO Auto-generated method stub
		   SQLiteDatabase db = mDbHelper.getWritableDatabase();
	       Log.i("aaa", "db finishedPosition="+finishedPosition); 
	       db.execSQL("update thread_info set finished_position=? where url=? and thread_id=?",new Object[]{finishedPosition,url,thread_id});
	       db.close();
	}

	@Override
	public List<ThreadInfo> getThreads(String url) {
		// TODO Auto-generated method stub
		List<ThreadInfo> threadInfos = new ArrayList<ThreadInfo>();
		 SQLiteDatabase db = mDbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from thread_info where url=?", new String[]{url});
		while(cursor.moveToNext()){
			ThreadInfo threadInfo = new ThreadInfo();
			threadInfo.setId(cursor.getInt(cursor.getColumnIndex("_id")));
			threadInfo.setUrl(cursor.getString(cursor.getColumnIndex("url")));
			threadInfo.setStartPosition(cursor.getInt(cursor.getColumnIndex("start_position")));
			threadInfo.setEndPosition(cursor.getInt(cursor.getColumnIndex("end_position")));
			threadInfo.setFinishedPosition(cursor.getInt(cursor.getColumnIndex("finished_position")));
			threadInfos.add(threadInfo);
		}
		db.close();
		cursor.close();
		return threadInfos;
	}

	@Override
	public boolean isExist(String url, int thread_id) {
		// TODO Auto-generated method stub
		 SQLiteDatabase db = mDbHelper.getWritableDatabase();
		 Cursor cursor =  db.rawQuery("select 1 from thread_info where url=? and thread_id=?", new String[]{url,String.valueOf(thread_id)});
		 boolean flag =  cursor.moveToNext();
		 cursor.close();
		 db.close();
		 return flag;
	}

}
