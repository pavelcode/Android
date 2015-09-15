package com.cblue.reflect;

interface Fruit {
	public void eat();
}

class Apple implements Fruit {
	public void eat() {
		System.out.println("吃苹果");
	}
}

class Orange implements Fruit {
	public void eat() {
		System.out.println("吃橘子");
	}
}

class Factory {

	public static Fruit getFruit(String fruitName) {

		Fruit fruit = null;
		try {
		 if(fruitName=="Apple"){
			 fruit = new Apple();
		 }
		 if(fruitName=="Orange"){
			 fruit= new Orange();
		 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fruit;

	}
}

public class Demo03 {

	public static void main(String[] args) {
/*         Fruit fruit = Factory.getFruit("Orange");
         fruit.eat();*/
         
     	Fruit fruit = Factory.getFruit("Apple");
    	fruit.eat();
    }
}
