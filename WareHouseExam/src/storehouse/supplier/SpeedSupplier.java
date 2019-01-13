package storehouse.supplier;

import java.util.Map;

import storehouse.StoreHouse;
import storehouse.goods.Good;

public class SpeedSupplier extends Supplier {
	private static final double OVERCHARGE = 0.2;

	public SpeedSupplier(String name, StoreHouse storeHouse) {
		super(name, Type.SPEED_SUPPLIER, storeHouse);
	}

	// remove 10% from buyed good for store
	@Override
	protected void processGoods(Map<Good, Integer> map) {
		int n = map.size() / 10;
		for (int i = 0; i < n; i++) {
			for (Good good : map.keySet()) {
				map.remove(good);
			}
		}
	}

	@Override
	public double getAdditionalCharge() {
		return OVERCHARGE;
	}
}