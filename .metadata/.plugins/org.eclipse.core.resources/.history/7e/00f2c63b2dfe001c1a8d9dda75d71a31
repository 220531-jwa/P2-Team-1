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
}
