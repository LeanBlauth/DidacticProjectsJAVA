package entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import entities.enums.WorkerLevel;

public class Worker {

	private Department			department;
	private String 				name;
	private WorkerLevel 		level;
	private Double 				baseSalary;
	private List<HourContract> 	contracts = new ArrayList<>();
	
	public Worker() {
		
	}
	
	public Worker(Department department, String name, WorkerLevel level, Double baseSalary) {
		this.department = department;
		this.name = name;
		this.level = level;
		this.baseSalary = baseSalary;
	}
	
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public void setWorkerLevel(WorkerLevel level) {
		this.level = level;
	}
	
	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}
	
	public Department getDepartment() {
		return department;
	}
	
	public String getName() {
		return name;
	}
	
	public WorkerLevel getWorkerLevel() {
		return level;
	}
	
	public void addContract(HourContract contract) {
		contracts.add(contract);
	}
	
	public void removeContract(HourContract contract) {
		contracts.remove(contract);
	}
	
	public Double income(int year, int month) {
		Double income = baseSalary;
		Calendar cal = Calendar.getInstance();
		for (HourContract contract : contracts) {
			cal.setTime(contract.getDate());
			int contractYear 	=     cal.get(Calendar.YEAR);
			int contractDay		=	  cal.get(Calendar.DAY_OF_MONTH);
			int contractMonth 	= 1 + cal.get(Calendar.MONTH);
			if ((contractYear == year)&&(contractMonth == month)) income += contract.totalReturn(); 
		}
		return income;
	}
	
	public String getValidLevel(Scanner scan) {
		
		boolean valid = false;
		 String level;
		
		 do {
			System.out.print("\nLevel: JUNIOR , MID_LEVEL or SENIOR ? ");
			
			level = scan.next();
			
			for (WorkerLevel wl : WorkerLevel.values()) {
				
				if (wl.name().equals(level)) {
					valid = true;
					break;
				}
			}
	
		} while (!valid);
		return level;
	}
}













