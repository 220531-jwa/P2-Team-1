package controllers;

import io.javalin.http.Context;
import services.TicketService;

public class TicketController {
	private static TicketService ts;
	
	public TicketController(TicketService ts) {
		this.ts = ts;
	}
	
	public void submitNewTicket(Context ctx) {
		
	}
}
