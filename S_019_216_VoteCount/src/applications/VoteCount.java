package applications;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VoteCount {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.println("---- Vote Count ----\n");
		System.out.print("Enter file path relative to current workspace: ");
		String path = scan.nextLine();

		try (BufferedReader bRead = new BufferedReader(new FileReader(path))) {

			Map<String, Integer> voters = new HashMap<>();
			String line = bRead.readLine();
			while (line != null) {
				String[] entryFields = line.split(",");
				String name = entryFields[0];
				Integer votes = Integer.parseInt(entryFields[1]);
				if (!voters.containsKey(name)) {
					voters.put(name, votes);
				} else {
					voters.put(name, voters.get(name) + votes);
				}
				line = bRead.readLine();
			}
			System.out.println();
			for (Map.Entry voter : voters.entrySet()) {
				System.out.println(voter.getKey() + ": " + voter.getValue());
			}
			scan.close();

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
