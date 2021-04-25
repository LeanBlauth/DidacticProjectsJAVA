package model.entities;

public class Invoice {

	private double basicPayment;
	private double tax;

	public Invoice(double basicPayment, double tax) {
		this.basicPayment = basicPayment;
		this.tax = tax;
	}

	public double getBasicPayment() {
		return basicPayment;
	}

	public void setBasicPayment(double basicPayment) {
		this.basicPayment = basicPayment;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getTotalPayment() {
		return getBasicPayment() + getTax();
	}
	
	@Override
	public String toString() {
		return "INVOICE:"
				+ "\n  Basic payment: " + String.format("%.2f", basicPayment)
				+ "\n  Tax: " + String.format("%.2f", tax)
				+ "\n Total payment: " + String.format("%.2f", getTotalPayment());
	}
}
