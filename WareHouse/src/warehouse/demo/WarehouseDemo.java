package warehouse.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tools.Tools;
import warehouse.clients.Client;
import warehouse.products.Product;
import warehouse.stores.Shop;
import warehouse.stores.Warehouse;
import warehouse.stores.WarehouseSupplyer;

public class WarehouseDemo {
	public static final int SHOP_COUNT = 3;
	public static final int CLIENT_COUNT = 9;
	public static final int COUTER = 10;

	public static void main(String[] args) {

		Warehouse warehouse = new Warehouse("The Best Goots");
		System.out.println("1.===================Wellcom to " + warehouse.getName() + " Warehouse========================");
		Map<Product, Integer> warehouseProducts = warehouse.getProducts();
		Tools.printMap(warehouseProducts);

		System.out.println("============================================");

		WarehouseSupplyer supplyer = new WarehouseSupplyer(warehouse);
		warehouse.setSupplyer(supplyer);

		System.out.println("2.============================================");
		List<Shop> shops = new ArrayList<>();
		for (int i = 0; i < SHOP_COUNT; i++) {
			shops.add(new Shop("Shop" + ( i + 1 ), warehouse));
			Map<Product, Integer> shopProducts = shops.get(i).getProducts();
			System.out.println(shops.get(i).getName());
			Tools.printMap(shopProducts);
			System.out.println("============================================");

		}
		System.out.println("3.============================================");
		List<Client> clients = new ArrayList<>();
		for (int i = 0; i < CLIENT_COUNT; i++) {
			Client client = new Client("Lili" + ( i + 1 ));
			shops.get(i % 3).addClients(client);
			client.setShop(shops.get(i % 3));
			clients.add(client);
		}
		for (int i = 0; i < SHOP_COUNT; i++) {
			System.out.println(shops.get(i).getName() + shops.get(i).getClients().toString());
		}

		System.out.println("4.============================================");
		for (int i = 0; i < COUTER; i++) {
			Client randomClient = clients.get(Tools.getRandomNumber(0, clients.size() - 1));
			Shop shop = randomClient.getShop();
			Product randomProduct = Tools.getRandom(shop.getProducts().keySet());
			int randomQuantity = Tools.getRandomNumber(1, 4);
			System.out.println("Client: " + randomClient.toString());
			randomClient.buyProducts(randomProduct, randomQuantity);
			System.out.println("From " + shop.getName());
			System.out.println("============================================");
			Tools.printMap(shop.getProducts());
			System.out.println("============================================");

		}

	}
}