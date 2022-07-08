package services;

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
}
