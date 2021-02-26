package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		System.out.println("Enter rental data");
		System.out.print("Car model: ");
		String model = sc.nextLine();
		System.out.print("Pickup (dd/MM/yyyy HH:mm): ");
		Date start = sdf.parse(sc.nextLine());
		System.out.print("Return (dd/MM/yyyy HH:mm): ");
		Date finish = sdf.parse(sc.nextLine());

		CarRental cr = new CarRental(start, finish, new Vehicle(model));

		System.out.print("Enter price per hour: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("Enter price per day: ");
		double pricePerDay = sc.nextDouble();

		RentalService rs = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());

		rs.processInvoice(cr);

		System.out.println("INVOICE: ");
		System.out.printf("Basic payment: %.2f\n", cr.getInvoice().getBasicPayment());
		System.out.printf("Tax: %.2f\n", cr.getInvoice().getTax());
		System.out.printf("Total payment: %.2f\n", cr.getInvoice().getTotalPayment());

		sc.close();
	}

}
