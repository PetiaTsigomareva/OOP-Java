package storehouse.supplier;

import java.util.Map;

import storehouse.Info;
import storehouse.StoreHouse;
import storehouse.goods.Good;

public abstract class Supplier extends Info {

	public static enum Type {
		SPEED_SUPPLIER, QUALITY_SUPPLIE
	};

	private Type type;
	private StoreHouse storeHouse;

	public Supplier(String name, Type type, StoreHouse storeHouse) {
		super(name, 0);
		this.type = type;
		this.storeHouse = storeHouse;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public StoreHouse getStoreHouse() {
		return storeHouse;
	}

	public void setStoreHouse(StoreHouse storeHouse) {
		this.storeHouse = storeHouse;
	}

	public double deliverGoods(Map<Good, Integer> orderFromStoreGoods) {
		double commision = 0;

		processGoods(orderFromStoreGoods);

		for (Good good : orderFromStoreGoods.keySet()) {
			Integer quantity = orderFromStoreGoods.get(good);

			if (quantity != null && quantity > 0) {

				// add commission to supplier
				commision += good.getPrice() * quantity * getAdditionalCharge();

				System.out.println("Supplier: "
				                   + getName()
				                   + "("
				                   + getType()
				                   + ")"
				                   + " delivered to "
				                   + getStoreHouse().getName()
				                   + " "
				                   + good.toString()
				                   + " : "
				                   + quantity
				                   + " pieces");
			} else {
				System.out.println("The good availablility is "
				                   + quantity
				                   + " and not be buyed. Please check order store list ");
			}
		}

		System.out.println("Supplier: "
		                   + getName()
		                   + "("
		                   + getType()
		                   + ") received commision "
		                   + String.format("%.2f", commision));

		this.addMoney(commision);
		return commision;
	}

	public abstract double getAdditionalCharge();

	protected abstract void processGoods(Map<Good, Integer> map);

	@Override
	public String toString() {
		return "Supplier: "
		       + getName()
		       + "("
		       + getType()
		       + ") = "
		       + String.format("%.2f", getMoney())
		       + " BGN  worked in "
		       + storeHouse;
	}

}
