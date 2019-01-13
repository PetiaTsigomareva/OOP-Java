package storehouse;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import storehouse.goods.Good;
import tools.Tools;

public class Employee {
	private String name;
	private double salary;
	private boolean isExperienced;
	private StoreHouse storeHouse;

	private Map<Good, Integer> acceptedGoods;
	private Map<Good, Integer> issuedGoods;

	public Employee(String name, double money, StoreHouse storeHouse) {
		this.name = name;
		this.salary = money;
		this.storeHouse = storeHouse;

		this.acceptedGoods = new HashMap<>();
		this.issuedGoods = new HashMap<>();

		isUnexperienced();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public boolean isUnexperienced() {

		if (new Random().nextBoolean()) {
			isExperienced = true;
		}
		return isExperienced;
	}

	public StoreHouse getStoreHouse() {
		return storeHouse;
	}

	public int getAcceptedGoodsCount() {
		return Tools.getMapValueCount(acceptedGoods);
	}

	public void addAcceptedGoods(Map<Good, Integer> sourceMap) {
		Tools.addMapToMap(this.acceptedGoods, sourceMap);
	}

	public int getIssuedGoodsCount() {
		return Tools.getMapValueCount(issuedGoods);
	}

	public void addIssuedGoods(Map<Good, Integer> sourceMap) {
		Tools.addMapToMap(this.issuedGoods, sourceMap);
	}

	public void acceptGoodsToStore(Map<Good, Integer> map) {
		skipedGoods(map);

		for (Good good : map.keySet()) {
			Integer currentAvailability = map.get(good);
			storeHouse.addGoods(good, currentAvailability);
		}

		addAcceptedGoods(map);
	}

	public void issuanceGoodsFromStore(Map<Good, Integer> map) {
		skipedGoods(map);

		for (Good good : map.keySet()) {
			Integer currentAvailability = map.get(good);
			storeHouse.sellGoods(good, currentAvailability);
		}

		addIssuedGoods(map);
	}

	private void skipedGoods(Map<Good, Integer> map) {
		int n = Tools.getRandomNumber(1, 100);
		if (!isExperienced && n <= 50) {
			Good randomGood = Tools.getRandom(map.keySet());
			map.remove(randomGood);
			System.out.println(this.toString() + " skipped " + randomGood);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
		long temp;
		temp = Double.doubleToLongBits(salary);
		result = prime * result + (int) ( temp ^ ( temp >>> 32 ) );
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee "
		       + name
		       + " ("
		       + ( isExperienced ? "expirienced" : "unexpirienced" )
		       + ")";
	}

}
