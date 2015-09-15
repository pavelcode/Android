package com.cblue.reflect;

import java.lang.reflect.Method;


class Student{
	
	public void study(){
		System.out.println("study执行");
	}
	
	public void study(String lession){
		System.out.println(lession);
	}
}




public class Demo05 {

	/**
	 * 调用方法
	 * @param 
	 */
	public static void main(String[] args)throws Exception {
		Class<?> cls = Class.forName("reflect.Student");
		Object obj = cls.newInstance();
		//无参方法
		Method m1 = cls.getMethod("study");
		m1.invoke(obj, null);
		
		//得到方法对象
		Method m = cls.getMethod("study", java.lang.String.class);
		Object o = m.invoke(obj,"aaa");
	}

}
