package com.cblue.component.service;

import android.os.Parcel;
import android.util.Log;

/**
 * 实现对象的序列化
 * @author Administrator
 *
 */
public class ParcelDemo {
	
	
	private Parcel parcel;
	
	public ParcelDemo(){
		parcel = Parcel.obtain();
	}
	
	public void writeObj(){
		parcel.writeString("zhang");
		parcel.writeInt(20);
	}
	
	public void readObj(){
		parcel.setDataPosition(0);
		String name = parcel.readString();
		int age = parcel.readInt();
		Log.i("aaa", "name="+name+";age="+age);
		
	}

}
