package com.cblue.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

interface DAO {

	public void add();
}

class DAOSupport implements DAO, Cloneable {
	
	private String name;
	
	public DAOSupport(){
		System.out.println("无参构造函数");
	}
	
	public DAOSupport(String name){
		
	}
	
	public void add() {

	}

}

public class Demoxx1 {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			Class<?> cls = Class.forName("reflect.DAOSupport");

			// 得到父类
			Class<?> superClass = cls.getSuperclass();
			System.out.println(superClass.getName());
			System.out.println("*******************************");
			// 得到全部接口
			Class<?>[] interfaces = cls.getInterfaces();
			for(Class<?> interface1:interfaces){
				System.out.println(interface1.getName());
			}
			System.out.println("*******************************");
			//得到全部构造函数
			Constructor [] constructs = cls.getConstructors();
			for(Constructor constructor:constructs){
				System.out.println(constructor);
			}
			System.out.println("*******************************");
			
			//得到指定构造函数
			Constructor contructor = cls.getConstructor(String.class);
			System.out.println(contructor);
			
			//调用无参构造函数
			Constructor noParamContructor = cls.getConstructor();
			noParamContructor.newInstance();
			
			//取得全部方法(包括继承类的方法)
			Method[] methods = cls.getMethods();
			for(Method method:methods){
				System.out.println(method);
			}
			
			//取得指定方法
			Method method = cls.getMethod("add");
			System.out.println(method);
			

			/**
			 * 取得所有方法名
			 * 
			 */
			for(Method listmethod:methods){
				//取得权限
			    System.out.print(Modifier.toString(listmethod.getModifiers())+"  ");
			     //得到返回值
			    Class<?> returnType = listmethod.getReturnType();
			    System.out.print(returnType);
			    System.out.print("  ");
			    
			    //取得所有方法名
				System.out.print(listmethod.getName());
				//得到参数类型
				Class<?> []parameterTypes = listmethod.getParameterTypes();
				System.out.print("( ");
				StringBuffer stringBuffer = new StringBuffer();
				for(Class<?> parameterType:parameterTypes){
					stringBuffer.append(parameterType.getName()+" ,");
				} 
				if(stringBuffer.length()>1){
				  System.out.print(stringBuffer.deleteCharAt(stringBuffer.length()-1));
                  //System.out.println(stringBuffer.length());
				}
                System.out.print(" ) throws ");
				
                //取得异常
               Class<?>[] exceptions = listmethod.getExceptionTypes();
			   for(Class<?> exception:exceptions){
				   System.out.print(exception.getName());
			   }	
               
				System.out.println();
		
			}
			
		
			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
