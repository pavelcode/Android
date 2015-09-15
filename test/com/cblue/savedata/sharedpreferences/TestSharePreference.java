package com.cblue.savedata.sharedpreferences;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import android.test.AndroidTestCase;
import android.util.Log;

public class TestSharePreference extends AndroidTestCase {

	public static String TAG = "TestSharePreferenceTools";
	
	SharePreferencesTools mPreferencesTools;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		Log.i(TAG, "开始执行");
		mPreferencesTools = new SharePreferencesTools(getContext());

	}

	public void testWriteSharePreferences() {
		Log.i(TAG, "testWriteSharePreferences");
		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("name","zhangsan");
		userMap.put("age", 20);
		userMap.put("sex", true);
		
		mPreferencesTools.writeSharePreferences("testWrite", userMap);
	}
	
	public void testreadSharePreferences(){
		
		Map<String, ?> userMap = mPreferencesTools.readSharePreferences("testWrite");
		for(Entry<String, ?> entry:userMap.entrySet()){
			Log.i(TAG, "key="+entry.getKey()+";value="+entry.getValue());
		}
		
	}
	

}
