package com.cblue.interaction.httpdemo;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class LunchDao {

	private DBOpenHelper dbOpenHelper;
	private SQLiteDatabase sqLiteDatabase;

	public LunchDao(Context context) {
		dbOpenHelper = new DBOpenHelper(context);
		sqLiteDatabase = dbOpenHelper.getWritableDatabase();
	}

	public void addLunch(Lunch lunch) {
		sqLiteDatabase
				.execSQL(
						"insert into b_lunch(cateid,cateurl,catetitle,catecontent,cateprice)values(?,?,?,?,?)",
						new Object[] { lunch.getCateid(), "http://169.254.200.110:8080/Android1406B"+lunch.getCateurl(),
								lunch.getCatetitle(), lunch.getCatecontent(),
								lunch.getCateprice() });
	}
	public List<Lunch> queryAll(){
		List<Lunch> lunchs = new ArrayList<Lunch>();
		Cursor cursor = sqLiteDatabase.rawQuery("select cateid,cateurl,catetitle,catecontent,cateprice from b_lunch", null);
         while(cursor.moveToNext()){
        	 Lunch lunch = new Lunch();
        	 lunch.setCateid(cursor.getInt(cursor.getColumnIndex("cateid")));
        	 lunch.setCateurl(cursor.getString(cursor.getColumnIndex("cateurl")));
        	 lunch.setCatetitle(cursor.getString(cursor.getColumnIndex("catetitle")));
        	 lunch.setCatecontent(cursor.getString(cursor.getColumnIndex("catecontent")));
        	 lunch.setCateprice(cursor.getString(cursor.getColumnIndex("cateprice")));
        	 lunchs.add(lunch);
         }
         return lunchs;
	}
}
