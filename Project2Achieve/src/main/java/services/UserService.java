package services;

import repositories.UserDAO;

public class UserService {
	private static UserDAO ud;
	
	public UserService(UserDAO ud) {
		this.ud = ud;
	}
	
	public double addBalance(int id, double amount ) {
		return ud.addBalance(id, amount);
	}
}
