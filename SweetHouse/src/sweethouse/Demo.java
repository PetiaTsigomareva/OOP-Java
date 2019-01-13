package sweethouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeSet;

import sweethouse.cake.Cake;
import sweethouse.cake.ChildrenCake;
import sweethouse.cake.SpecialCake;
import sweethouse.cake.StandartCake;
import sweethouse.cake.WeddingCake;
import sweethouse.customer.CorporationCustomer;
import sweethouse.customer.Customer;
import sweethouse.customer.PrivateCustomer;
import tools.Tools;

public class Demo {

	public static void main(String[] args) {
		// Map<Cake, Integer> map = new HashMap<>();
		//
		// ChildrenCake childenCake1 = new ChildrenCake("Whinie the Pooh", "BLA-BLA",
		// 12, 12, ChildrenCake.Kind.BIRTH_DAY,
		// "Ivan");
		// Cake childenCake2 = new ChildrenCake("Spone Bob", "BLA-BLA", 12, 12,
		// ChildrenCake.Kind.BIRTH_DAY, "Ivan");
		// Cake childenCake3 = new ChildrenCake("Whinie the Pooh", "BLA-BLA", 12, 12,
		// ChildrenCake.Kind.KRYSHTENE, "Ivan");
		//
		// map.put(childenCake1, 2);
		// map.put(childenCake2, 1);
		// map.put(childenCake3, 4);
		//
		// Tools.printMap(map);

		// double REBATE = Tools.getRandomNumber(5, 15) / 100D;
		// System.out.println(REBATE);

		// 1.
		SweetHouse sweetHouse = new SweetHouse("SWEET TALANTS", Tools.getRandomPhoneNumber(), "Sofiq, Infinity Tows52");
		for (int i = 0; i < 5; i++) {
			sweetHouse.addSupplier(new Supplier("Gosho" + ( i + 1 ), Tools.getRandomPhoneNumber(), sweetHouse));
		}

		System.out.println(Tools.printFrame("1." + sweetHouse.getName(), 100));
		System.out.println(Tools.printFrame("1." + Supplier.class.getSimpleName(), 100));
		for (Supplier suppliers : sweetHouse.getSuppliers()) {
			System.out.println(suppliers.toString());
		}

		// 2.
		System.out.println(Tools.printFrame("2. Add SweetHouseCatalog", 100));

		for (int i = 0; i < 30; i++) {

			int randomCakeNumber = Tools.getRandomNumber(1, 4);
			int cakePrice = Tools.getRandomNumber(35, 100);
			int cakePieces = Tools.getRandomNumber(12, 30);

			Cake cake = null;

			switch (randomCakeNumber) {
			case 1:
				cake = new StandartCake("Standartka", "Ala bala", cakePrice, cakePieces, StandartCake.Kind.getRandomKind(),
				    new Random().nextBoolean());
				break;

			case 2:
				cake = new SpecialCake("Specialka", "Ala bala", cakePrice, cakePieces, SpecialCake.Kind.getRandomKind(),
				    " ItTalantsEvent" + Tools.getRandomNumber(1, 10));
				break;
			case 3:
				cake = new WeddingCake("Wedinka", "Ala bala", cakePrice, cakePieces, WeddingCake.Kind.getRandomKind(),
				    Tools.getRandomNumber(1, 5));
				break;
			case 4:
				cake = new ChildrenCake("Childrenka", "Ala bala",
				    cakePrice, cakePieces, ChildrenCake.Kind.getRandomKind(), "Lili" + Tools.getRandomNumber(1, 20));
				break;
			default:
				System.out.println("The cake was not be created");
				break;
			}
			sweetHouse.addCake(cake, Tools.getRandomNumber(1, 2));

		}

		System.out.println(Tools.printFrame("2.SweetHouseCatalog", 100));
		Tools.printMap(sweetHouse.getCatalog());

		// 3.
		System.out.println(Tools.printFrame("3." + Customer.class.getSimpleName(), 100));

		List<Customer> customers = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			double rebate = Tools.getRandomNumber(5, 15) / 100D;
			CorporationCustomer client = new CorporationCustomer("Ivan" + ( i + 1 ),
			    Tools.getRandomPhoneNumber(), "Address50", sweetHouse, rebate);
			customers.add(client);
			System.out.println(client.toString());

		}

		for (int i = 0; i < 5; i++) {
			PrivateCustomer customer = new PrivateCustomer("Ani" + ( i + 2 ), Tools.getRandomPhoneNumber(), "Address100",
			    sweetHouse,
			    Tools.getRandomNumber(1, 4), Tools.getRandomNumber(10, 30));
			customers.add(customer);
			System.out.println(customer.toString());
		}

		// 4. 5.
		System.out.println(Tools.printFrame("4.Create orders", 100));

		for (Customer customer : customers) {
			System.out.println(Tools.printFrame("Catalog", 100));
			Tools.printMap(sweetHouse.getCatalog());
			System.out.println();

			Map<Cake, Integer> randomCakes = new HashMap<>();
			for (int i = 0; i < customer.getOrderCakesCount(); i++) {
				Cake randomCake = Tools.getRandom(sweetHouse.getCatalog().keySet());

				Integer requested = randomCakes.get(randomCake);
				if (requested == null) {
					requested = 1;
				} else {
					requested = requested + 1;
				}
				randomCakes.put(randomCake, requested);
			}

			sweetHouse.createOrder(randomCakes, customer);

			System.out.println(Tools.printFrame("Catalog", 100));
			Tools.printMap(sweetHouse.getCatalog());

			// System.out.println(Tools.printFrame("Sold Catalog", 100));
			// Tools.printMap(sweetHouse.getSold());

			// 6.
			System.out
			    .println(Tools.printFrame("Cashdesk: " + String.format("%.2f", sweetHouse.getKeshDesk()) + " BGN", 60));
		}

