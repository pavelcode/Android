package com.cblue.savedata.externalstorage;

import java.io.File;

import android.test.AndroidTestCase;
import android.util.Log;

public class TestSDCardFile extends AndroidTestCase {
	
	public static final String TAG="TestSDCardFile";
	
	
	SDCardFileTools mSdCardFileTools;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		mSdCardFileTools = new SDCardFileTools(getContext());
	}
	
	public void testWriteSDCardFile(){
		try {
			try {
				mSdCardFileTools.writeSDCardFile("sdcarfile.txt","sd卡的信息".getBytes());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testReadSDCardFile(){
		try {
			byte a [] =mSdCardFileTools.readSDCardFile("sdcarfile.txt");
			Log.i(TAG, "从SD中读到的内容---"+new String(a));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testSaveFileBySuffix(){
		try {
			mSdCardFileTools.saveFileBySuffix("aa.txt","根据后缀名保存的文件".getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void testDeleteFileFromSDCard(){
		   mSdCardFileTools.deleteFileFromSDCard("Download", "aa.txt");
	}

}
