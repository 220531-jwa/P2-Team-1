package services;

import repositories.TicketDAO;

public class TicketService {
	
	private static TicketDAO td;
	
	public TicketService(TicketDAO td) {
		this.td = td;
	}
}
