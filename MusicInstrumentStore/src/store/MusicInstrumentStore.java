package store;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import store.instruments.MusicInstrument;
import store.instruments.MusicInstrument.Name;
import store.instruments.MusicInstrument.Type;
import tools.Tools;

public class MusicInstrumentStore {
	private String name;
	private Map<MusicInstrument, Integer> catalog;
	private Map<MusicInstrument, Integer> sold;
	private double initialMoney;

	public MusicInstrumentStore(String name, double initialMoney) {
		super();

		this.name = name;
		this.catalog = new HashMap<>();
		this.sold = new HashMap<>();
		this.initialMoney = initialMoney;
	}

	public static Comparator<Map.Entry<MusicInstrument, Integer>> soldByQuantityComparator = new Comparator<Map.Entry<MusicInstrument, Integer>>() {

		@Override
		public int compare(Entry<MusicInstrument, Integer> o1, Entry<MusicInstrument, Integer> o2) {
			int result;

			result = o2.getValue().compareTo(o1.getValue());

			if (result == 0) {
				result = o2.getKey().getName().toString().compareTo(o1.getKey().getName().toString());
			}
			if (result == 0) {
				result = o2.getKey().getType().toString().compareTo(o1.getKey().getType().toString());
			}
			if (result == 0) {
				result = Double.compare(o2.getKey().getPrice(), o1.getKey().getPrice());
			}
			return result;
		}
	};
	public static Comparator<Map.Entry<Type, Integer>> soldByTypeComparator = new Comparator<Map.Entry<Type, Integer>>() {

		@Override
		public int compare(Entry<Type, Integer> o1, Entry<Type, Integer> o2) {
			int result;

			result = o2.getValue().compareTo(o1.getValue());

			if (result == 0) {
				result = o2.getKey().toString().compareTo(o1.getKey().toString());
			}

			return result;
		}
	};

	public static Comparator<Map.Entry<Type, Double>> soldByPriceComparator = new Comparator<Map.Entry<Type, Double>>() {

		@Override
		public int compare(Entry<Type, Double> o1, Entry<Type, Double> o2) {
			int result;

			result = Double.compare(o2.getValue(), o1.getValue());

			if (result == 0) {
				result = o2.getKey().toString().compareTo(o1.getKey().toString());
			}

			return result;
		}
	};

	public void addInstrument(MusicInstrument instrument, int availability) {
		Integer currentAvailability = catalog.get(instrument);

		if (currentAvailability == null) {
			catalog.put(instrument, availability);
		} else {
			catalog.put(instrument, currentAvailability + availability);
		}

		System.out.println("Added: " + instrument + " (" + availability + ")");
	}

	public void addInstruments(Map<MusicInstrument, Integer> newInstruments) {
		for (MusicInstrument instrument : newInstruments.keySet()) {
			addInstrument(instrument, newInstruments.get(instrument));
		}
	}

	public MusicInstrument getIntrument(Name name) {
		MusicInstrument result;
		MusicInstrument tmpKey = new MusicInstrument(name, 0);

		result = Tools.findKey(catalog, tmpKey);

		return result;
	}

	public void sellInstrument(Name name, int count) {
		MusicInstrument instrument = getIntrument(name);

		if (instrument != null) {
			// sell instrument if it is availability in store catalog.
			Integer availableInstrumentsCount = catalog.get(instrument);
			// System.out.println(availableInstrumentsCount);

			if (( availableInstrumentsCount != null ) && ( availableInstrumentsCount >= count )) {
				// up store availability
				catalog.put(instrument, availableInstrumentsCount - count);

				Integer soldInstrumentsCount = sold.get(instrument);
				if (soldInstrumentsCount != null) {
					sold.put(instrument, soldInstrumentsCount + count);
				} else {
					sold.put(instrument, count);
				}

				this.initialMoney += instrument.getPrice();

				System.out.println("Sold: "
				                   + instrument
				                   + " ("
				                   + count
				                   + ")");

			} else {
				System.out.println("Searched instrument "
				                   + name
				                   + " can not be sold - current available = "
				                   + availableInstrumentsCount
				                   + " are not enogh to match the requested "
				                   + count);
			}
		} else {
			System.out.println("Searched instrument " + name + " is not available in the store catalog");
		}
	}

