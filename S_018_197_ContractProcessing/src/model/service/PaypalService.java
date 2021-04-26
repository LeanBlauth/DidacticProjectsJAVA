package model.service;

public class PaypalService implements OnlinePaymentService {
	
	public static final double interestRate = 0.01;
	public static final double serviceFee = 0.02;
	private double amount;
	private int month;
	
	public PaypalService(double amount, int month) {
		this.amount = amount;
		this.month = month;
	}

	@Override
	public double paymentFee(double amount) {
		return amount*(1 + serviceFee);
	}
	
	@Override
	public double interest(double amount, int month) {
		return amount*(1 + interestRate);
	}

	@Override
	public double calculations(double amount, int months) {
		return(paymentFee(interest(amount, months)));
	}
}
