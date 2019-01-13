package storehouse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import storehouse.distributor.Distributor;
import storehouse.goods.Good;
import storehouse.supplier.Supplier;
import tools.Tools;

public class StoreHouse extends Info {
	private static final double INIT_CASH = 10000;
	private String address;
	private Set<Supplier> suppliers;
	private Set<Distributor> distributors;
	private Set<Employee> employees;
	private Map<Good, Integer> goods;
	private Map<Good, Integer> soldGoods;

	private double totalSpendMoney;
	private double totalRecieveMoney;

	public StoreHouse(String name, String address) {
		super(name, INIT_CASH);
		this.address = address;
		this.suppliers = new HashSet<>();
		this.distributors = new HashSet<>();
		this.employees = new HashSet<>();
		this.goods = new HashMap<>();
		this.soldGoods = new HashMap<>();
	}

	Comparator<Map.Entry<Good, Integer>> goodsBydeficitQuantityComarator = new Comparator<Map.Entry<Good, Integer>>() {

		@Override
		public int compare(Entry<Good, Integer> o1, Entry<Good, Integer> o2) {
			if (( o1.getValue() < 10 ) && ( o2.getValue() < 10 )) {
				return o2.getValue() - o1.getValue();

			}
			return 0;
		}
	};

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Supplier> getSuppliers() {
		return suppliers;
	}

	public void addSupplier(Supplier supplier) {
		this.suppliers.add(supplier);
	}

	public Set<Distributor> getDistributors() {
		return distributors;
	}

	public void addDistributor(Distributor distributor) {
		this.distributors.add(distributor);
	}

	public Set<Employee> getEmployee() {
		return employees;
	}

	public void addEmployee(Employee employee) {
		this.employees.add(employee);
	}

	public Map<Good, Integer> getGoods() {
		return goods;
	}

	public void setGoods(Map<Good, Integer> goods) {
		this.goods = goods;
	}

	public Map<Good, Integer> getSoldGoods() {
		return soldGoods;
	}

	public void setSoldGoods(Map<Good, Integer> soldGoods) {
		this.soldGoods = soldGoods;
	}

	public double getTotalSpendMoney() {
		return totalSpendMoney;
	}

	public void spendMoney(double spendMoney) {
		this.totalSpendMoney += spendMoney;
		decreaseMoney(spendMoney);
		System.out.println("StoreHouse " + getName() + " spent " + String.format("%.2f", spendMoney) + " BGN");
	}

	public double getTotalRecieveMoney() {
		return totalRecieveMoney;
	}

	public void recieveMoney(double recievedMoney) {
		this.totalRecieveMoney += recievedMoney;
		addMoney(recievedMoney);
		System.out.println("StoreHouse " + getName() + " received " + String.format("%.2f", recievedMoney) + " BGN");
	}

	@Override
	public String toString() {
		return "StoreHouse:" + getName();
	}

	public void addGoods(Good good, int availability) {
		Integer currentAvailability = this.goods.get(good);
		if (currentAvailability == null) {
			this.goods.put(good, availability);
		} else {
			this.goods.put(good, currentAvailability + availability);
		}
		System.out.println("In store " + getName() + " is added: " + good + " : " + availability);
	}

	public void sellGoods(Good good, int quantity) {
		Integer currentAvailableQuantity = this.goods.get(good);
		// Map<Goods, Integer> deficitGoods = null;
		if (currentAvailableQuantity != null && quantity <= currentAvailableQuantity) {
			this.goods.put(good, currentAvailableQuantity - quantity);
			Integer currentSoldQuantity = this.soldGoods.get(good);
			if (currentSoldQuantity == null) {
				this.soldGoods.put(good, quantity);
			} else {
				this.soldGoods.put(good, currentSoldQuantity + quantity);
			}

			System.out.println("From store " + getName() + " is sold: " + good + " : " + quantity);

		} else {
			// deficitgood.put(goods, currentAvailableQuantity);
			System.out.println("Can not be sold - no enogh availability " + currentAvailableQuantity);
			// loadingGoods(deficitGoods);
		}
	}

