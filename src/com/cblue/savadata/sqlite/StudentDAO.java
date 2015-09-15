package com.cblue.savadata.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * 操作数据库的第一种方式：使用sql语句
 * @author pavel
 *
 */
public class StudentDAO
{
	private DBOpenHelper helper;
	private SQLiteDatabase db;

	public StudentDAO(Context context)
	{
		helper = new DBOpenHelper(context);
		db = helper.getWritableDatabase();
	}

	/**
	 * 添加新的学生信息
	 * 
	 * @param student
	 */
	public void add(Student student)
	{
		try {
			db.execSQL("insert into t_student (name,age) values (?,?)", new Object[]
					{ student.getName(), student.getAge() });	
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(db!=null){
				db.close();
			}
			
		}
		
	}

	/**
	 * 更新学生信息
	 * 
	 * @param student
	 */
	public void update(Student student)
	{
		db.execSQL("update t_student set name = ?,age = ? where _id = ?", new Object[]
		{ student.getName(), student.getAge(), student.getSid() });
	}

	/**
	 * 查找学生信息
	 * 
	 * @param sid
	 * @return
	 */
	public Student find(int sid)
	{
		Student student = null;
		Cursor cursor = db.rawQuery("select _id,name,age from t_student where _id = ?", new String[]
		{ String.valueOf(sid) });
		if (cursor.moveToNext())
		{
			student = new Student(cursor.getInt(cursor.getColumnIndex("_id")), cursor.getString(cursor.getColumnIndex("name")), cursor.getShort(cursor.getColumnIndex("age")));
		}
		/*if(cursor!=null){
			cursor.close();
		}*/
		return student;
	}

	/**
	 * 刪除学生信息
	 * 
	 * @param sids
	 */
	//注意这里是Integer包装类型
	public void detele(Integer... ids)
	{
		if (ids.length > 0)
		{
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < ids.length; i++)
			{
				sb.append('?').append(',');
			}
			sb.deleteCharAt(sb.length() - 1);
			Log.i("StudentDao", "delete from student where _id in( "
					+ sb.toString() + " );");
			Log.i("StudentDao", ids.toString());
			//TODO 最后一个参数是强转的，不是new出来的  注意，强转成Object数组
			db.execSQL("delete from t_student where _id in (" + sb + ")", (Object[]) ids);
		}
	}

	/**
	 * 获取学生信息
	 * @param start 其实位置
	 * @param count 学生数量
	 * @return
	 */
	public List<Student> getScrollData(int start, int count)
	{
		List<Student> students = new ArrayList<Student>();
		Cursor cursor = db.rawQuery("select * from t_student limit ?,?", new String[]{ String.valueOf(start), String.valueOf(count) });
		while (cursor.moveToNext())
		{
			students.add(new Student(cursor.getInt(cursor.getColumnIndex("_id")), cursor.getString(cursor.getColumnIndex("name")), cursor.getShort(cursor.getColumnIndex("age"))));
		}
		return students;
	}

	/**
	 * 获取学生数量
	 * @return
	 */
	public long getCount()
	{
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select count(_id) from t_student", null);
		if (cursor.moveToNext())
		{
			return cursor.getLong(0);
		}
		return 0;
	}
}
