package com.rajesh.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rajesh.domain.Cart;
import com.rajesh.domain.Item;


public class ItemGenerator {
	
private Map<Integer, Item> itemDB=new HashMap<>();
	
	public ItemGenerator() {
		itemDB.put(1, new Item(1, "Macbook", 2300.99f));
		itemDB.put(2, new Item(2, "Dell", 1500.99f));
		itemDB.put(3, new Item(3, "Lenevo", 1560.99f));
		itemDB.put(4, new Item(4, "ASUS", 1560.99f));

	}

	public Collection<Item> fetchAllItems() {
		return itemDB.values();
	}
	
	public Map<Integer,Item> getItemDB(){
		return this.itemDB;
	}
	
	public List<Cart> updator (List<Cart> cart) 
	{ List<Cart> update_item = new ArrayList<>();
		List<Item> original = new ArrayList<Item>(itemDB.values());
		if( cart == null)
		{
			for (Item all:original)
			{
				update_item.add(new Cart(all.getId(),all.getName(),all.getPrice(),0));
				
			}
			return update_item;
		}
		
	 	for(Item all :original)
		{
	 		Cart x= null;
	 		boolean check = false;
			for(Cart c:cart)
			{
				if(c.getId()== all.getId())
				{
					check = true;
					x = c;
					//update_item.add(c);
				}
				
			}
			if(check) {
				update_item.add(x);
			}
			else {
				update_item.add(new Cart(all.getId(),all.getName(),all.getPrice(),0));
			}
			
		}
		
		return update_item;

	}

}
