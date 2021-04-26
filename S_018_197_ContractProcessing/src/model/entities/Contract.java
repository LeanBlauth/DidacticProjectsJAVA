package model.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Contract {
	private int number;
	private Date date;
	private double totalValue;
	private int installments;
	private double finalValue;
	List<Installment> installmentList = new ArrayList<>();
	
	public Contract(int number, Date date, double totalValue, int installments) {
		this.number = number;
		this.date = date;
		this.totalValue = totalValue;
		this.installments = installments;
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		Date installmentDate;
		for (int i = 0; i < installments; i++) {
			c.add(Calendar.MONTH, +1);
			installmentDate = c.getTime();
			installmentList.add(new Installment(installmentDate, getInstallmentValue(), i+1));
		}
	}

	public int getNumber() {
		return number;
	}

	public Date getDate() {
		return date;
	}

	public double getTotalValue() {
		return totalValue;
	}
	
	public double getInstallmentNumber() {
		return installments;
	}
	
	public double getInstallmentValue() {
		return totalValue/installments;
	}
	
	public List<Installment> getInstallmentList(){
		return installmentList;
	}
	
	public void printInstallments() {
		System.out.println("\nInstallments:");
		for (Installment installment : installmentList) {
			System.out.println(installment);
			finalValue += installment.getFinalAmount();
		}
		System.out.println("\nTotal value of installments: $" + String.format("%.2f", finalValue));
	}
}
