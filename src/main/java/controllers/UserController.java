package controllers;

import io.javalin.http.Context;
<<<<<<< HEAD
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
=======
import models.User;
import services.UserService;

public class UserController {
    private static UserService us;
    public UserController(UserService us){
        this.us = us;
    }

    public static void createUser(Context ctx){
        User u = ctx.bodyAsClass(User.class);
        String username = u.getUsername();
        String password = u.getPassword();
        String name = u.getName();
        try{
            User s = us.createUser(username, password, name);
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
>>>>>>> origin/katie_corbett
}
