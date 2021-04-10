package entities;

public abstract class TaxPayer {
	
	protected String name;
	protected double anualIncome;
	
	public TaxPayer(String name, double anualIncome) {
		this.name = name;
		this.anualIncome = anualIncome;
	}
	
	public abstract double tax();
	
	public String toString() {
		return 	"Name: "+ name +": $"
				+ String.format("%.2f", tax());
	}
}
