package demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import storehouse.Employee;
import storehouse.Shop;
import storehouse.StoreHouse;
import storehouse.distributor.Distributor;
import storehouse.goods.Good;
import storehouse.supplier.QualitySupplier;
import storehouse.supplier.SpeedSupplier;
import storehouse.supplier.Supplier;
import tools.Tools;

public class Demo {

	public static void main(String[] args) {

		// 1.

		System.out.println(
		    "==========================================ADD STOREHOUSE/WORKERS/SUPPLIER/DISTRIBUTOR===============================================================");

		List<StoreHouse> storeHouses = new ArrayList<>();
		for (int i = 0; i < 1; i++) {
			StoreHouse storeHouse = new StoreHouse("STORE" + i + 1, "Sofa" + i + 1);
			System.out.println(storeHouse.getName() + ":");

			// Add Employees
			for (int j = 0; j < 4; j++) {
				Employee employee = new Employee("Ivo" + j + 1, 50 + j + 10, storeHouse);
				storeHouse.addEmployee(employee);
			}
			Tools.print(storeHouse.getEmployee());
			System.out.println();

			// Add Distributors
			for (int j = 0; j < 5; j++) {
				Distributor distributor = new Distributor("Ivan" + j + 1);
				storeHouse.addDistributor(distributor);
				distributor.setStoreHouse(storeHouse);
			}
			Tools.print(storeHouse.getDistributors());
			System.out.println();

			// Add Suppliers
			for (int j = 0; j < 5; j++) {
				Supplier supplier = null;
				int n = Tools.getRandomNumber(0, 2);
				switch (n) {
				case 1:
					supplier = new QualitySupplier("Pepi" + j + 1, storeHouse);

					break;
				case 2:
					supplier = new SpeedSupplier("Goro" + j + 1, storeHouse);

					break;

				default:
					supplier = null;
					break;
				}

				storeHouse.addSupplier(supplier);
				supplier.setStoreHouse(storeHouse);
			}
			Tools.print(storeHouse.getSuppliers());
			System.out.println();

			storeHouses.add(storeHouse);
		}

		// 2.

		System.out.println(
		    "==========================================ADD GOODS TO STOREHOUSE===============================================================");
		for (StoreHouse storeHouse : storeHouses) {
			System.out.println(storeHouse.getName());
			int randomGoodsCount = Tools.getRandomNumber(3, 5);

			for (int i = 0; i < randomGoodsCount; i++) {
				String randomName = "Goods" + Tools.getRandomNumber(1, 10);
				double randomPrice = Tools.getRandomDouble(10, 15);
				int randomQuantity = Tools.getRandomNumber(20, 50);
				storeHouse.addGoods(new Good(randomName, randomPrice), randomQuantity);
			}

			System.out.println();

			// Tools.print(storeHouse.getGoods());
		}

		// 3.
		System.out.println(
		    "==========================================ADD SHOPS TO DISTRIBUTORS ===============================================================");
		for (StoreHouse storeHouse : storeHouses) {
			for (Distributor distributor : storeHouse.getDistributors()) {
				int randomShopsCount = Tools.getRandomNumber(1, 3);

				for (int i = 0; i < randomShopsCount; i++) {
					distributor.addShop(new Shop("SHOP" + Tools.getRandomNumber(10, 20), "Sofa" + i + 20));
				}

				System.out.println();
			}
		}

		// 4.
		System.out.println(
		    "==========================================LOAD SHOPS==============================================================");

		int randomLoadCounter = Tools.getRandomNumber(1, 2);
		for (int i = 0; i < randomLoadCounter; i++) {

			for (StoreHouse storeHouse : storeHouses) {
				Map<Good, Integer> goodsToLoad = new HashMap<>();

				// get random goods with random quantity from the store
				int randomGoodsCounter = Tools.getRandomNumber(5, 10);
				for (int j = 0; j < randomGoodsCounter; j++) {
					Good randomGood = Tools.getRandom(storeHouse.getGoods().keySet());

					Integer randomQuantity = Tools.getRandomNumber(10, 20);
					goodsToLoad.put(randomGood, randomQuantity);
				}

				System.out.println("\nLoading storeHouse " + storeHouse.getName() + " with " + goodsToLoad.size() + " goods:");
				storeHouse.loadingGoods(goodsToLoad);
			}
		}

		System.out.println(
		    "==========================================DELIVER GOODS==============================================================");
		int randomDeliverCounter = Tools.getRandomNumber(1, 3);
		for (int i = 0; i < randomDeliverCounter; i++) {

			for (StoreHouse storeHouse : storeHouses) {
				Map<Good, Integer> goodsToDeliver = new HashMap<>();

				// get random goods with random quantity from the store
				int randomGoodsCounter = Tools.getRandomNumber(5, 10);
				for (int j = 0; j < randomGoodsCounter; j++) {
					Good randomGood = Tools.getRandom(storeHouse.getGoods().keySet());

					Integer randomQuantity = Tools.getRandomNumber(10, 20);
					goodsToDeliver.put(randomGood, randomQuantity);
				}

				System.out.println("\nDelivering from storeHouse "
				                   + storeHouse.getName()
				                   + " with "
				                   + goodsToDeliver.size()
				                   + " goods:");
				Distributor distributor = Tools.getRandom(storeHouse.getDistributors());
				Shop randomShop = Tools.getRandom(distributor.getShops());
				storeHouse.deliveryGoods(goodsToDeliver, randomShop);
			}
		}

		// 5.
		System.out.println(
		    "==========================================STATISTICS==============================================================");
		for (StoreHouse storeHouse : storeHouses) {
			System.out.println(storeHouse.getName() + ":");
			storeHouse.printBadEmployees();
			System.out.println();
			storeHouse.printBalance();
			System.out.println();
			storeHouse.printBuyStatistic();
			System.out.println();
			storeHouse.printDeficitGoods();
			System.out.println();
			storeHouse.printSalesStatistic();
			System.out.println();
			storeHouse.printTheBestSoldGoods();
			System.out.println(
			    "========================================================================================================");
		}
	}

}
