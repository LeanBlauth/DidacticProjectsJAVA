package applications;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.ImportedProduct;
import entities.Product;
import entities.UsedProduct;

public class ProductManagement {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);

		System.out.println("-------- Product Management --------");
		System.out.print("Enter the number of products: ");
		int numProducts = getValidInt(scan);
		char[] validSet = {'c','u','i'};
		List<Product> productList= new ArrayList<>();
		
		for (int i = 0; i < numProducts; i++ ) {
			System.out.println("\nProduct #"+(i+1)+" data:");
			System.out.print("Common, used or imported [c/u/i] ? ");	
			char answer = getValidAnswer(validSet, scan);
			System.out.print("Name: ");
			String name = scan.nextLine();
			System.out.print("Price: ");
			Double price = getValidDouble(scan);
			
			switch (answer) {
				case 'i': 	System.out.print("Customs fee: ");
							double fee = getValidDouble(scan);
							productList.add(new ImportedProduct(name, price, fee));
							break;
				case 'u':	System.out.print("Manufacture date: ");
							Date date = UsedProduct.dateFormat.parse(scan.next());
							productList.add(new UsedProduct(name, price, date));
							break;
				default:	productList.add(new Product(name, price));
			}
		}
			
		System.out.println("\nPRICE TAGS:");
		
		for (Product product : productList) {
			System.out.println(product);
		}
		
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







