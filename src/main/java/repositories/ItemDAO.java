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

		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				items.add(new Item(
						rs.getString("name"),
						rs.getDouble("cost"),
						rs.getString("description"),
						rs.getInt("id"),
						rs.getInt("sellerid"),
						rs.getInt("inventory"),
						rs.getString("imglink")
				));
			}

			if (items.size() == 0) {
				return null;
			}
			return items;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void deleteItem(int id2) {
		String sql = "delete from achieveapp.items where id = ?";
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id2);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Item> getAllSellerItems(int sellerId) {

		String sql = "select * from achieveapp.items where sellerId = ?";
		List<Item> items = new ArrayList<>();

		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, sellerId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				items.add(new Item(
						rs.getString("name"),
						rs.getDouble("cost"),
						rs.getString("description"),
						rs.getInt("id"),
						rs.getInt("sellerid"),
						rs.getInt("inventory"),
						rs.getString("imglink")
				));
			}

			if (items.size() == 0) {
				return null;
			}
			return items;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Item updateItem(int sellerId, int itemId, Item update) {
		String sql = "Update achieveapp.items set name = ?, cost = ?, description = ?, inventory = ?, imglink = ? where id = ? and sellerId = ? returning *";

		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, update.getName());
			ps.setDouble(2, update.getCost());
			ps.setString(3, update.getDesc());
			ps.setInt(4, update.getInventory());
			ps.setString(5, update.getImglink());
			ps.setInt(6, itemId);
			ps.setInt(7, sellerId);
			

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new Item(
						rs.getString("name"),
						rs.getDouble("cost"),
						rs.getString("description"),
						rs.getInt("id"),
						rs.getInt("sellerid"),
						rs.getInt("inventory"),
						rs.getString("imglink")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Item createNewItem(String name, double cost, String description, int sellerId, int inventory){
		String sql = "insert into achieveapp.items values (default, ?, ?, ?, ?, ?, default) returning *;";

		try(Connection connect = cu.getConnection()){
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, name);
			ps.setDouble(2, cost);
			ps.setString(3, description);
			ps.setInt(4, sellerId);
			ps.setInt(5, inventory);

			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				return new Item(
						rs.getString("name"),
						rs.getDouble("cost"),
						rs.getString("description"),
						rs.getInt("id"),
						rs.getInt("sellerid"),
						rs.getInt("inventory"),
						rs.getString("imglink")
				);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public Item getItemById(int itemId) {
		String sql = "select * from achieveapp.items where id = ?";
				
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, itemId);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return new Item(
						rs.getString("name"),
						rs.getDouble("cost"),
						rs.getString("description"),
						rs.getInt("id"),
						rs.getInt("sellerid"),
						rs.getInt("inventory"),
						rs.getString("imglink")
						);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}