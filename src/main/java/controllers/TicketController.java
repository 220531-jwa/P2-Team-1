package controllers;

import io.javalin.http.Context;
import models.Ticket;
import services.TicketService;

public class TicketController {
	private static TicketService ts;
	
	public TicketController(TicketService ts) {
		this.ts = ts;
	}
	
	public void submitNewTicket(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		Ticket readTicket = ctx.bodyAsClass(Ticket.class);
		
		Ticket newTicket = ts.submitNewTicket(id, readTicket);
		ctx.status(200);
		ctx.json(newTicket);
			
	}
}
