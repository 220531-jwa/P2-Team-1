package models;

public class Cart {
    private double total;
    private int[] itemIds;

    public Cart(){

    }

    public Cart(double total, int[] itemIds){
        this.total = total;
        this.itemIds = itemIds;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int[] getItemIds() {
        return itemIds;
    }

    public void setItemIds(int[] itemIds) {
        this.itemIds = itemIds;
    }
}
