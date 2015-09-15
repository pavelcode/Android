package com.cblue.reflect;


public class Demo01 {

	/**
	 * 得到类属于那个包
	 * 说明都必须得到Class类
	 * @param args
	 */
	public static void main(String[] args)throws Exception{
		java.util.Date date = new java.util.Date();  
	   //第一种
	   //System.out.println(date.getClass().getName()); 
       //第二种
	   // Class<?> cls = java.util.Date.class;
       // System.out.println(cls.getName());
	   //第三种
	   Class<?> cls = Class.forName("java.util.Date");
	   System.out.println(cls.getName());

	
    }

}
