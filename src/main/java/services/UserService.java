package services;

import models.User;
import repositories.UserDAO;

import java.util.List;

public class UserService {
	private static UserDAO ud;
	
	public UserService(UserDAO ud) {
		this.ud = ud;
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
	public User createUser(String username, String password, String name, int accounttype){
		if(checkUniqueUsername(username)){
			return ud.createUser(username, password, name, accounttype);
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

	public double updateBalance(int id, double total){
		double newBalance = ud.updateBalance(id, total);
		if(newBalance <= 0.00){
			System.out.println("Insufficient balance");
			return 0.00;
		} else {
			return newBalance;
		}
	}

	public int addRewardPoints(int id, double balance){
		int rewardPoints = (int) (balance / 10);
		return ud.addRewardPoints(id, rewardPoints);
	}

	public int showRewardPoints(int id){
		return ud.showRewardPoints(id);
	}
}

