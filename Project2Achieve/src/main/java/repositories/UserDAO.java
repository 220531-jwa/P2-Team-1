package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.ConnectionUtil;

public class UserDAO {
	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	public double addBalance(int id, double amount) {
		String sql = "UPDATE achieveapp.users set balance = ? where id = ? returning *";
		
		//get and bal are used to retrieve the current balance and update it.
		String get = "Select * from achieveapp.users where id = ?";
		double bal;
		
		try(Connection conn = cu.getConnection()){
			PreparedStatement getter = conn.prepareStatement(get);
			
			getter.setInt(1, id);
			ResultSet rs1 = getter.executeQuery();
			
			if(rs1.next()) {
				bal = rs1.getDouble("cost") + amount; //adding to make bal = current + deposit
			}
			else return -1;
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(2, id);
			ps.setDouble(1, bal);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getDouble("balance");
			}
			
			
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return -1;
	}
}
