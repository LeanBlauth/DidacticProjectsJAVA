package entities;

import model.exceptions.DomainException;

public class Account {

	private final int number;
	private String holder;
	private double balance;
	private double withdrawLimit;
	
	public Account(int number, String holder, double balance, double withdrawLimit) {
		this.number = number;
		this.holder = holder;
		this.balance = balance;
		this.withdrawLimit = withdrawLimit;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public double getWithdrawLimit() {
		return withdrawLimit;
	}

	public void setWithdrawLimit(double withdrawLimit) {
		this.withdrawLimit = withdrawLimit;
	}

	public int getNumber() {
		return number;
	}

	public double getBalance() {
		return balance;
	}
	
	public void deposit(double amount) {
		balance += amount;
	}
	
	public void withdraw(double amount) throws DomainException {
		if (amount > 0) {
			if (balance == 0) {
				throw new DomainException("Holder can not withdraw from account with zero balance.");
			}
			if (amount > withdrawLimit) {
				throw new DomainException("Amount surpasses withdraw limit.");
			}
			balance -= amount;
		}
	}
	
}
