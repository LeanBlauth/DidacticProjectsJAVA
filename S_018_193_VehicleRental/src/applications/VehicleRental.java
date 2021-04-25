package applications;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class VehicleRental {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		
		
		System.out.println("---- Vehicle Rental ----\n");
		System.out.println("Please enter rental data");
		System.out.print("Vehicle Model: ");
		String model = scan.nextLine();
		System.out.print("\nPickup [dd/mm/yyyy hh:mm]: ");
		Date start = dateFormat.parse(scan.nextLine());
		System.out.print("\nReturn [dd/mm/yyyy hh:mm]: ");
		Date finish = dateFormat.parse(scan.nextLine());
		
		
		CarRental rental = new CarRental(start, finish, new Vehicle(model));
		
		System.out.print("\nEnter price per hour: ");
		double pricePerHour = getValidDouble(scan);
		System.out.print("\nEnter price per day: ");
		double pricePerDay = getValidDouble(scan);
		
		RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());
		
		rentalService.processInvoice(rental);
		
		System.out.println(rental.getInvoice());

		scan.close();
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
