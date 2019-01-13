package sweethouse.customer;

import java.util.ArrayList;
import java.util.List;

import sweethouse.Order;
import sweethouse.SweetHouse;
import sweethouse.info.PersonInfo;

public abstract class Customer extends PersonInfo {
	private List<Order> orders;
	private SweetHouse sweetHouse;

	public Customer(String name, String phoneNumber, String address, SweetHouse sweetHouse) {
		super(name, phoneNumber, address);
		this.sweetHouse = sweetHouse;
		this.orders = new ArrayList<>();
	}

	public abstract double getDiscount(double price);

	public abstract double getSupplierTipPercentage();

	public abstract int getOrderCakesCount();

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public SweetHouse getSweetHouse() {
		return sweetHouse;
	}

	public void setSweetHouse(SweetHouse sweetHouse) {
		this.sweetHouse = sweetHouse;
	}

	public boolean isPrivateCustomer() {
		return false;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}