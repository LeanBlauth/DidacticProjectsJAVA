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

import entities.Product;

public class AveragePrice {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		System.out.println("---- Average Price ----\n");
		System.out.print("Enter file path relative to current workspace: ");
		String path = scan.nextLine();

		try (BufferedReader bRead = new BufferedReader(new FileReader(path))) {
			
			List<Product> productList = new ArrayList<>();
			String line = bRead.readLine();
			while (line != null) {
				String[] entryFields = line.split(",");
				productList.add(new Product(entryFields[0], Double.parseDouble(entryFields[1])));
				line = bRead.readLine();
			}
			
			double average = productList.stream()
					.map(p -> p.getPrice())
					.reduce(0.0, (x,y) -> x + y) / productList.size();
			
			System.out.println("\nAverage price: " + String.format("%.2f", average));
			
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			List<String> names = productList.stream()
					.filter(p -> p.getPrice() < average)
					.map(p -> p.getName())
					.sorted(comp.reversed())
					.collect(Collectors.toList());
			
			System.out.println("\nProducts priced below average, in alphabetical descending order : ");
			
			names.forEach(System.out::println);
			

			scan.close();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
