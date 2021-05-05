package applications;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class EmployeeDataSearch {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		System.out.println("---- Employee Data Search ----\n");
		System.out.print("Enter file path relative to current workspace: ");
		String path = scan.nextLine();

		try (BufferedReader bRead = new BufferedReader(new FileReader(path))) {

			List<Employee> empList = new ArrayList<>();
			String line = bRead.readLine();
			while (line != null) {
				String[] entryFields = line.split(",");
				empList.add(new Employee(entryFields[0], entryFields[1], Double.parseDouble(entryFields[2])));
				line = bRead.readLine();
			}

			System.out.print("\nProvide salary threshold for search :");
			double salaryThreshold = getValidDouble(scan);

			Comparator<String> alphabeticalSort = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			List<String> emailList = empList.stream()
					.filter(emp -> emp.getSalary() > salaryThreshold)
					.map(emp -> emp.getEmail())
					.sorted(alphabeticalSort)
					.collect(Collectors.toList());
			
			System.out.println("\nEmail from people whose salary is greater than " + String.format("%.2f", salaryThreshold) + " :");
			emailList.forEach(System.out::println);
			
			double sum = empList.stream()
					.filter(emp -> emp.getName().charAt(0) == 'M')
					.map(emp -> emp.getSalary())
					.reduce(0.0, (x,y) -> x + y);
					
			System.out.print("\nSum of salary of all people whose name begins with 'M': " + String.format("%.2f", sum));

			scan.close();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

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
