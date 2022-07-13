package models;

public class Item {
	
	private String name;
	private double cost;
	private String desc;
	private int id;
	private int sellerId;
	private int inventory;
	
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	
	public Item(String name, double cost, String desc, int id, int sellerId, int inventory) {
		super();
		this.name = name;
		this.cost = cost;
		this.desc = desc;
		this.id = id;
		this.sellerId = sellerId;
		this.inventory = inventory;
	}
	
	public Item() {
		super();
	}
}
