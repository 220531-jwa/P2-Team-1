package controllers;

import io.javalin.http.Context;
import services.UserService;

public class UserController {
	private static UserService us;
	
	public UserController(UserService us) {
		this.us = us;
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
}