	public void printStoreStatus(Comparator<MusicInstrument> comparator) {
		System.out.println("\n");
		System.out.println(Tools.printFrame("Store \"" + name + "\"", 70));
		System.out.println(Tools.printFrame("Cash-Desk: " + initialMoney + " BGN", 70));

		printStoreAvailableStatus(comparator);
		printStoreSoldStatus(comparator);
	}

	public void printStoreAvailableStatus(Comparator<MusicInstrument> comparator) {
		System.out.println(Tools.printFrame("Available", 70));
		printMap(catalog, comparator);
	}

	public void printStoreSoldStatus(Comparator<MusicInstrument> comparator) {
		System.out.println(Tools.printFrame("Sold", 70));
		printMap(sold, comparator);
	}

	private void printMap(Map<MusicInstrument, Integer> map, Comparator<MusicInstrument> comparator) {
		System.out.println(Tools.printFrame(getTableHeader(), 70));

		Map<MusicInstrument, Integer> sortedMap = Tools.convertMapToSortedByCriteriaMap(map, comparator);

		int i = 0;
		for (Map.Entry<MusicInstrument, Integer> e : sortedMap.entrySet()) {
			System.out.println(Tools.format("" + ++i, 2) + e.getKey() + Tools.format(e.getValue().toString(), 8));
		}
	}

	public void printStoreSoldByQuantity(Comparator<Map.Entry<MusicInstrument, Integer>> comparator) {
		System.out.println(Tools.printFrame("Sold By Quantity", 70));
		System.out.println(Tools.printFrame(getTableHeader(), 70));

		List<Map.Entry<MusicInstrument, Integer>> sortedList = Tools.convertMapToSortedByCriteriaList(sold, comparator);

		int i = 0;
		for (Map.Entry<MusicInstrument, Integer> e : sortedList) {
			System.out.println(Tools.format("" + ++i, 2) + e.getKey() + Tools.format(e.getValue().toString(), 8));
		}
	}

	public void printBestSoldInstruments() {
		System.out.println(Tools.printFrame("Best Sold Instruments", 70));
		String tableHeader = getTableHeader();

		System.out.println(Tools.printFrame(tableHeader, 70));

		List<Map.Entry<MusicInstrument, Integer>> sortedList = Tools.convertMapToSortedByCriteriaList(sold,
		    MusicInstrumentStore.soldByQuantityComparator);
		if (!sortedList.isEmpty()) {
			MusicInstrument bestSoldInstrument;
			int maxQuantity = sortedList.get(0).getValue();
			for (int j = 0; j < sortedList.size(); j++) {
				if (sortedList.get(j).getValue() >= maxQuantity) {
					maxQuantity = sortedList.get(j).getValue();
					bestSoldInstrument = sortedList.get(j).getKey();
					System.out.println(Tools.format("" + ( j + 1 ), 2) + bestSoldInstrument + Tools.format("" + maxQuantity, 8));
				}
			}
		}
	}

	public void printWorsedSoldInstruments() {
		System.out.println(Tools.printFrame("Worsed Sold Instruments", 70));
		String tableHeader = getTableHeader();

		System.out.println(Tools.printFrame(tableHeader, 70));

		List<Map.Entry<MusicInstrument, Integer>> sortedList = Tools.convertMapToSortedByCriteriaList(sold,
		    MusicInstrumentStore.soldByQuantityComparator);

		if (!sortedList.isEmpty()) {
			MusicInstrument worsedSoldInstrument;
			int minQuantity = sortedList.get(0).getValue();
			for (int j = sortedList.size() - 1; j >= 0; j--) {
				if (sortedList.get(j).getValue() <= minQuantity) {
					minQuantity = sortedList.get(j).getValue();
					worsedSoldInstrument = sortedList.get(j).getKey();
					System.out
					    .println(Tools.format("" + ( j + 1 ), 2) + worsedSoldInstrument + Tools.format("" + minQuantity, 8));
				}
			}
		}
	}

