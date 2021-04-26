package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Installment {

	private Date dueDate;
	private double amount;
	private double finalAmount;
	private int month;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public Installment(Date dueDate, double amount, int month) {
		this.dueDate = dueDate;
		this.amount = amount;
		this.month = month;
	}

	public double getAmount() {
		return amount;
	}
	
	public int getMonth() {
		return month;
	}
	
	public void setFinalAmount(double value) {
		finalAmount = value;
	}
	
	public double getFinalAmount() {
		return finalAmount;
	}

	@Override
	public String toString() {
		return dateFormat.format(dueDate) + " - $" + String.format("%.2f", finalAmount);
	}
}
