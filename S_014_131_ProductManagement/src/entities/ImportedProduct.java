package entities;

public class ImportedProduct extends Product {

	private double customsFee;
	
	public ImportedProduct(String name, double price, double customsFee) {
		super(name, price);
		this.customsFee = customsFee;
	}

	private double totalPrice() {
		return price + customsFee;
	}
	
	@Override
	public String toString() {
		return name + " $"+ totalPrice()
				+ " [Customs fee: $"+customsFee+"]"; 
	}
	
}
