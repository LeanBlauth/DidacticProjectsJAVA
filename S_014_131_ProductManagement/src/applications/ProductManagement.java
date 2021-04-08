package applications;

import java.util.Scanner;

public class ProductManagement {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("-------- Product Management --------");
		System.out.print("Enter the number of products: ");
		int numProducts = getValidInt(scan);
		
		for (int i = 0; i < numProducts; i++ ) {
			System.out.println("\nProduct #"+(i+1)+" data:");
			
			
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

	public static char getValidAnswer(Scanner scan) {
		char answer = 'n';
		do {
			if ((answer != 'y') && (answer != 'n')) {
				System.out.print("Please provide a valid answer [y/n] : ");
			}
			answer = (scan.next()).charAt(0);
			scan.nextLine();
		} while ((answer != 'y') && (answer != 'n'));
		return answer;
	}

}
