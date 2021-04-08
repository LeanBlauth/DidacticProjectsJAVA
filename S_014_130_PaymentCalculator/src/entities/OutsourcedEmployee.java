package entities;

public class OutsourcedEmployee extends Employee {

	private 		double additionalCharge;
	private static  double bonusRate = 1.1;
	
	public OutsourcedEmployee() {
		super();
	}

	public OutsourcedEmployee(String name, int hours, double valuePerHour) {
		super(name, hours, valuePerHour);
	}
	
	public OutsourcedEmployee(String name, int hours, double valuePerHour, double additionalCharge) {
		super(name, hours, valuePerHour);
		this.additionalCharge = additionalCharge;
		
	}
	
	@Override
	public double payment() {
		return valuePerHour*hours + bonusRate*additionalCharge;
	}
	
	@Override
	public String toString() {
		return name + " - $" + payment();
	}
}
