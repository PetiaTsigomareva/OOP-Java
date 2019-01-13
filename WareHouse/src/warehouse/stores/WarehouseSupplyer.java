package warehouse.stores;

import warehouse.products.Product;

public class WarehouseSupplyer {
	private static final int SUPPLY_AVAILABILITY = 25;
	private Store warehouse;

	public WarehouseSupplyer(Store warehouse) {
		super();
		this.warehouse = warehouse;
	}

	public void deliveryOfGoods(Product product) {
		// check for availability under 15
		if (warehouse.isDeficitProduct(product)) {
			System.out
			    .println("The product "
			             + product
			             + " is deficited current availability : "
			             + warehouse.getProducts().get(product));
			warehouse.getProducts().put(product, warehouse.getProducts().get(product) + SUPPLY_AVAILABILITY);
			System.out.println("The product current availability was increase with : "
			                   + SUPPLY_AVAILABILITY
			                   + " and is :"
			                   + warehouse.getProducts().get(product));

		} else {// TODO PRODUCT AVAILABILITY FINISH < 15
			System.out.println("The product "
			                   + product
			                   + "is not deficited current availability :"
			                   + warehouse.getProducts().get(product));

		}
	}
}
