package com.cblue.interaction.httpdemo;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JSONParser {
	
	public static List<Lunch> getJSONObject(String jsonStr){
		Gson gson = new Gson();
		return gson.fromJson(jsonStr, new TypeToken<List<Lunch>>(){}.getType());
	}
	
	public static <T> List<T> getJSONObject(String jsonStr,Type type){
		Gson gson = new Gson();
		return gson.fromJson(jsonStr, type);
	}

}
