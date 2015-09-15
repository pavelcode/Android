package com.cblue.interaction.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class FastJsonTool {

	public static String createJsonString(Object object) {
		String jsonString = JSON.toJSONString(object);
		return jsonString;
	}

	public static <T> T createJsonBean(String jsonString, Class<T> cls) {
		T t = JSON.parseObject(jsonString, cls);
		return t;
	}

	public static <T> List<T> createJsonToListBean(String jsonString,
			Class<T> cls) {
		List<T> list = null;
		list = JSON.parseArray(jsonString, cls);
		return list;
	}

	public static List<Map<String, Object>> createJsonToListMap(
			String jsonString) {
		List<Map<String, Object>> list2 = JSON.parseObject(jsonString,
				new TypeReference<List<Map<String, Object>>>(){});
		return list2;
	}
	
	public static <T> List<Map<String,User>> creatJSONToListMapObject(String jsonString){
		 List<Map<String, User>> list = JSON.parseObject(jsonString,new TypeReference<List<Map<String,User>>>(){});
		  return list;
	}

	public static void main(String[] args) {

		// 1 Object
		String jsonStr1 = createJsonString(JSONData.getSingleObject());
		System.out.println(jsonStr1);
		System.out.println(createJsonBean(jsonStr1, User.class));

		// 2 list<Object>
		String jsonStr2 = createJsonString(JSONData.getListUser());
		System.out.println(jsonStr2);
		List<User> list = createJsonToListBean(jsonStr2,User.class);
		for(User user:list){
		System.out.println(user);
		}
		// 3 list<String>
        String jsonStr3 = createJsonString(JSONData.getListStr());
		System.out.println(jsonStr3);
		List<String> citys = createJsonToListBean(jsonStr3,String.class); 
		for(String city: citys){
			
			System.out.println(city);
		}

		// 4 List<Map<String,String>>
	    String jsonStr4 = createJsonString(JSONData.getListMap());
		System.out.println(jsonStr4);
		List<Map<String,Object>> listMap = createJsonToListMap(jsonStr4);
		for(Map<String,Object> map :listMap){
			for(Map.Entry<String, Object> entry:map.entrySet()){
				System.out.println(entry.getKey()+"----"+entry.getValue());
			}
		}
		
		
		// 5 List<Map<String,User>>
		String jsonStr5 = createJsonString(JSONData.getListMap2());
		System.out.println(jsonStr5);
		List<Map<String,User>> listMap1 = creatJSONToListMapObject(jsonStr5);
		for(Map<String,User> map :listMap1){
			for(Map.Entry<String, User> entry:map.entrySet()){
				System.out.println(entry.getKey());
				//User user = (User)entry.getValue();
				System.out.println("----"+entry.getValue());
			}
		}
		
	
		
		
/*		 List<Map<String,Object>>  listMap = new ArrayList<Map<String,Object>>();
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("id1", new User("tianqi1",1));
		 map.put("id2", new User("tianqi2",2));
		 listMap.add(map);
		 String listMapStr = createJsonString(listMap);
		 System.out.println(listMapStr);
		 System.out.println(creatJSONToListMapObject(listMapStr,User.class));*/
	}
}