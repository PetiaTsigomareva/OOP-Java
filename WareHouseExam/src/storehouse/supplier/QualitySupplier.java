package storehouse.supplier;

import java.util.Map;

import storehouse.StoreHouse;
import storehouse.goods.Good;

public class QualitySupplier extends Supplier {
	private static final double OVERCHARGE = 0.5;

	public QualitySupplier(String name, StoreHouse storeHouse) {
		super(name, Type.QUALITY_SUPPLIE, storeHouse);
	}

	@Override
	protected void processGoods(Map<Good, Integer> map) {
	}

	@Override
	public double getAdditionalCharge() {
		return OVERCHARGE;
	}
}
