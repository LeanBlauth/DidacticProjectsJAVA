package applications;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import entities.LogEntry;

public class AccessLog {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("---- Access Log ----\n");
		System.out.print("Enter file path relative to current workspace: ");
		String path = scan.nextLine();
		
		try (BufferedReader bRead = new BufferedReader(new FileReader(path))) {
			
			Set<LogEntry> accessSet = new HashSet<>();
			String line = bRead.readLine();
			while(line != null) {
				String[] entryFields = line.split(" ");
				String userName = entryFields[0];
				Date accessTime = Date.from(Instant.parse(entryFields[1]));
				accessSet.add(new LogEntry(userName, accessTime));
				line = bRead.readLine();
			}
			System.out.print("\nTotal users: " + accessSet.size());
			scan.close();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

}
