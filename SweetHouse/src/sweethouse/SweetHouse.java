package sweethouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sweethouse.cake.Cake;
import sweethouse.customer.Customer;
import sweethouse.info.PersonInfo;
import tools.Tools;

public class SweetHouse extends PersonInfo {

	private double keshDesk;
	private Map<Cake, Integer> catalog;
	private Map<Cake, Integer> sold;
	private Set<Supplier> suppliers;
	private List<Order> orders;

	public SweetHouse(String name, String phoneNumber, String address) {
		super(name, phoneNumber, address);
		this.catalog = new HashMap<>();
		this.sold = new HashMap<>();

		this.suppliers = new HashSet<>();
		this.orders = new ArrayList<>();
	}

	public double getKeshDesk() {
		return keshDesk;
	}

	public void addKeshToDesk(double keshDesk) {
		this.keshDesk = this.keshDesk + keshDesk;
	}

	public Map<Cake, Integer> getCatalog() {
		return catalog;
	}

	public void setCatalog(Map<Cake, Integer> catalog) {
		this.catalog = catalog;
	}

	public Map<Cake, Integer> getSold() {
		return sold;
	}

	public void setSold(Map<Cake, Integer> sold) {
		this.sold = sold;
	}

	public Set<Supplier> getSuppliers() {
		return suppliers;
	}

	public void addSupplier(Supplier supplier) {
		this.suppliers.add(supplier);
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public void addCake(Cake cake, int availability) {
		Integer currentavaialability = catalog.get(cake);
		if (currentavaialability == null) {
			catalog.put(cake, availability);
		} else {
			catalog.put(cake, currentavaialability + availability);

		}
		System.out.println("Added: " + cake + " (" + availability + ")");

	}

	public void removeSoldCakeFromCatalog(Map<Cake, Integer> cakes) {
		Integer currentAvailability;
		Integer requestedQuantity;
		Integer newAvaiability;

		for (Cake cake : cakes.keySet()) {
			currentAvailability = catalog.get(cake);
			requestedQuantity = cakes.get(cake);

			if (currentAvailability != null && currentAvailability > 0) {
				newAvaiability = currentAvailability - requestedQuantity;
				catalog.put(cake, newAvaiability);
				System.out.println("Available: "
				                   + cake.toString()
				                   + " ("
				                   + currentAvailability
				                   + " - "
				                   + requestedQuantity
				                   + " = "
				                   + newAvaiability
				                   + ")");
			} else {
				System.out.println("Cake is not availabal in this catalog!");
			}
		}

	}

	public void addSoldCakeToSoldMap(Map<Cake, Integer> cakes) {
		Integer currentAvailability;
		Integer requestedQuantity;
		Integer newAvaiability;

		for (Cake cake : cakes.keySet()) {
			currentAvailability = sold.get(cake);
			requestedQuantity = cakes.get(cake);

			if (currentAvailability != null && currentAvailability > 0) {
				newAvaiability = currentAvailability + requestedQuantity;
				sold.put(cake, newAvaiability);
			} else {
				newAvaiability = requestedQuantity;
				sold.put(cake, newAvaiability);
			}

			System.out.println("Sold: "
			                   + cake.toString()
			                   + " ("
			                   + currentAvailability
			                   + " + "
			                   + requestedQuantity
			                   + " = "
			                   + newAvaiability
			                   + ")");
		}
	}

	public void createOrder(Map<Cake, Integer> cakes, Customer customer) {
		Order order;
		double orderPrice = 0;
		double customerDiscount;
		Map<Cake, Integer> orderAvailableCakes = new HashMap<>();
		double orderTip;
		double netOrderPrice;

		Tools.printFrame("Requested cakes", 40);
		Tools.printMap(cakes);

		// check if random cakes is available of the sweethouse catalog
		for (Cake cake : cakes.keySet()) {
			Integer cakeRequested = cakes.get(cake);
			Integer cakeAvailability = getCatalog().get(cake);

			if (cakeRequested != null && cakeAvailability != null && cakeRequested <= cakeAvailability) {
				orderAvailableCakes.put(cake, cakeRequested);
			} else {
				System.out.println("Not available: " + cake + "(" + cakeRequested + ")");
			}
		}

		// create order
		if (!orderAvailableCakes.isEmpty()) {
			for (Cake cake : orderAvailableCakes.keySet()) {
				Integer quantity = orderAvailableCakes.get(cake);
				orderPrice += cake.getPrice() * quantity;
			}

			customerDiscount = customer.getDiscount(orderPrice);
			orderPrice = orderPrice - customerDiscount;

			// random supplier
			Supplier orderSupplier = Tools.getRandom(getSuppliers());
			order = new Order(customer, orderSupplier, orderPrice, getAddress(), orderAvailableCakes,
			    Tools.getRandomDate());

			orders.add(order);
			customer.getOrders().add(order);
			orderSupplier.getOrders().add(order);

			// tips of the order price
			orderTip = order.getPrice() * order.getCustomer().getSupplierTipPercentage();
			order.getSupplier().addTip(orderTip);

			// add order price to sweet house cash desk
			netOrderPrice = order.getPrice() - orderTip;
			addKeshToDesk(netOrderPrice);

			// add cakes to sold map
			System.out.println("---");
			addSoldCakeToSoldMap(order.getCakes());
			System.out.println("---");
			removeSoldCakeFromCatalog(order.getCakes());
			System.out.println("---");

			System.out.println("Create order: " + order.toString());

		} else {
			System.out
			    .println("The order for following cakes could not be fulfilled, cakes are not availible in Sweethouse \""
			             + getName()
			             + "\"\n:"
			             + cakes.toString());
		}
	}

	@Override
	public String toString() {
		return "SweetHouse : " + super.toString();
	}
}