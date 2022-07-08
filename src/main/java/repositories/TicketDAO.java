package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import models.Ticket;
import utils.ConnectionUtil;

public class TicketDAO {
	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	public Ticket submitNewTicket(int id, Ticket readTicket) {
		String sql = "Insert into achieveapp.tickets values(default, ?, ?, ?, ?, ?) returning *";
		
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, "open");
			ps.setString(2, readTicket.getDescription());
			
			LocalDateTime now = LocalDateTime.now(); 
			   Timestamp timestamp = Timestamp.valueOf(now);
			ps.setTimestamp(3, timestamp);
			ps.setInt(4, id);
			ps.setString(5, readTicket.getSubject());
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return new Ticket(
						rs.getInt("id"),
						rs.getString("status"),
						rs.getString("subject"),
						rs.getString("description"),
						rs.getDate("submissiontime")
						);	
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return null;
	}
}
