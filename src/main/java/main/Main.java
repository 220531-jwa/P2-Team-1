package main;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.patch;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;
import static io.javalin.apibuilder.ApiBuilder.delete;

import controllers.ItemController;
import controllers.TicketController;
import controllers.UserController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import repositories.ItemDAO;
import repositories.TicketDAO;
import repositories.UserDAO;
import services.ItemService;
import services.TicketService;
import services.UserService;

public class Main {
	public static void main(String[] args) {
		
		ItemController ic = new ItemController(new ItemService(new ItemDAO()));
		UserController uc = new UserController(new UserService(new UserDAO()), new ItemService(new ItemDAO()));
		TicketController tc = new TicketController(new TicketService(new TicketDAO()));
		
		Javalin app = Javalin.create(config -> {
			config.enableCorsForAllOrigins(); //config.enableCors origin mapping needed
			config.addStaticFiles("/public", Location.CLASSPATH);
		});
		app.start(8081);
		
		app.routes(() -> {
			path("/createAccount", () -> {
				post(UserController::createUser);
			});
			path("/login", () -> {
				post(UserController::loginUser);
			});
			path("/item", () -> {
				get(ic::getAllItems);
				path("/{itemId}", () ->{
					get(ic::getItemById);
				});
			});
			path("/user", () ->{
				path("/{id}", () ->{
					get(UserController::displayRewardPoints);
					path("/balance", () ->{
						patch(uc::addBalance);
					});
					path("/checkout", () -> {
						patch(UserController::checkout);
					});
					path("/tickets", ()->{
						get(tc::getAllTickets);
						post(tc::submitNewTicket);
						path("/{ticketId}", ()->{
							get(tc::getTicketById);
							delete(tc::deleteTicketById);
						});
					});
				});
			});
			path("/admin", ()-> {
				path("/ticket", ()-> {
					get(tc::getAllTicketsAdmin);
					path("/{ticketId}", () ->{
						get(tc::getSingleTicketAdmin);
						put(tc::updateTicketAdmin);
					});
				});
			});
			path("/seller/{sellerID}", ()-> {
				path("/items", ()->{
					get(ic::getAllSellerItems);
					post(ic::createNewItem);
					path("/{itemId}", ()-> {
						put(ic::updateItem);
						delete(ic::deleteItem);
					});
				});
			});
		});
	}
}
