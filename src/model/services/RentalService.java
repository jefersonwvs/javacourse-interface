package model.services;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {

	private double pricePerHour;
	private double pricePerDay;

	private BrazilTaxService taxService;

	public RentalService(double pricePerHour, double pricePerDay, BrazilTaxService taxService) {
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxService = taxService;
	}

	public void processInvoice(CarRental carRental) {
		
		long t1 = carRental.getStart().getTime();
		long t2 = carRental.getFinish().getTime();
		double hours = (double) (t2 - t1) / 1000 / 60 / 60;

		double basicPayment = (hours <= 12.0) ?  Math.ceil(hours) * pricePerHour  :  Math.ceil(hours / 24) * pricePerDay;
		
		double tax = taxService.tax(basicPayment);

		carRental.setInvoice(new Invoice(basicPayment, tax));
	}

}
