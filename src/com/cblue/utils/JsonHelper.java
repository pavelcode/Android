package com.cblue.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonHelper {

	public static void main(String[] args) {

		// 第一种：序列化数组
		/*
		 * String[] arrStr = new String[] { "shilei", "liquan", "shanhouwang",
		 * "liangzhi" };
		 * 
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("firstJsonString", arrStr);
		 * 
		 * String jsonString1 = JSONSerializer.toJSON(arrStr).toString(); String
		 * jsonString2 = JSONSerializer.toJSON(map).toString();
		 * System.out.println(jsonString1); System.out.println(jsonString2);
		 * 
		 * String[] arrStrings = jsonStringToArray(jsonString2,
		 * "firstJsonString");
		 * 
		 * for (int i = 0; i < arrStrings.length; i++) {
		 * System.out.println(arrStrings[i]); }
		 * 
		 * JSONArray jsonArray = JSONArray.fromObject(jsonString1);
		 * 
		 * System.out.println("以下是反序列化："); String[] strArr = new
		 * String[jsonArray.size()]; for (int i = 0; i < jsonArray.size(); i++)
		 * { strArr[i] = jsonArray.getString(i); }
		 * 
		 * System.out.println(strArr);
		 */

		// 第二种：序列化Map集合
		/*
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("username", "wangxiangjun"); map.put("password", "123456");
		 * map.put("email", "wxj@gmail.com");
		 * 
		 * Map<String, Object> map2 = new HashMap<String, Object>();
		 * map2.put("myJsonString2", map); System.out.println(map2); String
		 * jsonString2 = JSONSerializer.toJSON(map2).toString();
		 * System.out.println(jsonString2);
		 * 
		 * Map<String, Object> map3 = jsonStringToMap(jsonString2, new String[]
		 * { "username", "password", "email" }, "myJsonString2");
		 * 
		 * System.out.println(map3);
		 */

		// 第三种：序列化list集合
	/*	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 5; i++) {
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("username", "wangxiangjun_" + i);
			map2.put("password", "123456_" + i);
			map2.put("email", "wxj@gmail.com_" + i);
			list.add(map2);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("myJsonString10", list);

		String jsonString3 = new JSONSerializer.toJSON(map).toString();
		System.out.println(jsonString3);

		List<Map<String, Object>> list5 = jsonStringToList(jsonString3,
				new String[] { "username", "password", "email" },
				"myJsonString10");
		System.out.println(list5);

	}*/
	}

	// 将json字符串反序列化成字符串数组
	public static String[] jsonStringToArray(String jsonString, String key) {
		JSONArray jsonArray = null;
		try {
		if (key == null) {
				jsonArray = new JSONArray(jsonString);
		} else {

				JSONObject jsonObject = new JSONObject(jsonString);
			jsonArray = jsonObject.getJSONArray(key);
		}

			String[] arrString = new String[jsonArray.length()];
			for (int i = 0; i < jsonArray.length(); i++) {
			arrString[i] = jsonArray.getString(i);
		}
			return arrString;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 将json字符串反序列化成Map对象
	public static Map<String, Object> jsonStringToMap(String jsonString,
			String[] keyNames, String key) {
		JSONObject jsonObject = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
		if (key == null) {
			jsonObject = new JSONObject(jsonString);
			for (int i = 0; i < keyNames.length; i++) {
				map.put(keyNames[i], jsonObject.get(keyNames[i]));
			}
		} else {
			jsonObject = new JSONObject(jsonString);
			JSONObject jsonObject2 = jsonObject.getJSONObject(key);
			for (int i = 0; i < keyNames.length; i++) {
				map.put(keyNames[i], jsonObject2.get(keyNames[i]));
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

	// json字符串反序列化成List对象
	public static List<Map<String, Object>> jsonStringToList(String jsonString,
			String[] keyNames, String key) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		JSONObject jsonObject = null;
		JSONArray jsonArray = null;
		try {
		if (key == null) {
				jsonArray = new JSONArray(jsonString);

		} else {
				jsonObject = new JSONObject(jsonString);
			jsonArray = jsonObject.getJSONArray(key);
		}

			for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject2 = jsonArray.getJSONObject(i);
			Map<String, Object> map = new HashMap<String, Object>();

			for (int j = 0; j < keyNames.length; j++) {
				map.put(keyNames[j], jsonObject2.get(keyNames[j]));
			}
			list.add(map);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/*
	 * 【备注：】使用哪个方法来解析json字符串呢？ 要分析json字符串的格式。在json字符串中"["表示数组，"{"表示对象。
	 * 当看到json字符串有“[”，就用JSONArray，见到“{”就用JSONObject。
	 * 如果两者都有，那么解析的时候就两者都用。谁先出现就先用谁。
	 */
}
