package applications;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;

public class orderManagement {

	public static void main(String[] args) throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		Scanner scan  = new Scanner(System.in);
		Order   order = new Order();

		System.out.println("-------- Order Management --------\n\n");

		System.out.println("Enter client data:");
		System.out.print("Name: ");
		String clientName = scan.nextLine();
		System.out.print("email: ");
		String email = scan.nextLine();
		System.out.print("birthDate [dd/mm/yyyy]: ");
		Date bDay = dateFormat.parse(scan.next());
		
		Client client = new Client(clientName, email, bDay);

		System.out.println("\nEnter order data: ");
		System.out.print("Order status: ");
		String status = getValidStatus(scan);
		System.out.print("\nHow many items to this order ? ");
		int itemNumber = getValidInt(scan);

		order = new Order(new Date(), OrderStatus.valueOf(status), client);
		
		for (int i = 0; i < itemNumber; i++) {
			System.out.println("\nEnter item # " + (i+1) + " data: ");
			System.out.print("Product name: ");
			String productName = scan.nextLine();
			System.out.print("Product price: ");
			double price = getValidDouble(scan);
			System.out.print("Quantity: ");
			int quantity = getValidInt(scan);

			Product product = new Product(productName, price);
			OrderItem orderItem = new OrderItem(quantity, product);
			order.addItem(orderItem);
		}
		
		System.out.println("\n----------------------------------------------------------------------");
		System.out.println("\nORDER SUMMARY:");
		System.out.print("Order moment: " + order.getMoment() 
						+"\nOrder status: " + order.getStatus()
						+"\nClient: "+ client.getName() + " ("
						+ dateFormat.format(client.getBirthDate())
						+ ") - " + client.getEmail());
		System.out.println("\nOrder items: ");
		for (OrderItem orderItem : order.getOrderList()) {
			System.out.println(orderItem);
		}
		System.out.println("\nTotal price: $"+ order.total());
		scan.close();
	}
	
	public static int getValidInt(Scanner scan) {
		int value = 0;
		do {
			if (value < 0) System.out.print("Please provide a positive value: ");
			value = scan.nextInt();
			scan.nextLine();
		} while (value < 0);
		return value;
	}
	
	public static double getValidDouble(Scanner scan) {
		double value = 0.0;
		do {
			if (value < 0) System.out.print("Please provide a positive value: ");
			value = scan.nextDouble();
		} while (value < 0);
		return value;
	}
	
	public static String getValidStatus(Scanner scan) {
		
		boolean valid = false;
		 String input;
		
		 do {
			System.out.println("\nPossible states:\n "
							+ "PENDING_PAYMENT, "
							+ "PROCESSING, "
							+ "SHIPPED, "
							+ "DELIVERED");
			System.out.print("Status: ");

			input = scan.next();
			
			for (OrderStatus status : OrderStatus.values()) {
				
				if (status.name().equals(input)) {
					valid = true;
					break;
				}
			}
	
		} while (!valid);
		return input;
	}
}
