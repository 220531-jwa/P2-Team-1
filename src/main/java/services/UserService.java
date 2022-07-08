package services;

import models.User;
import repositories.UserDAO;

import java.util.List;

public class UserService {
	private static UserDAO ud;
	
	public UserService(UserDAO ud) {
		UserService.ud = ud;
	}
	
	public double addBalance(int id, double amount ) {
		return ud.addBalance(id, amount);
	}
	public static boolean checkUniqueUsername(String username){
		List<String> usernames = ud.getAllUsernames();
		for (String s : usernames) {
			if (s.equals(username)) {
				return false;
			}
		}
		return true;
	}
	public User createUser(String username, String password, String name){
		if(checkUniqueUsername(username)){
			return ud.createUser(username, password, name);
		} else {
			return null;
		}
	}

	public User loginUser(String username, String password){
		User u = ud.getUser(username);
		if (u == null){
			System.out.println("Username not found");
			return null;
		}
		if(u.getPassword().equals(password)){
			return u;
		} else {
			System.out.println("Wrong password");
			return null;
		}
	}
}

