package applications;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Company;
import entities.Individual;
import entities.TaxPayer;

public class TaxCalculator {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);
		List<TaxPayer> payerList = new ArrayList<>();
		
		System.out.println("-------- Tax Calculator --------");
		
		System.out.print("\nEnter the number of tax payers: ");
		int numberOfPayers = getValidInt(scan);
		
		for (int i = 0; i < numberOfPayers; i++) {
			System.out.println("\nProvide tax payer #"+(i+1)+" data:");
			System.out.print(" Invividual or company [i/c]: ");
			char validSet[] = {'i','c'};
			char answer = getValidAnswer(validSet,scan);
			System.out.print(" Name: ");
			String name = scan.nextLine();
			System.out.print(" Anual income: ");
			double income = getValidDouble(scan);
			if (answer == 'c') {
				System.out.print(" Number of employees: ");
				int numberOfEmployees = getValidInt(scan);
				payerList.add(new Company(name, income, numberOfEmployees));
			} else {
				System.out.print(" Health expenditures: ");
				double healthExpend = getValidDouble(scan);
				payerList.add(new Individual(name, income, healthExpend));
			}
		}
		
		System.out.println("\nTAXES PAID:");
		double totalTaxes = 0;
		for (TaxPayer payer : payerList) {
			System.out.println(payer);
			totalTaxes += payer.tax();
		}
		
		System.out.print("\nTOTAL TAXES: " + totalTaxes);
		
		scan.close();
	
	}	// End of main
	
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

	public static char getValidAnswer(char[] validSet, Scanner scan) {
		
		Boolean adequate = false;
		char answer;
		
		do {
			answer = (scan.next()).charAt(0);
			scan.nextLine();
			
			for (char valid : validSet) {
				if (answer == valid) {
					adequate = true;
					break;
				}	
			}
			if (!adequate) System.out.print("\nPlease provide a valid answer: ");
		} while (!adequate); 
		return answer;
	}

}
