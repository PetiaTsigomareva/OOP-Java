package sweethouse;

import java.time.LocalDate;
import java.util.Map;

import sweethouse.cake.Cake;
import sweethouse.customer.Customer;
import tools.Tools;

public class Order {
	private Customer customer;
	private Supplier supplier;
	private double price;
	private String address;
	private Map<Cake, Integer> cakes;
	private LocalDate delivaryDate;

	public Order(Customer customer, Supplier supplier, double price, String address, Map<Cake, Integer> cakes,
	    LocalDate delivaryDate) {
		super();
		this.customer = customer;
		this.supplier = supplier;
		this.price = price;
		this.address = address;
		this.cakes = cakes;
		this.delivaryDate = delivaryDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustmer(Customer customer) {
		this.customer = customer;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getDelivaryDate() {
		return delivaryDate;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Map<Cake, Integer> getCakes() {
		return cakes;
	}

	public void setCakes(Map<Cake, Integer> cakes) {
		this.cakes = cakes;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setDelivaryDate(LocalDate delivaryDate) {
		this.delivaryDate = delivaryDate;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return customer.getClass().getSimpleName()
		       + " "
		       + customer.getName()
		       + ",supplier="
		       + supplier.getName()
		       + ", price="
		       + String.format("%.2f", price)
		       + ", address="
		       + address
		       + ", deliveryDate="
		       + delivaryDate
		       + ", cakes=\n"
		       + Tools.formatMap(cakes);
	}

}
