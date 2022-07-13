package services;

import java.util.List;

import models.Ticket;
import repositories.TicketDAO;

public class TicketService {
	
	private static TicketDAO td;
	
	public TicketService(TicketDAO td) {
		this.td = td;
	}
	
	public Ticket submitNewTicket(int id, Ticket readTicket) {
		
		return td.submitNewTicket(id, readTicket);
	}
	
	public List<Ticket> getAllTickets(int id){
		
		return td.getAllTickets(id);
	}
	
	public List<Ticket> getAllTicketsAdmin(){
		return td.getAllTicketsAdmin();
	}
	
	public Ticket getSingleTicketAdmin(int id) {
		return td.getSingleTicketAdmin(id);
	}
	
	public Ticket updateTicketAdmin(int id, String newStatus) {
		return td.updateTicketAdmin(id, newStatus);
	}
	
	public Ticket getTicketById(int id, int ticketId) {
		return td.getTicketById(id, ticketId);
	}
}