	public void loadingGoods(Map<Good, Integer> loadGoodsMap) {
		// chose random supplier
		Supplier supplier = Tools.getRandom(suppliers);

		// store house pay from loaded goods
		double currentStoreMoney = this.getMoney();
		double allGoodsPrice = 0;

		for (Good good : loadGoodsMap.keySet()) {
			Integer quantity = loadGoodsMap.get(good);
			allGoodsPrice += good.getPrice() * quantity;
		}

		// supplier buy list of goods
		double supplierCommision = supplier.deliverGoods(loadGoodsMap);

		if (currentStoreMoney >= allGoodsPrice + supplierCommision) {
			spendMoney(allGoodsPrice + supplierCommision);

			// Random worker to arrange goods
			Employee employee = Tools.getRandom(employees);
			employee.acceptGoodsToStore(loadGoodsMap);
		} else {
			System.out.println("In the cash desk there are not enogh money for buyed goods!");
		}

	}

	public void deliveryGoods(Map<Good, Integer> delivaryGoodsMap, Shop shop) {
		// get price of the all goods
		double allGoodsPrice = 0;
		double distributorCommision;

		for (Good good : delivaryGoodsMap.keySet()) {
			Integer quantity = delivaryGoodsMap.get(good);
			allGoodsPrice += good.getSellPrice() * quantity;
		}

		// chose random distributor to deliver good to shop list
		Distributor distributor = Tools.getRandom(shop.getDistributors());
		distributorCommision = distributor.loadShop(delivaryGoodsMap, shop);

		// chose random worker to remove good from store map
		Employee employee = Tools.getRandom(employees);
		employee.issuanceGoodsFromStore(delivaryGoodsMap);

		// get the net price (without distributors commission)
		recieveMoney(allGoodsPrice - distributorCommision);
	}

	public void printTheBestSoldGoods() {
		System.out.println("THE BEST SOLD GOODS");
		// sort sold map by quantity
		List<Map.Entry<Good, Integer>> sortSoldMapByQuantity = Tools.convertMapToSortedByCriteriaList(soldGoods,
		    (Map.Entry<Good, Integer> e1, Map.Entry<Good, Integer> e2) -> e2.getValue()
		                                                                  - e1.getValue());

		// print first 5 goods
		for (int i = 0; i < 5 && i < sortSoldMapByQuantity.size(); i++) {
			Good good = sortSoldMapByQuantity.get(i).getKey();
			Integer quantity = sortSoldMapByQuantity.get(i).getValue();
			System.out
			    .println(good.getName() + " : " + quantity);
		}
	}

	public void printBadEmployees() {
		// sort worker set by remove or arrange good list
		List<Employee> sortedList = Tools.convertSetToSortedByCriteriaList(employees,
		    ( (Employee e1, Employee e2) -> ( e1.getAcceptedGoodsCount() + e1.getIssuedGoodsCount() )
		                                    - ( e2.getAcceptedGoodsCount() + e2.getIssuedGoodsCount() ) ));
		// print first 3 bad employee
		System.out.println("BAD EMPLOYEES");
		for (int i = 0; i < 3; i++) {
			System.out.println(sortedList.get(i).getName()
			                   + " - "
			                   + ( sortedList.get(i).getAcceptedGoodsCount() + sortedList.get(i).getIssuedGoodsCount() ));

		}
	}

	public void printDeficitGoods() {
		System.out.println("DEFICIT GOODS");
		// sort sold map by quantity
		List<Entry<Good, Integer>> deficitGoods = new ArrayList<>();

		for (Entry<Good, Integer> good : this.goods.entrySet()) {
			if (good.getValue() < 10) {
				deficitGoods.add(good);
			}
		}

		// print first 5 goods
		for (Entry<Good, Integer> entry : deficitGoods) {
			System.out.println(entry.getKey().getName() + " : " + entry.getValue());
		}
	}

	public void printSalesStatistic() {
		System.out.println("SALES STATISTIC");
		for (Distributor distributor : distributors) {
			System.out.println("Distributor "
			                   + distributor.getName()
			                   + " has total incomes "
			                   + String.format("%.2f", distributor.getMoney())
			                   + " BGN");
		}
	}

	public void printBuyStatistic() {
		System.out.println("BUY STATISTIC");
		for (Supplier supplier : suppliers) {
			System.out.println("Supplier "
			                   + supplier.getType().toString()
			                   + " "
			                   + supplier.getName()
			                   + " has total incomes "
			                   + String.format("%.2f", supplier.getMoney())
			                   + " BGN");
		}

	}

	public void printBalance() {
		System.out.println("BALANCE STATISTIC");
		System.out.println("Total spent money for loading good : "
		                   + String.format("%.2f", totalSpendMoney)
		                   + " BGN\nTotal received money from selling goods: "
		                   + String.format("%.2f", totalRecieveMoney)
		                   + " BGN\nCash desk availability: "
		                   + String.format("%.2f", getMoney())
		                   + " BGN");

	}

}
