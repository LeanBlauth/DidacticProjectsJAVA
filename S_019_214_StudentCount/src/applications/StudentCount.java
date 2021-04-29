package applications;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import entities.Student;

public class StudentCount {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("---- Studen Count ----");
		
		int studentCode;
		Set<Student> courseA = new HashSet<>();
		Set<Student> courseB = new HashSet<>();
		Set<Student> courseC = new HashSet<>();

		System.out.print("\nHow many students for course A ? ");
		int populationA = getValidInt(scan);
		for (int i = 0; i < populationA; i++) {
			System.out.print("Student code: ");
			studentCode = getValidInt(scan);
			courseA.add(new Student(studentCode));
		}
		
		System.out.print("\nHow many students for course B ? ");
		int populationB = getValidInt(scan);		
		for (int i = 0; i < populationB; i++) {
			System.out.print("Student code: ");
			studentCode = getValidInt(scan);
			courseB.add(new Student(studentCode));
		}
		
		System.out.print("\nHow many students for course C ? ");
		int populationC = getValidInt(scan);
		for (int i = 0; i < populationC; i++) {
			System.out.print("Student code: ");
			studentCode = getValidInt(scan);
			courseC.add(new Student(studentCode));
		}
		
		courseA.addAll(courseB);
		courseA.addAll(courseC);
		
		System.out.println("\nTotal students: " + courseA.size());
		
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
	
}