	public void printAllIncomes() {
		double income = 0.0;
		if (!sold.isEmpty()) {

			for (MusicInstrument instrument : sold.keySet()) {
				income += instrument.getPrice();

			}
			System.out.println(Tools.printFrame("\nThe income from all sold instruments is :" + income + " BGN\n", 70));

		} else {
			System.out.println("\nThere are no sales, the incomes is :" + income + " BGN\n");
		}
	}

	private String getTableHeader() {
		String tableHeader = ( Tools.format("N", 2)
		                       + Tools.format("Name", 18)
		                       + Tools.format("Type", 12)
		                       + Tools.format("Price", 12)
		                       + Tools.format("Quantity", 8) );
		return tableHeader;
	}

	public void printBestSoldByType() {
		System.out.println(Tools.printFrame("Best Sold Instruments By Type", 70));
		String tableHeader = getTableHeader();

		System.out.println(Tools.printFrame(tableHeader, 70));

		Map<Type, Integer> instrumentByTypes = new HashMap<>();
		Type currentType;
		Integer currentQuantity;

		if (!sold.isEmpty()) {

			for (MusicInstrument instrument : sold.keySet()) {
				currentType = instrument.getType();
				currentQuantity = sold.get(instrument).intValue();

				Integer currrentTypeQuantity = instrumentByTypes.get(currentType);

				if (currrentTypeQuantity != null) {
					instrumentByTypes.put(currentType, currrentTypeQuantity + currentQuantity);

				} else {
					instrumentByTypes.put(currentType, currentQuantity);
				}
			}

			List<Map.Entry<Type, Integer>> sortedList = Tools.convertMapToSortedByCriteriaList(instrumentByTypes,
			    MusicInstrumentStore.soldByTypeComparator);
			if (!sortedList.isEmpty()) {
				Type bestSoldType;
				int maxQuantity = sortedList.get(0).getValue();
				for (int j = 0; j < sortedList.size(); j++) {
					if (sortedList.get(j).getValue() >= maxQuantity) {
						maxQuantity = sortedList.get(j).getValue();
						bestSoldType = sortedList.get(j).getKey();
						System.out.println(Tools.format("" + ( j + 1 ), 2) + bestSoldType + Tools.format("" + maxQuantity, 8));
					}
				}
			}
		} else {
			System.out.println("\nThere are no sales");
		}
	}

	public void printBestIncomeByType() {
		System.out.println(Tools.printFrame("Best Sold Income from Instruments By Type", 70));
		String tableHeader = getTableHeader();

		System.out.println(Tools.printFrame(tableHeader, 70));

		Map<Type, Double> incomeInstrumentByTypes = new HashMap<>();
		Type currentType;
		Double currentPrice;

		if (!sold.isEmpty()) {

			for (MusicInstrument instrument : sold.keySet()) {
				currentType = instrument.getType();
				currentPrice = instrument.getPrice();

				Double currrentTypePrice = incomeInstrumentByTypes.get(currentType);

				if (currrentTypePrice != null) {
					incomeInstrumentByTypes.put(currentType, currrentTypePrice + currentPrice * sold.get(instrument));

				} else {
					incomeInstrumentByTypes.put(currentType, currentPrice * sold.get(instrument));
				}
			}

			List<Map.Entry<Type, Double>> sortedList = Tools.convertMapToSortedByCriteriaList(incomeInstrumentByTypes,
			    MusicInstrumentStore.soldByPriceComparator);
			if (!sortedList.isEmpty()) {
				Type bestSoldType;
				Double maxQuantity = sortedList.get(0).getValue();

				for (int j = 0; j < sortedList.size(); j++) {
					if (sortedList.get(j).getValue() >= maxQuantity) {
						maxQuantity = sortedList.get(j).getValue();
						bestSoldType = sortedList.get(j).getKey();
						System.out.println(Tools.format("" + ( j + 1 ), 2) + bestSoldType + Tools.format("" + maxQuantity, 8));
					}
				}
			}
		} else {
			System.out.println("\nThere are no sales");
		}
	}
}
