package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.User;
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
				bal = rs1.getDouble("balance") + amount; //adding to make bal = current + deposit
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
    public User createUser(String username, String password, String name, int accounttype){
        String sql = "insert into achieveapp.users values (default, ?, ?, ?, ?, default, default) returning *;";

        try(Connection connect = cu.getConnection()){
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, name);
            ps.setInt(4, accounttype);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("accounttype"),
                        rs.getDouble("balance"),
                        rs.getInt("rewardPoints")
                );
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        System.out.println("the issues is in the DAO Layer");
        return null;
    }

    public List<String> getAllUsernames(){
        String sql = "select username from achieveapp.users;";

        try(Connection connect = cu.getConnection()){
            PreparedStatement ps = connect.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            List<String> usernames = new ArrayList<>();
            while(rs.next()){
                usernames.add(rs.getString("username"));
            }
            return usernames;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public User getUser(String username){
        String sql = "select * from achieveapp.users where username = ?;";

        try(Connection connect = cu.getConnection()){
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("accountType"),
                        rs.getDouble("balance"),
                        rs.getInt("rewardPoints")
                );
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public double updateBalance(int id, double total){
        String sql = "update achieveapp.users set balance = balance - ? where id = ? returning *;";

        try(Connection connect = cu.getConnection()){
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setDouble(1, total);
            ps.setInt(2, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return rs.getDouble("balance");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return 0.00;
    }
    public int addRewardPoints(int id, int rewardPoints){
        String sql = "update achieveapp.users set rewardPoints = rewardPoints + ? where id = ? returning *;";

        try(Connection connect = cu.getConnection()){
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, rewardPoints);
            ps.setInt(2, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return rs.getInt("rewardPoints");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public int showRewardPoints(int id){
        String sql = "select rewardPoints from achieveapp.users where id = ?;";

        try(Connection connect = cu.getConnection()){
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return rs.getInt("rewardPoints");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
}

