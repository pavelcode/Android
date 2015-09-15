package com.cblue.interaction.json;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.content.res.AssetManager;

public class JSONTool {


	private Context context;
	public JSONTool(Context context){
		this.context = context;
	}
	
	public User parseJSONToObject(String jsonStr)throws Exception{
	
		User user = new User();
		JSONObject jsonObject = new JSONObject(jsonStr);
		user.setName(jsonObject.getString("name"));
		user.setAge(jsonObject.getInt("age"));
		return user;
		
	}
	
	public List<User> parseJSONToArray(String jsonStr)throws Exception{
		List<User> users = new ArrayList<User>();
		JSONArray jsonArray = new JSONArray(jsonStr);
		for(int i=0;i<jsonArray.length();i++){
			User user = new User();
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			user.setName(jsonObject.getString("name"));
			user.setAge(jsonObject.getInt("age"));
			users.add(user);
		}
		return users;
	}
	
	public String getJSONStr()throws Exception{
		 AssetManager mAssetManager = context.getAssets();
		 InputStream input = mAssetManager.open("user.json");
		 BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		 StringBuffer stringBuffer = new StringBuffer();
		 String line = null;
		 while(((line= reader.readLine())!=null)){
			 stringBuffer.append(line);
		 }
		 return stringBuffer.toString();
	}

	
}
