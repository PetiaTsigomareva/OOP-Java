package storehouse;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import storehouse.distributor.Distributor;
import storehouse.goods.Good;

public class Shop extends Info {
	private static final double INIT_CASH = 1000;

	private String address;
	private Set<Distributor> distributors;
	private Map<Good, Integer> goods;

	public Shop(String name, String address) {
		super(name, INIT_CASH);

		this.address = address;
		this.distributors = new HashSet<>();
		this.goods = new HashMap<>();
	}

	public Set<Distributor> getDistributors() {
		return distributors;
	}

	public void addDistributor(Distributor distributor) {
		this.distributors.add(distributor);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Map<Good, Integer> getGoods() {
		return goods;
	}

	public void addGoods(Good good, int availability) {
		Integer currentAvailability = this.goods.get(good);
		if (currentAvailability == null) {
			this.goods.put(good, availability);
		} else {
			this.goods.put(good, currentAvailability + availability);
		}

	}

	@Override
	public String toString() {
		return getName();
	}

}
