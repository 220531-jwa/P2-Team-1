package services;

import java.util.List;

import models.Item;
import repositories.ItemDAO;

public class ItemService {
	private static ItemDAO id;
	
	public ItemService(ItemDAO id) {
		this.id = id;
	}
	
	public List<Item> getAllItems(){
		return id.getAllItems();
	}

	public void deleteItem(int id2) {
		// TODO Auto-generated method stub
	id.deleteItem(id2);
	}
	
	public List<Item> getAllSellerItems(int sellerId) {
		return id.getAllSellerItems(sellerId);
	}
	
	public Item updateItem(int sellerId, int itemId, Item update) {
		return id.updateItem(sellerId, itemId, update);
	}

	public Item createNewItem(String name, double cost, String description, int sellerId, int inventory){
		Item i = id.createNewItem(name, cost, description, sellerId, inventory);
		if(i == null){
			System.out.println("Item not created");
			return null;
		} else {
			return i;
		}
	}
}
