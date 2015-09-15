package com.cblue.reflect;

import java.lang.reflect.Field;




class Book{
	private String name;
}

/**
 * 给私有属性设置值
 */

public class Demoxx3 {
	public static void main(String[] args)throws Exception {
		 Class<?> cls =Book.class;
		 Object object = cls.newInstance();
		 Field field  = cls.getDeclaredField("name");
		 //field.setAccessible(true);
		 field.set(object, "aaa");
		 System.out.println(field.get(object));
	}

}
