package com.rajesh.domain;

public class Item {
	
	private int id;
	private String name;
	private float price;
	private int quantity;

	public Item(int id, String name, float price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}



	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}

	

}
