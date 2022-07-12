package models;

public class Cart {
    private double total;

    public Cart(){

    }

    public Cart(double total){
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
