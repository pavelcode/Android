package com.cblue.media.musicdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cblue.android.R;


public class Contacts {
	
	public static List<Map<String,Object>> songs =new ArrayList<Map<String,Object>>();
	
	public static void initData(){
		Map<String,Object> song1 = new HashMap<String,Object>();
		song1.put("image", R.drawable.a);
		song1.put("name","最美");
		song1.put("path", R.raw.beautiful);
		songs.add(song1);
		
		Map<String,Object> song2 = new HashMap<String,Object>();
		song2.put("image", R.drawable.a);
		song2.put("name","千里之外");
		song2.put("path", R.raw.faraway);
		songs.add(song2);

	}

}
