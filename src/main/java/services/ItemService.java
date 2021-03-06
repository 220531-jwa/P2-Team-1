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
	
	public Item getItemById(int itemId) {
		
		return id.getItemById(itemId);
	}

	public int checkoutRemoveInventory(int itemId, int totalRemove){
		int i = id.checkoutRemoveInventory(itemId, totalRemove);
		if(i <= 0) {
			return 0;
		} else {
			return i;
		}
	}

	public boolean checkStock(int[] itemIds){
		boolean checkoutOkay = true;
		for(int i = 0; i < itemIds.length; i++){
			int remaining = id.checkStock(itemIds[i]);
			if(remaining == 0){
				checkoutOkay = false;
			}
		}
		return checkoutOkay;
	}
}
