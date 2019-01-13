package sweethouse.customer;

import sweethouse.SweetHouse;
import tools.Tools;

public class PrivateCustomer extends Customer {
	private int voucherCount;
	private int voucherValue;
	private static final double SUPPLIER_TIP_PERCENTAGE = 0.02;

	public PrivateCustomer(String name, String phoneNumber, String address, SweetHouse sweetHouse, int count, int value) {
		super(name, phoneNumber, address, sweetHouse);
		this.voucherCount = count;
		this.voucherValue = value;
	}

	public int getVoucherCount() {
		return voucherCount;
	}

	public int getVoucherValue() {
		return voucherValue;
	}

	@Override
	public String toString() {
		return "PrivateCustomer :" + super.toString() + voucherCount + " vouchers of " + voucherValue + " BGN";
	}

	@Override
	public boolean isPrivateCustomer() {
		return true;
	}

	@Override
	public double getDiscount(double price) {
		double result;
		double allVouchers;

		allVouchers = voucherCount * voucherValue;

		if (price >= allVouchers) {
			result = voucherCount * voucherValue;
			voucherCount = 0;
			voucherValue = 0;
		} else {
			result = 0;

			// use each iteration one of the vouchers
			while (voucherCount > 0 && voucherValue <= price) {
				// used one voucher
				result = result + voucherValue;
				price = price - voucherValue;
				voucherCount--;
			}
		}

		return result;
	}

	@Override
	public double getSupplierTipPercentage() {
		return SUPPLIER_TIP_PERCENTAGE;
	}

	@Override
	public int getOrderCakesCount() {
		return Tools.getRandomNumber(1, 3);
	}
}
