package model.services;

public class BrazilTaxServices {
	
	private static final double threshold = 100.0;
	private static final double taxRateUnderThreshold = 0.2;
	private static final double taxRateAboveThreshold = 0.15;
	
	public double tax(double amount) {
		if (amount <= threshold) {
			return amount * taxRateUnderThreshold;
		} else {
			return amount * taxRateAboveThreshold;
		}
	}
}
