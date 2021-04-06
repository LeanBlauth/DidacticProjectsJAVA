package applications;
 import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class workerIncomeCalculator {

	public static void main(String[] args) throws ParseException {

		Scanner scan = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("--------- Worker Income Calculator ---------"
						 + "\nCalculates a worker's income based on the \n"
						 + "total value of contracts in that month.\n\n");
		
		Worker worker = new Worker();
		
		System.out.print("Enter department's name: ");
		String department = scan.nextLine();
		System.out.println("\nPlease provide worker data:");
		System.out.print("\nName: ");
		String name = scan.nextLine();
		
		String level = worker.getValidLevel(scan);
			
		System.out.print("\nBase salary: ");
		Double baseSalary = getValidDouble(scan);
				
		worker = new Worker(new Department(department), name, WorkerLevel.valueOf(level), baseSalary);
		
		System.out.print("\nHow many contracts to this worker ? ");
		int contractNumber = getValidInt(scan);
		
		for (int i = 0; i < contractNumber ; i++) {
			
			System.out.println("\nEnter contract # " + (i+1) + " data:");
			System.out.print("Date [dd/mm/yyyy]: ");
			Date date = sdf.parse(scan.next());
			System.out.print("Value per hour: ");
			Double valuePerHour = getValidDouble(scan);
			System.out.print("Duration [hours]: ");
			int hours = getValidInt(scan);
			
			HourContract contract = new HourContract(date, valuePerHour, hours);
			worker.addContract(contract);
		}
		
		System.out.println("\nWhat month of what year you wish to calculate "
						 + worker.getName() + "'s income ?" );
		System.out.print("[mm/yyyy]: ");
		String mm_yyyy = scan.next();
		
		int month = Integer.parseInt(mm_yyyy.substring(0,2));
		int year  = Integer.parseInt(mm_yyyy.substring(3,7)); 
		
		
		System.out.print("\nName: " + worker.getName());
		System.out.print("\nDepartment: " + worker.getDepartment());
		System.out.print("\nLevel: " + worker.getWorkerLevel());
		System.out.print("\nIncome for " + mm_yyyy + ": " + worker.income(year, month));

		scan.close();
	}
	
	public static Double getValidDouble(Scanner scan) {
		Double value = 0.0;
		do {
			if (value < 0) System.out.print("Please provide a positive value: ");
			value = scan.nextDouble();
		} while (value < 0);
		return value;
	}
	
	public static Integer getValidInt(Scanner scan) {
		Integer value = 0;
		do {
			if (value < 0) System.out.print("Please provide a positive value: ");
			value = scan.nextInt();
		} while (value < 0);
		return value;
	}
}
