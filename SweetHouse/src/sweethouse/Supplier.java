package sweethouse;

import java.util.HashSet;
import java.util.Set;

import sweethouse.info.PersonInfo;
import tools.Tools;

public class Supplier extends PersonInfo {

	private SweetHouse sweetHouse;
	private double tips;
	private Set<Order> orders;

	public Supplier(String name, String phoneNumber, SweetHouse sweetHouse) {
		super(name, phoneNumber, sweetHouse.getAddress());
		this.sweetHouse = sweetHouse;
		this.orders = new HashSet<>();
	}

	/**
	 * @return the sweetHouse
	 */
	public SweetHouse getSweetHouse() {
		return sweetHouse;
	}

	/**
	 * @param sweetHouse
	 *          the sweetHouse to set
	 */
	public void setSweetHouse(SweetHouse sweetHouse) {
		this.sweetHouse = sweetHouse;
	}

	/**
	 * @return the tips
	 */
	public double getTips() {
		return tips;
	}

	/**
	 * @param tips
	 *          the tips to set
	 */
	public void addTip(double tip) {
		this.tips = this.tips + tip;
	}

	/**
	 * @return the orders
	 */
	public Set<Order> getOrders() {
		return orders;
	}

	/**
	 * @param orders
	 *          the orders to set
	 */
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Supplier : "
		       + Tools.format(getName(), 10)
		       + Tools.format(getPhoneNumber(), 12)
		       + "working of the Sweet House"
		       + Tools.format(sweetHouse.getName(), 10)
		       + Tools.format(String.format("%.2f", getTips()), 8);
	}

	public void ptintAllSupplierByTips() {

	}

}
