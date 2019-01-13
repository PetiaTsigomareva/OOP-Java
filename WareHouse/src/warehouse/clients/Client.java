package warehouse.clients;

import java.util.Map;

import warehouse.products.Product;
import warehouse.stores.Shop;
import warehouse.stores.Store;

public class Client {
	private String name;
	private Shop shop;

	public Client(String name) {
		super();
		this.name = name;
	}

	/**
	 * @return the shop
	 */
	public Shop getShop() {
		return shop;
	}

	/**
	 * @param shop
	 *          the shop to set
	 */
	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public void buyProducts(Product product, int quantity) {
		Map<Product, Integer> shopProducts = shop.getProducts();
		Integer currentAvaialbility = shopProducts.get(product);
		Integer newAvailability;

		if (currentAvaialbility != null && currentAvaialbility >= Store.INITIAL_AVAILABILITY) {
			newAvailability = currentAvaialbility - quantity;
			shopProducts.put(product, newAvailability);
			System.out.println("Buyed product:\n " + product + " with quantity " + quantity + "\n");

		} else {
			System.out.println("The product hasn't enough availability.Wait for Suppling!");
			shop.loadProducs(product);

			buyProducts(product, quantity);
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
	}

}
