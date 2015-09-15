package com.cblue.savadata.sqlite;

import com.cblue.component.activity.LoginActivity;

import android.test.AndroidTestCase;
import android.util.Log;

public class TestSqlite extends AndroidTestCase {
	
	
	public static final String TAG="TestSqlite";
	
	/**
	 * 创建一个数据库文件
	 */
	public void testCreateDatabase(){
	     DBOpenHelper mDbOpenHelper = new DBOpenHelper(getContext());
	     //TODO 执行写方法的时候，才会创建数据库文件,读方式会报错
	     mDbOpenHelper.getWritableDatabase();
	}

	
	public void testInsertStudent(){
		StudentDAO mStudentDAO = new StudentDAO(getContext());
		mStudentDAO.add(new Student(1, "zhangsan", 20));
	}
	
	public void testFindStuentById(){
		StudentDAO mStudentDAO = new StudentDAO(getContext());
		Log.i(TAG, mStudentDAO.find(1).toString());
	}
	
	
}
