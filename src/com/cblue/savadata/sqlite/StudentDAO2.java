package com.cblue.savadata.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 数据库操作的第二种方式：使用类来操作，为后面ContentProvider做准备
 * @author pavel
 *
 */
public class StudentDAO2 {
	private DBOpenHelper helper;
	private SQLiteDatabase db;

	public StudentDAO2(Context context) {
		helper = new DBOpenHelper(context);
	}

	/**
	 * 添加新的学生信息
	 * 
	 * @param student
	 */
	public void add(Student student) {
		db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("_id", student.getSid());
		values.put("name", student.getName());
		values.put("age", student.getAge());
		/**
		 * nullColumnHack：当values参数为空或者里面没有内容的时候，我们insert是会失败的（底层数据库不允许插入一个空行），
		 * 为了防止这种情况，我们要在这里指定一个列名，到时候如果发现将要插入的行为空行时，就会将你指定的这个列名的值设为null，
		 * 然后再向数据库中插入。
		 * 我们可以想象一下，如果我们不添加nullColumnHack的话，那么我们的sql语句最终的结果将会类似
		 * insert into  tableName()values();这显然是不允许的。
		 * 而如果我们添加上nullColumnHack呢，sql将会变成这样，
		 * 
		 * 
		 * 当values参数为空或者里面没有内容的时候，我们insert是会失败的（底层数据库不允许插入一个空行），
		 * 为了防止这种情况，我们要在这里指定一个列名，到时候如果发现将要插入的行为空行时，就会将你指定的这个列名的值设为null，
		 * 然后再向数据库中插入。
		 * insert into tableName (nullColumnHack)values(null);这样很显然就是可以的。
		 */

		db.insert("t_student", "_id", values);
	}

	/**
	 * 更新学生信息
	 * 
	 * @param student
	 */
	public void update(Student student) {
		db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", student.getName());
		values.put("age", student.getAge());
		db.update("t_student", values, "_id = ?",
				new String[] { String.valueOf(student.getSid()) });
	}

	/**
	 * 查找学生信息
	 * 
	 * @param sid
	 * @return
	 */
	public Student find(int sid) {
		db = helper.getWritableDatabase();
		// db.query(table, columns, selection, selectionArgs, groupBy, having,
		// orderBy)
		Cursor cursor = db.query("t_student", new String[] { "_id", "name",
				"age" }, "sid=?", new String[] { String.valueOf(sid) }, null,
				null, null);
		if (cursor.moveToNext()) {
			return new Student(cursor.getInt(0), cursor.getString(1),
					cursor.getShort(2));
		}
		return null;
	}

	/**
	 * 刪除学生信息
	 * 
	 * @param sids
	 */
	public void detele(Integer... sids) {
		if (sids.length > 0) {
			StringBuffer sb = new StringBuffer();
			String[] strPid = new String[sids.length];
			for (int i = 0; i < sids.length; i++) {
				sb.append('?').append(',');
				strPid[i] = String.valueOf(sids[i]);
			}
			sb.deleteCharAt(sb.length() - 1);
			db = helper.getWritableDatabase();
			db.delete("t_student", "_id in (" + sb + ")", strPid);
		}
	}

	/**
	 * 获取学生信息
	 * 
	 * @param start
	 *            其实位置
	 * @param count
	 *            学生数量
	 * @return
	 */
	public List<Student> getScrollData(int start, int count) {
		List<Student> students = new ArrayList<Student>();
		db = helper.getWritableDatabase();
		Cursor cursor = db.query("t_student", new String[] { "_id", "name",
				"age" }, null, null, null, null, "_id desc", start + ","
				+ count);
		while (cursor.moveToNext()) {
			students.add(new Student(cursor.getInt(0), cursor.getString(1),
					cursor.getShort(2)));
		}
		return students;
	}

	/**
	 * 获取学生数量
	 * 
	 * @return
	 */
	public long getCount() {
		db = helper.getWritableDatabase();
		Cursor cursor = db.query("t_student", new String[] { "count(*)" },
				null, null, null, null, null);
		if (cursor.moveToNext()) {
			return cursor.getLong(0);
		}
		return 0;
	}
	
	
	/**
	 * 获取学生信息
	 * 
	 * @param start
	 *            其实位置
	 * @param count
	 *            学生数量
	 * @return
	 */
	public Cursor getCursorScrollData(int start, int count) {
		
		db = helper.getWritableDatabase();
		Cursor cursor = db.query("t_student", new String[] { "_id","name",
				"age" }, null, null, null, null, "_id desc", start + ","
				+ count);
		return cursor;
	}
}
