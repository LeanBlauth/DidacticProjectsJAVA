package entities;

public class Individual extends TaxPayer{

	private double healthExpenditures;
	private double taxOverIncome = 0.15;
	private static final double taxThreshold = 20000;
	private static final double discountOverHealth = 0.5;
		
	public Individual(String name, double anualIncome, double healthExpenditures) {
		super(name, anualIncome);
		this.healthExpenditures = healthExpenditures;
	}

	@Override
	public double tax() {
		if (anualIncome > taxThreshold) taxOverIncome = 0.25;
		return taxOverIncome*anualIncome - discountOverHealth*healthExpenditures;
	}
}
