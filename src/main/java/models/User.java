package models;

public class User {
	
	private String username;
	private String password;
	private int id;
	private String name;
	private int accountType;
	private double balance;
	private int rewardPoints;

	public User() {
		super();
	}

	public User(String username, String password, int id, String name, int accountType, double balance, int rewardPoints) {
		this.username = username;
		this.password = password;
		this.id = id;
		this.name = name;
		this.accountType = accountType;
		this.balance = balance;
		this.rewardPoints = rewardPoints;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAccountType() {
		return accountType;
	}
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
}
