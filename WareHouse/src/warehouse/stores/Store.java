package warehouse.stores;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import warehouse.products.Product;
import warehouse.products.Product.Name;

public abstract class Store {

	public static final int INITIAL_AVAILABILITY = 15;

	private String name;
	private Map<Product, Integer> products;

	public Store(String name) {
		super();
		this.name = name;
		this.products = new HashMap<>();

		Set<Product.Name> productNames = Product.getAllProductsName();
		for (Name productName : productNames) {
			products.put(new Product(productName), INITIAL_AVAILABILITY);

		}

	}

	public String getName() {
		return name;
	}

	public Map<Product, Integer> getProducts() {
		return products;
	}

	public void addProduct(Product product, int availability) {
		Integer currentAvailability = products.get(product);
		if (currentAvailability == null) {
			products.put(product, availability);
		} else {
			products.put(product, currentAvailability + availability);
		}
		System.out.println("Added: " + product + " (" + availability + ")");

	}

	public boolean isDeficitProduct(Product product) {
		boolean result;

		int currentAvailability = products.get(product);
		result = currentAvailability < INITIAL_AVAILABILITY;

		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name + ":\n" + products.toString() + "\n";
	}

}
