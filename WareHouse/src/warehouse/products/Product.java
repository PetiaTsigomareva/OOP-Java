package warehouse.products;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import tools.Tools;

public class Product {
	public enum Name {

		BANANA, ORANGE, APPLE, POTATO, EGGPLANT, CUCUMBER, PORK, BEEF, CHICKEN
	}

	public enum Type {
		FRUITS, VEGETABLES, MEATS
	}

	private static Comparator<Map.Entry<Name, Type>> productTypeComparator = new Comparator<Map.Entry<Name, Type>>() {

		@Override
		public int compare(Entry<Name, Type> o1, Entry<Name, Type> o2) {

			return o1.getValue().toString().compareTo(o2.getValue().toString());
		}
	};

	private static Map<Name, Type> productTypesMap = new HashMap<>();

	static {
		productTypesMap.put(Name.APPLE, Type.FRUITS);
		productTypesMap.put(Name.BANANA, Type.FRUITS);
		productTypesMap.put(Name.ORANGE, Type.FRUITS);

		productTypesMap.put(Name.POTATO, Type.VEGETABLES);
		productTypesMap.put(Name.EGGPLANT, Type.VEGETABLES);
		productTypesMap.put(Name.CUCUMBER, Type.VEGETABLES);

		productTypesMap.put(Name.PORK, Type.MEATS);
		productTypesMap.put(Name.BEEF, Type.MEATS);
		productTypesMap.put(Name.CHICKEN, Type.MEATS);

	}

	private Name name;
	private Type type;

	public Product(Name name) {
		super();
		if (productTypesMap.keySet().contains(name)) {
			this.name = name;
		} else {
			throw new RuntimeException("Product " + name + "is not among the known: " + productTypesMap);
		}

		this.type = productTypesMap.get(name);
	}

	/**
	 * @return the name
	 */
	public Name getName() {
		return name;
	}

	/**
	 * @param name
	 *          the name to set
	 */
	public void setName(Name name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @param type
	 *          the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * @return the productTypesMap
	 */
	public static Map<Name, Type> getProductTypesMap() {
		return productTypesMap;
	}

	public static Set<Name> getAllProductsName() {
		return productTypesMap.keySet();

	}

	/**
	 * @param productTypesMap
	 *          the productTypesMap to set
	 */
	public static void setProductTypesMap(Map<Name, Type> productTypesMap) {
		Product.productTypesMap = productTypesMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
		result = prime * result + ( ( type == null ) ? 0 : type.hashCode() );
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Product other = (Product) obj;
		if (name != other.name) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Tools.format(name.toString(), 18) + Tools.format(type.toString(), 12);
	}

	public static void printProductNameToType() {
		List<Map.Entry<Name, Type>> products = Tools.convertMapToSortedByCriteriaList(productTypesMap,
		    productTypeComparator);
		for (Entry<Name, Type> entry : products) {
			System.out.println(entry.getValue() + ":" + entry.getKey());

		}

	}

}
