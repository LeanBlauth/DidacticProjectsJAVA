package applications;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.OrderItem;

public class GenerateOrderSummary {

	public static void main(String[] args) {
		
		List<OrderItem> orderList = new ArrayList<>();
		String path = "SourceFile.csv";
		
		try(BufferedReader bRead = new BufferedReader(new FileReader(path))) {
			
			String line = bRead.readLine();
			while(line != null) {
				String[] itemArray = line.split(",", 3);
				orderList.add(new OrderItem(itemArray[0], Double.parseDouble(itemArray[1]), Integer.parseInt(itemArray[2])));
				line = bRead.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());			
		}
		
		path = "C:\\Users\\HP\\Workspace\\ws_eclipse\\S_017_189_ReadWriteFile\\out\\Summary.csv";
		try(BufferedWriter bWrite = new BufferedWriter(new FileWriter(path))){
			for (OrderItem item : orderList) {
				String product = item.getProduct();
				double itemCost = item.itemCost();
				
				bWrite.write(product + ",");
				bWrite.write(String.valueOf(itemCost));
				bWrite.newLine();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
