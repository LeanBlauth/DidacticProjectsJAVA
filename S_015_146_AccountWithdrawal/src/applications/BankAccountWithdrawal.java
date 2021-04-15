package applications;

import java.util.Locale;
import java.util.Scanner;

import entities.Account;
import model.exceptions.DomainException;

public class BankAccountWithdrawal {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);
		
		try {
			System.out.println("-------- Bank Account Withdrawal --------");
			System.out.println("\nPlease provide the account data");
			System.out.print("Number: ");
			int number = getValidInt(scan);
			System.out.print("Holder: ");
			String holder = scan.nextLine();
			System.out.print("Initial balance: ");
			double balance = getValidDouble(scan);
			System.out.print("Withdraw limit: ");
			double limit = getValidDouble(scan);
			
			Account account = new Account(number, holder, balance, limit);
			
			System.out.print("\nEnter amount for withdraw: ");
			double withdraw = getValidDouble(scan);
			
			account.withdraw(withdraw);
			
			System.out.println("\nNew balance: " + String.format("%.2f", account.getBalance()));
			scan.close();
		}		
		catch (DomainException e) {
			System.out.println("\nError: " + e.getMessage());
		}
		catch (RuntimeException e) {
			System.out.println("\nUnexpected error.");
		}
	}

	public static int getValidInt(Scanner scan) {
		int value = 0;
		do {
			if (value < 0)
				System.out.print("Please provide a positive value: ");
			value = scan.nextInt();
			scan.nextLine();
		} while (value < 0);
		return value;
	}

	public static double getValidDouble(Scanner scan) {
		double value = 0.0;
		do {
			if (value < 0)
				System.out.print("Please provide a positive value: ");
			value = scan.nextDouble();
			scan.nextLine();
		} while (value < 0);
		return value;
	}
}
