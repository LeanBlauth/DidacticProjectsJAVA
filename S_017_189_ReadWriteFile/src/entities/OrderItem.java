package entities;

public class OrderItem {
	private String product;
	private double price;
	private int quantity;
	
	public OrderItem(String product, double price, int quantity) {
		this.product = product;
		this.price = price;
		this.quantity = quantity;
	}
	
	public String getProduct() {
		return product;
	}
	
	public double itemCost() {
		return price*quantity;
	}
}
