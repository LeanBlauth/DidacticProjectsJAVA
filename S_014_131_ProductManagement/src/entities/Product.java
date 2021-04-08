package entities;

public class Product {
	
	protected String name;
	protected double price;
	
	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}
	
	public String toString() {
		return name + " $"+ price;
	}
}
