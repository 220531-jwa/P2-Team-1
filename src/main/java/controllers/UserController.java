package controllers;

import io.javalin.http.Context;
import models.Cart;
import models.User;
import services.ItemService;
import services.UserService;

public class UserController {
	private static UserService us;
	private static ItemService is;
	
	public UserController(UserService us, ItemService is) {
		this.us = us;
		this.is = is;
	}
	
	public void addBalance(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		Double amount = Double.parseDouble(ctx.body());
		double changed = us.addBalance(id, amount);
		
		if(changed == -1) {
			ctx.status(404);
		}
		else {
			ctx.status(200);
			ctx.json(changed);
		}
	}
	public static void createUser(Context ctx){
		User u = ctx.bodyAsClass(User.class);
		String username = u.getUsername();
		String password = u.getPassword();
		String name = u.getName();
		int accountType = u.getAccountType();
		try{
			User s = us.createUser(username, password, name, accountType);
			if(s != null){
				ctx.status(200);
				ctx.json(s);
			} else {
				ctx.status(404);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void loginUser(Context ctx){
		User u = ctx.bodyAsClass(User.class);
		String username = u.getUsername();
		String password = u.getPassword();
		try{
			User s = us.loginUser(username, password);
			if(s != null){
				ctx.status(200);
				ctx.json(s);
			} else {
				ctx.status(404);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void checkout(Context ctx){
		int id = Integer.parseInt(ctx.pathParam("id"));
		Cart cart = ctx.bodyAsClass(Cart.class);
		double total = cart.getTotal();
		//remove items from inventory
		int[] itemIds = cart.getItemIds();
		boolean checkoutOkay = is.checkStock(itemIds);
		if(checkoutOkay == true){
			//remove total from balance
			try{
				double balance = us.updateBalance(id, total);
				//add reward points
				us.addRewardPoints(id, total);
				//remove items from inventory
				for(int i = 0; i < itemIds.length; i++){
					is.checkoutRemoveInventory(itemIds[i], 1);
				}
				if(balance <= 0.00){
					ctx.status(404);
				} else {
					ctx.status(200);
					ctx.json(balance);
				}
			} catch(Exception e){
				e.printStackTrace();
			}
		} else {
			String message = "No inventory";
			ctx.json(message);
			ctx.status(404);
		}
	}

	public static void displayRewardPoints(Context ctx){
		int id = Integer.parseInt(ctx.pathParam("id"));
		int rewardPoints = us.showRewardPoints(id);
		ctx.json(rewardPoints);
	}
}
