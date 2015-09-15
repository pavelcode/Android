package com.cblue.interaction.json;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonTool {

	public static String createGsonString(Object object) {
		Gson gson = new Gson();
		String gsonString = gson.toJson(object);
		return gsonString;
	}

	public static <T> T changeGsonToBean(String gsonString, Class<T> cls) {
		Gson gson = new Gson();
		T t = gson.fromJson(gsonString, cls);
		return t;
	}

/*	public static <T> List<T> changeGsonToList(String gsonString, Class<T> cls) {
		Gson gson = new Gson();
		List<T> list_person = gson.fromJson(gsonString,
				new TypeToken<List<T>>() {
				}.getType());
		return list_person;
	}*/
    
    /**
     * 上面的方法：是错误的，首先后面的泛型参数，对程序没有任何影响。其次，使用上面方法不能正确打印出用户对象。（String类型可以，其他都不行，比如自定义数据类型，Integer）
     * 所以，基于上面的问题，变化的内容是new TypeToken<List<User>>(){}.getType(),把变化的内容作为参数提交进去，这个参数是java.lang.reflect.Type     * 
     */
	public static <T> List<T> changeGsonToCollection(String jsonStr,Type type) {
			  Gson gson = new Gson();
			  List<T> list =gson.fromJson(jsonStr, type);
			  return list;
	}
	
	/*public static List<Map<String, Object>> changeGsonToListMaps(String gsonString) {
		List<Map<String, Object>> list = null;
		Gson gson = new Gson();
		list = gson.fromJson(gsonString,
				new TypeToken<List<Map<String, Object>>>() {
				}.getType());
		return list;
	}*/
	
	/*public static <T>List<Map<String,Object>> getListMapFromGSON(String gsonStr,Class<T> cla){
		Gson gson = new Gson();
		List<Map<String,Object>> list = gson.fromJson(gsonStr, new TypeToken<List<Map<String,Object>>>(){}.getType());
	    return list;
	}*/

	public static void main(String[] args) {
		 String jsonStr1 =createGsonString(JSONData.getSingleObject());
		 System.out.println(jsonStr1);
		 System.out.println(changeGsonToBean(jsonStr1,User.class));
		 
		
		 String jsonStr2 = createGsonString(JSONData.getListUser());
		 System.out.println(jsonStr2);
		 List<User> users = changeGsonToCollection(jsonStr2,new TypeToken<List<User>>(){}.getType());
		 for(User user:users){
		 System.out.println(user);
		 }
		 
		 
		
		 String jsonStr3 = createGsonString(JSONData.getListStr());
		 System.out.println(jsonStr3);
		 List<String> citys = changeGsonToCollection(jsonStr3,new TypeToken<List<String>>(){}.getType());
		 for(String c:citys){
	   	 System.out.println(c);
		 }
		 
		 String jsonStr4 = createGsonString(JSONData.getListMap());
		 System.out.println(jsonStr4);
		 List<Map<String,String>> listMap = changeGsonToCollection(jsonStr4, new TypeToken<List<Map<String,String>>>(){}.getType());
		 for(Map<String,String> map:listMap){
			  for(Map.Entry<String, String> entry:map.entrySet()){
				  System.out.println(entry.getKey()+"-----"+entry.getValue());
			  }
		 }
		 
		 

		 String jsonStr5 = createGsonString(JSONData.getListMap2());
		 System.out.println(jsonStr5);
		 List<Map<String,User>> listMap1 = changeGsonToCollection(jsonStr5,new TypeToken<List<Map<String,User>>>(){}.getType());
		 for(Map<String,User> map1:listMap1){
			 for(Map.Entry<String,User> entry:map1.entrySet()){
				 System.out.println(entry.getKey());
				 User user =(User) entry.getValue();
				 System.out.println(user);
			 }
		 }
		
	}


}
