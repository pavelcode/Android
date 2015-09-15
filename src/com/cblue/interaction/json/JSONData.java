package com.cblue.interaction.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * JSON解析的四种数据类型
 * @author Administrator
 *
 */
public class JSONData {

	/**
	 * 单值数据类型
	 * @return
	 */
	public static User getSingleObject(){
		return new User("zhangsan", 5);
	}

	/**
	 * list集合对象数据类型
	 * @return
	 */
	public static List<User> getListUser(){
		List<User> users = new ArrayList<User>();
		users.add(new User("zhangsan", 10));
		users.add(new User("lisi", 20));
        return users;
	}
    
	/**
	 * list集合String数据类型
	 * @return
	 */
	public static List<String> getListStr(){
		List<String> city = new ArrayList<String>();
		city.add("北京");
		city.add("上海");
		city.add("广州");
		return city;
	}
	
	/**
	 * List_Map数据类型
	 * @return
	 */
	public static List<Map<String,Object>> getListMap(){
		List<Map<String, Object>> listM = new ArrayList<Map<String,Object>>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("name","zhaoliu");
		map1.put("age", 100);
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("name","tianqi");
		map2.put("age", 200);
		
		listM.add(map1);
		listM.add(map2);
		return listM;
	}
	
	public static List<Map<String,Object>> getListMap2(){
		 List<Map<String,Object>>  listMap = new ArrayList<Map<String,Object>>();
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("id1", new User("tianqi1",30));
		 map.put("id2", new User("tianqi2",40));
		 listMap.add(map);
		 return listMap;
		
	}

}
