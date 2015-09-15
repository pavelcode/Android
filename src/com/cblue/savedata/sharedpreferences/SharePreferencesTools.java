package com.cblue.savedata.sharedpreferences;

import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * SharePreferences读写的通用工具类
 * 
 * @author Administrator
 * 
 */
public class SharePreferencesTools {

	private Context context;

	public SharePreferencesTools(Context context) {
		this.context = context;
	}

	/**
	 * 写入SharePreferences
	 * 
	 * @param filename
	 * @param map
	 * @return
	 */
	public boolean writeSharePreferences(String filename,
			Map<String, Object> map) {
		
		boolean flag = false;
		SharedPreferences mPreferences = context.getSharedPreferences(filename,
				Context.MODE_PRIVATE);
		Editor mEditor = mPreferences.edit();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof Boolean) {
				mEditor.putBoolean(key, (Boolean) value);
			}
			if (value instanceof Float) {
				mEditor.putFloat(key, (Float) value);
			}
			if (value instanceof Integer) {
				mEditor.putInt(key, (Integer) value);
			}
			if (value instanceof Long) {
				mEditor.putLong(key, (Long) value);
			}
			if (value instanceof String) {
				mEditor.putString(key, (String) value);
			}
		}
		flag = mEditor.commit();

		return flag;
	}
	
	/**
	 * 读取SharePreferences
	 * @param filename
	 * @return
	 */
	public Map<String,?> readSharePreferences(String filename){
		Map<String, ?> map = null;
		SharedPreferences mPreferences = context.getSharedPreferences(filename,
				Context.MODE_PRIVATE);
		map = mPreferences.getAll();
		return map;
	}

}