		// 7.
		System.out.println(Tools.printFrame("7.Sorted Suppliers by tips", 60));
		TreeSet<Supplier> sortedSuppliersByTips = new TreeSet<>(
		    (Supplier s1, Supplier s2) -> Double.compare(s2.getTips(), s1.getTips()));
		sortedSuppliersByTips.addAll(sweetHouse.getSuppliers());
		for (Supplier sweetHouseSupplier : sortedSuppliersByTips) {
			System.out.println(sweetHouseSupplier.toString());
		}
		// 8.
		System.out.println(Tools.printFrame("8. Cakes best sellers", 60));
		Map<String, Integer> theBestKindCakes = new HashMap<>();
		String kind;
		Integer quantity;
		for (Cake cake : sweetHouse.getSold().keySet()) {
			kind = cake.getClass().getSimpleName();
			quantity = sweetHouse.getSold().get(cake);

			Integer bestSellerQuantity = theBestKindCakes.get(kind);
			if (bestSellerQuantity == null) {
				bestSellerQuantity = 0;
			}

			theBestKindCakes.put(kind, bestSellerQuantity + quantity);
		}

		Tools.printMap(theBestKindCakes);

		List<Map.Entry<String, Integer>> sortedKinds = Tools.convertMapToSortedByCriteriaList(theBestKindCakes,
		    (Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) -> ( o2.getValue() - o1.getValue() ));

		System.out.println(sortedKinds);
		Map.Entry<String, Integer> bestEntry = sortedKinds.get(0);
		List<Map.Entry<String, Integer>> bestEntryList = new ArrayList<>();
		for (int i = 0; i < sortedKinds.size(); i++) {
			if (sortedKinds.get(i).getValue() >= bestEntry.getValue()) {
				bestEntry = sortedKinds.get(i);
				bestEntryList.add(bestEntry);

			}
		}
		for (Entry<String, Integer> entry : bestEntryList) {

			System.out.println(entry);
		}
		// 9.
		System.out.println(Tools.printFrame("9. The best supplier", 60));
		Map<String, Integer> theBestSupplier = new HashMap<>();
		String name;
		Integer orders;

		for (Supplier supplier : sweetHouse.getSuppliers()) {
			name = supplier.getName();
			orders = supplier.getOrders().size();
			Integer currentOrder = theBestSupplier.get(name);
			if (currentOrder == null) {
				currentOrder = 0;
			}
			theBestSupplier.put(name, currentOrder + orders);

		}
		System.out.println("Supplier-Order Map");
		Tools.printMap(theBestSupplier);

		List<Map.Entry<String, Integer>> sortedSupplierByOrders = Tools.convertMapToSortedByCriteriaList(theBestSupplier,
		    (Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) -> o2.getValue() - o1.getValue());

		System.out.println("Sorted Supplier-Order Map \n" + sortedSupplierByOrders);

		Map.Entry<String, Integer> bestEntry1 = sortedSupplierByOrders.get(0);
		List<Map.Entry<String, Integer>> bestSupplyerList = new ArrayList<>();

		for (int i = 0; i < sortedSupplierByOrders.size(); i++) {
			if (sortedSupplierByOrders.get(i).getValue() >= bestEntry1.getValue()) {
				bestEntry1 = sortedSupplierByOrders.get(i);
				bestSupplyerList.add(bestEntry1);

			}
		}
		System.out.println("List with the best suppliers\n");
		for (Entry<String, Integer> entry1 : bestSupplyerList) {

			System.out.println(entry1);
		}
		// 10.
		System.out.println(Tools.printFrame("10. The Customer payed max price for order ", 60));
		Map<String, Double> customerMaxPayment = new HashMap<>();
		String customerName;
		Double price;

		for (Order order : sweetHouse.getOrders()) {
			customerName = order.getCustomer().getName();
			price = order.getPrice();
			Double currentPrice = customerMaxPayment.get(customerName);
			if (currentPrice == null) {
				currentPrice = 0.0;
			}
			customerMaxPayment.put(customerName, currentPrice + price);

		}

		System.out.println("Customer-Payment Map");
		Tools.printMap(customerMaxPayment);

		List<Map.Entry<String, Double>> sortedCustomerPayments = Tools.convertMapToSortedByCriteriaList(customerMaxPayment,
		    (Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) -> Double.compare(o2.getValue(), o1.getValue()));

		System.out.println("Sorted Customer-Payment Map \n" + sortedCustomerPayments);

		List<Map.Entry<String, Double>> bestCustomerPayments = new ArrayList<>();
		Map.Entry<String, Double> bestEntry2 = sortedCustomerPayments.get(0);
		for (int i = 0; i < sortedCustomerPayments.size(); i++) {
			if (sortedCustomerPayments.get(i).getValue() >= bestEntry2.getValue()) {
				bestEntry2 = sortedCustomerPayments.get(i);
				bestCustomerPayments.add(bestEntry2);

			}
		}

		System.out.println("List with the best payment customers!");
		for (Entry<String, Double> entry : bestCustomerPayments) {
			System.out.println(entry);

		}
		// System.out.println(Tools.getRandomDate());

	}

}
