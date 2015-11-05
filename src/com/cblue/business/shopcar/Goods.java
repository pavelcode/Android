package com.cblue.business.shopcar;


public class Goods {
	public Goods(boolean state, String id, String name, String price, int number) {
		super();
		this.state = state;
		this.id = id;
		this.name = name;
		this.price = price;
		this.number = number;
	}

	private boolean state;
	private String id;

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	private String price;
	private int number;

}
