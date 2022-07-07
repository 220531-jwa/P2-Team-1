package repositories;

<<<<<<< HEAD
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
		
		String sql = "select * from achieveapp.items returning *";
		List<Item> items = new ArrayList<>();
		
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				items.add(new Item(
						rs.getString("name"),
						rs.getDouble("cost"),
						rs.getString("desc"),
						rs.getInt("id"),
						rs.getInt("sellerid")
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
=======
public class ItemDAO {


>>>>>>> origin/katie_corbett
}
