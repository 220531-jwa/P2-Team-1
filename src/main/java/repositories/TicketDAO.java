package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<Ticket> getAllTickets(int id){
		List<Ticket> tickets = new ArrayList<>();
		String sql = "select * from achieveapp.tickets where accountid = ?";
		
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				tickets.add(new Ticket( 
						rs.getInt("id"),
						rs.getString("status"),
						rs.getString("subject"),
						rs.getString("description"),
						rs.getDate("submissiontime")
						));
			}
			
			if(tickets.size() == 0) {
				return null;
			}
			return tickets;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Ticket> getAllTicketsAdmin(){
		List<Ticket> tickets = new ArrayList<>();
		String sql = "select * from achieveapp.tickets";
		
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				tickets.add(new Ticket( 
						rs.getInt("id"),
						rs.getString("status"),
						rs.getString("subject"),
						rs.getString("description"),
						rs.getDate("submissiontime")
						));
			}
			
			if(tickets.size() == 0) {
				return null;
			}
			return tickets;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Ticket getSingleTicketAdmin(int id) {
		String sql = "select * from achieveapp.tickets where id = ?";
		
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
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
	
	public Ticket updateTicketAdmin(int id, String newStatus) {
		String sql = "UPDATE achieveapp.tickets set status = ? where id = ? returning *";
		
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, newStatus);
			ps.setInt(2, id);
			
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
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Ticket getTicketById(int id, int ticketId) {
		String sql = "select * from achieveapp.tickets where id = ? and accountId = ?";
		
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, ticketId);
			ps.setInt(2, id);
			
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
	
	public void deleteTicketById(int id, int ticketId) {
		
		String sql = "delete from achieveapp.tickets where id = ? and accountId = ?";
		
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, ticketId);
			ps.setInt(2, id);
			
			ps.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}
