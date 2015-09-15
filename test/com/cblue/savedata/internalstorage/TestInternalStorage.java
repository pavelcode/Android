package com.cblue.savedata.internalstorage;


import java.io.File;

import com.cblue.android.R;

import android.content.Context;
import android.test.AndroidTestCase;
import android.util.Log;

public class TestInternalStorage extends AndroidTestCase {
	

	InternalFileTools mInternalFileTools;
	CacheFileTools mCacheFileTools;
	RawFileTools mRawFileTools;
	AssetsFileTools mAssetsFileTools;
	
	public static final String TAG="TestInternalStorage";
	
	@Override
	protected void setUp() throws Exception {
		mInternalFileTools = new InternalFileTools(getContext());
		mCacheFileTools = new CacheFileTools(getContext());
		mRawFileTools = new RawFileTools(getContext());
		mAssetsFileTools = new AssetsFileTools(getContext());
	}
	
	public void testWriteInternalFile(){
		try {
			mInternalFileTools.writeInternalFile("internalFile.txt", "你好".getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testReadInternalFile(){
		try {
			byte b[] =mInternalFileTools.readInternalFile("internalFile.txt");
			Log.i(TAG, new String(b));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testWriteInternalFileBySelfFolder(){
		try {
			mInternalFileTools.writeInternalFileBySelfFolder("SelfFolder.txt", "你好".getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public void testListFile() {
		mInternalFileTools.listFile("/mnt");
	}
	
	public void testListAllFile(){
		File file = new File("/mnt");
		mInternalFileTools.listAllFile(file);
	}

	
	
	public void testWriteCacheFileBySelfFolder(){
		try {
			mCacheFileTools.writeCacheFileBySelfFolder("MyCacheFile.txt", "我的缓存".getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testReadRawFile(){
		try {
			byte [] b = mRawFileTools.readRawFile(R.raw.rawtext);
			Log.i(TAG, new String(b,0,b.length));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testReadAssetsFile(){
		try {
		  byte b[] = mAssetsFileTools.readAssetFile("ass.txt");
		  Log.i(TAG, new String(b,0,b.length));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
