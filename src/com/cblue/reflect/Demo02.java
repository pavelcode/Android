package com.cblue.reflect;

class Person{

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Person's toString method";
	}
	
}

public class Demo02 {

	  public static void main(String[] args) {
		 
//		  Person person = new Person();
//		  System.out.println(person);
		  
		  try {
			Class<?> cls = Class.forName("reflect.Person");
			Person person = (Person) cls.newInstance();
			System.out.println(person);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  
		  
		  
	}
}
