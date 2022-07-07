package main;
<<<<<<< HEAD

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.patch;
import static io.javalin.apibuilder.ApiBuilder.path;

import controllers.ItemController;
import controllers.UserController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import repositories.ItemDAO;
import repositories.UserDAO;
import services.ItemService;
import services.UserService;

public class Main {
	public static void main(String[] args) {
		
		ItemController ic = new ItemController(new ItemService(new ItemDAO()));
		UserController uc = new UserController(new UserService(new UserDAO()));
		
		Javalin app = Javalin.create(config -> {
			config.enableCorsForAllOrigins(); //config.enableCors origin mapping needed
			config.addStaticFiles("/public", Location.CLASSPATH);
		});
		app.start(8081);
		
		
		app.routes(() -> {
			path("/item", () ->{
				get(ic::getAllItems);
			});
			
			path("/user", () ->{
				path("/{id}", () ->{
					path("/balance", () ->{
						patch(uc::addBalance);
					});
				});
			});
		});
	}
=======
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.put;

import controllers.UserController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import repositories.UserDAO;
import services.UserService;

public class Main {
    public static void main(String[] args) {
        UserController uc = new UserController(new UserService(new UserDAO()));

        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
            config.addStaticFiles("/public", Location.CLASSPATH);
        });
        app.start(8081);
        app.routes(() -> {
            path("/createAccount", () -> {
                post(UserController::createUser);
            });
            path("/login", () -> {

            });
        });


    }
>>>>>>> origin/katie_corbett


}
