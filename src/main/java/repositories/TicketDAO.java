package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import models.Ticket;
import utils.ConnectionUtil;

public class TicketDAO {
	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	public Ticket submitNewTicket(int id, Ticket readTicket) {
		String sql = "Insert into achieveapp.tickets values(default, ?, ?, ?, ?)";
		
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, readTicket.getStatus());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
