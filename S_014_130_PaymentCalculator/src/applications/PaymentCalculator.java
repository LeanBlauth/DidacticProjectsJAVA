package applications;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Employee;
import entities.OutsourcedEmployee;

public class PaymentCalculator {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		List<Employee> employeeList = new ArrayList<>();
		
		Employee emp;

		System.out.println("-------- Payment Calculator --------\n");
		System.out.print("Enter the number of employees: ");
		int empNumber = getValidInt(scan);

		for (int i = 0; i < empNumber; i++) {
			System.out.println("\nEmployee #" + (i + 1) + " data:");
			System.out.print("Outsourced [y/n] ? ");
			char answer = getValidAnswer(scan);
			System.out.print("Name: ");
			String name = scan.nextLine();
			System.out.print("Hours: ");
			int hours = getValidInt(scan);
			System.out.print("Value per hour: ");
			double valuePerHour = getValidDouble(scan);
			if (answer == 'y') {
				System.out.println("Obs.: outsourced employees receive"
						         + "\n      110% of given additional value. ");
				System.out.print("Additional charge: ");
				double addCharge = getValidDouble(scan);
				OutsourcedEmployee outEmp = new OutsourcedEmployee(name, hours, valuePerHour, addCharge);
				employeeList.add(outEmp);
			} else {
				emp = new Employee(name, hours, valuePerHour);
				employeeList.add(emp);
			}
		}
		  System.out.println("\nPAYMENTS: ");
		  for (Employee e : employeeList) {
			  System.out.println(e);
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
