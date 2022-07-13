package controllers;

import java.util.ArrayList;
import java.util.List;

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
	
	public void getAllTickets(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		
		List<Ticket> tickets = new ArrayList<>();
		tickets = ts.getAllTickets(id);
		ctx.status(200);
		ctx.json(tickets);
	}
	
	public void getAllTicketsAdmin(Context ctx) {
		List<Ticket> tickets = new ArrayList<>();
		tickets = ts.getAllTicketsAdmin();
		ctx.status(200);
		ctx.json(tickets);
	}
	
	public void getSingleTicketAdmin(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("ticketId"));
		
		Ticket ticket = ts.getSingleTicketAdmin(id);
		
		ctx.status(200);
		ctx.json(ticket);
	}
	
	public void updateTicketAdmin(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("ticketId"));
		String newStatus = ctx.body();
		
		Ticket ticket = ts.updateTicketAdmin(id, newStatus);
		
		ctx.status(200);
		ctx.json(ticket);
	}
	
	public void getTicketById(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		int ticketId = Integer.parseInt(ctx.pathParam("ticketId"));
		
		Ticket ticket = ts.getTicketById(id, ticketId);
		
		ctx.status(200);
		ctx.json(ticket);
	}
}
