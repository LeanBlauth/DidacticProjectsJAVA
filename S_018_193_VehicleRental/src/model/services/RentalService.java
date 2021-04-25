package model.services;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {

	private double pricePerDay;
	private double pricePerHour;
	private TaxServices taxService;

	public RentalService(double pricePerDay, double pricePerHour, TaxServices taxService) {
		this.pricePerDay = pricePerDay;
		this.pricePerHour = pricePerHour;
		this.taxService = taxService;
	}

	public void processInvoice(CarRental rental) {
		long startTime = rental.getStart().getTime();
		long finishTime = rental.getFinish().getTime();
		double rentHours = (double) (finishTime - startTime) / 1000 / 60 / 60;

		double basicPayment;
		if (rentHours <= CarRental.timeThreshold) {
			basicPayment = Math.ceil(rentHours) * pricePerHour;
		} else {
			basicPayment = Math.ceil(rentHours/24) * pricePerDay;
		}

		double tax = taxService.tax(basicPayment);
		
		rental.setInvoice(new Invoice(basicPayment, tax));
		
	}

}
