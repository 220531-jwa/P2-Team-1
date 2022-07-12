package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Item;
import utils.ConnectionUtil;

public class ItemDAO {
	
	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	public List<Item> getAllItems() {
		
		String sql = "select * from achieveapp.items";
		List<Item> items = new ArrayList<>();
		
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				items.add(new Item(
						rs.getString("name"),
						rs.getDouble("cost"),
						rs.getString("description"),
						rs.getInt("id"),
						rs.getInt("sellerid"),
						rs.getInt("inventory")
						));
			}
			
			if(items.size() == 0) {
				return null;
			}
			return items;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void deleteItem(int id2) {
		// TODO Auto-generated method stub
		String sql = "delete from accounts where id = ?";
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id2);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Item> getAllSellerItems(int sellerId){
			
		String sql = "select * from achieveapp.items where sellerId = ?";
		List<Item> items = new ArrayList<>();
			
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, sellerId);
				
			ResultSet rs = ps.executeQuery();
				
			while(rs.next()) {
				items.add(new Item(
						rs.getString("name"),
						rs.getDouble("cost"),
						rs.getString("description"),
						rs.getInt("id"),
						rs.getInt("sellerid"),
						rs.getInt("inventory")
						));
			}
				
			if(items.size() == 0) {
				return null;
			}
			return items;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
