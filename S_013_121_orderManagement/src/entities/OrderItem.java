package entities;

public class OrderItem {
	
	private int 	quantity;
	private Product product;
	
	public OrderItem(int quantity, Product product) {
		this.quantity = quantity;
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double subTotal() {
		return product.getPrice()*quantity;
	}
	
	public String toString() {
		return 		product.getName() 		 + ", " 
				+	"$"+ product.getPrice()  + ", "
				+	"Quantity: " + quantity  + ", "
				+	"Subtotal: $"+ subTotal();
	}
}
