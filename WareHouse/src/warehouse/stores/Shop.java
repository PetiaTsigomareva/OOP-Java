package warehouse.stores;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import warehouse.clients.Client;
import warehouse.products.Product;

public class Shop extends Store {
	private static final int ADDED_AVAILABILITY = 5;
	private Warehouse warehouse;
	private List<Client> clients;

	public Shop(String name, Warehouse warehouse) {
		super(name);
		this.warehouse = warehouse;
		this.clients = new ArrayList<>();

	}

	public void loadProducs(Product deficitProduct) {
		Map<Product, Integer> shopProducts = this.getProducts();
		Integer currentAvailabilityInShop = shopProducts.get(deficitProduct);
		Integer currentAvailabilityInWarehouse = warehouse.getProducts().get(deficitProduct);

		if (currentAvailabilityInWarehouse != null && currentAvailabilityInWarehouse >= Store.INITIAL_AVAILABILITY) {
			shopProducts.put(deficitProduct, currentAvailabilityInShop + ADDED_AVAILABILITY);
			warehouse.getProducts().put(deficitProduct, currentAvailabilityInWarehouse - ADDED_AVAILABILITY);
		} else {
			warehouse.getSupplyer().deliveryOfGoods(deficitProduct);
			loadProducs(deficitProduct);

		}

	}

	/**
	 * @return the warehouse
	 */
	public Warehouse getWarehouse() {
		return warehouse;
	}

	/**
	 * @param warehouse
	 *          the warehouse to set
	 */
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	/**
	 * @return the clients
	 */
	public List<Client> getClients() {
		return clients;
	}

	/**
	 * @param clients
	 *          the clients to set
	 */
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public void addClients(Client client) {
		clients.add(client);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString();
	}

}
