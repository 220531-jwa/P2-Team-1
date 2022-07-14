package controllers;

import java.util.ArrayList;
import java.util.List;

import io.javalin.http.Context;
import models.Item;
import services.ItemService;

public class ItemController {
	private static ItemService is;
	
	public ItemController(ItemService is ) {
		this.is = is;
	}
	
	public void getAllItems(Context ctx) {
		List<Item> items = new ArrayList<>();
		
		items = is.getAllItems();
		
		if(items.size() != 0) {
			ctx.status(200);
			ctx.json(items);
		}
		else if(items.size() == 0 ) {
			ctx.status(404);
		}
	}
	public void deleteItem(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("Itemid"));
		try {
			is.deleteItem(id);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getAllSellerItems(Context ctx) {
		int sellerId = Integer.parseInt(ctx.pathParam("sellerID"));
		
		List<Item> items = new ArrayList<>();
		
		items = is.getAllSellerItems(sellerId);
		
		if(items.size() != 0) {
			ctx.status(200);
			ctx.json(items);
		}
		else if(items.size() == 0) {
			ctx.status(404);
		}
	}
	
	public void updateItem(Context ctx) {
		int sellerId = Integer.parseInt(ctx.pathParam("sellerID"));
		int itemId = Integer.parseInt(ctx.pathParam("itemId"));
		Item update = ctx.bodyAsClass(Item.class);
		
		Item updated = is.updateItem(sellerId, itemId, update);
		ctx.json(updated);
		ctx.status(200);
	}

	public void createNewItem(Context ctx){
		int sellerId = Integer.parseInt(ctx.pathParam("sellerID"));
		Item i = ctx.bodyAsClass(Item.class);
		String name = i.getName();
		String description = i.getDesc();
		double cost = i.getCost();
		int inventory = i.getInventory();

		Item created = is.createNewItem(name, cost, description, sellerId, inventory);
		if(created != null){
			ctx.status(200);
			ctx.json(created);
		} else {
			ctx.status(404);
		}
		
		ctx.status(200);
	}
}
