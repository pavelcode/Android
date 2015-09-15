package com.cblue.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

class People{
	

	public String nation;
	
}

class User extends People{
	

	private String name;

	public String password;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}


public class Demoxx2 {

	/**
	 * 得到属性 setter/getter方法
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
	  Class<?> cls = User.class;   
	  
	  //得到继承全部public属性
	  Field[] fields = cls.getFields();
	  for(Field field:fields){
	  System.out.println(field.getName());
	  }
	  System.out.println("**************");
	  //得到继承指定属性
	  Field field = cls.getField("password");
	  System.out.println(field.getName());
	  System.out.println("**************");
	  //得到本类全部属性
	  Field [] selfFields = cls.getDeclaredFields();
	  for(Field selfField:selfFields){
		  System.out.println(selfField.getName());
	  }
	  System.out.println("**************");
	  //得到本类的指定属性
	  Field selfField = cls.getDeclaredField("password");
	  System.out.println(selfField.getName());
	  System.out.println("**************");
	  //设置属性
	  Field passwordField = cls.getDeclaredField("password");
	  Object obj = cls.newInstance();
	  passwordField.set(obj, "zhangsan");
	  System.out.println(passwordField.get(obj));
	  System.out.println("**************");
	  //不能访问私有属性
//	  Field nameField = cls.getDeclaredField("name");
//	  Object obj1 = cls.newInstance();
//	  nameField.set(obj1, "zhangsan");
//	  System.out.println(passwordField.get(obj));
	  
	  //设置访问私有属性  
	  Object obj2 = cls.newInstance();
	  Field nameField2 = cls.getDeclaredField("name");
	  nameField2.setAccessible(true);
	  nameField2.set(obj2,"aaa");
	  System.out.println(nameField2);
	  System.out.println(nameField2.get(obj2));
	  
	  //调用setter方法
	  String atrribute="name";
	  Object obj3 = cls.newInstance();
	  Method setterMethod = cls.getMethod("set"+headUpperCase(atrribute), atrribute.getClass()); 
	  setterMethod.invoke(obj3, "地球");
	  //调用getter方法
	  Method getterMethod = cls.getMethod("get"+headUpperCase(atrribute)); 
	  System.out.println(getterMethod.invoke(obj3));
	  
	}
	
	public static String headUpperCase(String str){
		return str.substring(0,1).toUpperCase()+str.substring(1);
	}
	

}
