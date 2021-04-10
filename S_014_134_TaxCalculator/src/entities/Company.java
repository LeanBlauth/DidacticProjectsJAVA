package entities;

public class Company extends TaxPayer{

	private int numberOfEmployees;
	private double taxOverIncome = 0.16;
	private static final int employeeThreshold = 10;
	
	public Company(String name, double anualIncome, int numberOfEmployees) {
		super(name, anualIncome);
		this.numberOfEmployees = numberOfEmployees;
	}
	
	@Override
	public double tax() {
		if (numberOfEmployees > employeeThreshold) taxOverIncome = 0.14;
		return taxOverIncome*anualIncome;
	}
}
