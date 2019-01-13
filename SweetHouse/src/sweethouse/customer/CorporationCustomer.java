package sweethouse.customer;

import sweethouse.SweetHouse;
import tools.Tools;

public class CorporationCustomer extends Customer {
	private final double rebate;
	private static final double SUPPLIER_TIP_PERCENTAGE = 0.05;

	public CorporationCustomer(String name, String phoneNumber, String address, SweetHouse sweetHouse, double rebate) {
		super(name, phoneNumber, address, sweetHouse);
		this.rebate = rebate;
	}

	@Override
	public String toString() {
		return "CorporationCustomer: " + super.toString() + rebate;
	}

	@Override
	public double getDiscount(double price) {
		return price * rebate;
	}

	@Override
	public double getSupplierTipPercentage() {
		return SUPPLIER_TIP_PERCENTAGE;
	}

	@Override
	public int getOrderCakesCount() {
		return Tools.getRandomNumber(3, 5);
	}
}
