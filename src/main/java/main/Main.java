package main;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.put;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Main {
	public static void main(String[] args) {
		
		
		Javalin app = Javalin.create(config -> {
			config.enableCorsForAllOrigins(); //config.enableCors origin mapping needed
			config.addStaticFiles("/public", Location.CLASSPATH);
		});
		app.start(8081);
		
		
			
	}


}
