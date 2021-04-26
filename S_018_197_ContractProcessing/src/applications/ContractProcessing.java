package applications;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.service.ContractService;
import model.service.PaypalService;

public class ContractProcessing {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("---- Contract Management ----\n");
		System.out.println("Please provide the contract data\n");
		
		System.out.print("Number: ");
		int number = getValidInt(scan);
		System.out.print("Date [dd/mm/yyyy]: ");
		Date date = dateFormat.parse(scan.nextLine());
		System.out.print("Contract value: ");
		double value = getValidDouble(scan);
		System.out.print("Enter the number of installments: ");
		int installments = getValidInt(scan);
		
		Contract contract = new Contract(number, date, value, installments);
		
		ContractService.processContract(contract, new PaypalService(value, installments));
		
		contract.printInstallments();

		scan.close();
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
