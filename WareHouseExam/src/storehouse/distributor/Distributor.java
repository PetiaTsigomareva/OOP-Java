package storehouse.distributor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import storehouse.Info;
import storehouse.Shop;
import storehouse.StoreHouse;
import storehouse.goods.Good;

public class Distributor extends Info {
	private static final double COMMISSION = 0.2;

	private StoreHouse storeHouse;
	private List<Shop> shops;

	public Distributor(String name) {
		super(name, 0);

		this.shops = new ArrayList<>();
	}

	public StoreHouse getStoreHouse() {
		return storeHouse;
	}

	public void setStoreHouse(StoreHouse storeHouse) {
		this.storeHouse = storeHouse;
	}

	public List<Shop> getShops() {
		return shops;
	}

	public void addShop(Shop shop) {
		this.shops.add(shop);
		shop.addDistributor(this);

		System.out.println("Added shop " + shop + " to distributor " + getName());
	}

	public double loadShop(Map<Good, Integer> loadGoods, Shop shop) {
		double commision = 0;

		System.out.println("==================Loading " + shop.getName() + "========================");
		for (Good good : loadGoods.keySet()) {
			int quantity = loadGoods.get(good);

			System.out.println("Distributor "
			                   + this.getName()
			                   + " delivered to "
			                   + shop.getName()
			                   + " "
			                   + good.toString()
			                   + " : "
			                   + quantity
			                   + " pieces");
			shop.addGoods(good, quantity);

			commision += good.getSellPrice() * quantity * COMMISSION;
		}

		System.out.println("Distributor: "
		                   + getName()
		                   + " received commision "
		                   + String.format("%.2f", commision));

		this.addMoney(commision);
		return commision;
	}

	public double getCommission() {
		return COMMISSION;
	}

	@Override
	public String toString() {
		return "Distributor :" + getName() + ", " + String.format("%.2f", getMoney()) + " distributed from " + storeHouse;
	}

}
