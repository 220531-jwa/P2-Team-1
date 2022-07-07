package repositories;

import models.User;
import utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    public User createUser(String username, String password, String name){
        String sql = "insert into users values (default, ?, ?, ?, default, default) returning *;";

        try(Connection connect = cu.getConnection()){
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, name);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new User(
                       rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("accounttype"),
                        rs.getDouble("balance")
                );
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getAllUsernames(){
        String sql = "select username from users;";

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
}
