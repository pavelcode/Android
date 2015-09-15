package com.cblue.reflect;

interface Fruit1 {
	public void eat();
}

class Apple1 implements Fruit1 {
	public void eat() {
		System.out.println("吃苹果");
	}
}


class Orange1 implements Fruit1 {
	public void eat() {
		System.out.println("吃橘子");
	}
}
class Banana1 implements Fruit1 {
	public void eat() {
		System.out.println("吃香蕉");
	}
}
class Factory1 {

	public static Fruit1 getFruit(String classpath) {

		Fruit1 fruit = null;
		try {
			Class<?> cls = Class.forName(classpath);
			fruit = (Fruit1) cls.newInstance();
			return fruit;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}

public class Demo04 {

	public static void main(String[] args) {
         Fruit1 fruit = Factory1.getFruit("reflect.Banana1");
         fruit.eat();
	}
}
