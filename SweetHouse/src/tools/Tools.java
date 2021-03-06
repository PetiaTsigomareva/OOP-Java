package tools;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

public class Tools {
	public static <T> T getRandom(List<T> list) {
		T result;
		int randomIndex = new Random().nextInt(list.size());

		result = list.get(randomIndex);

		return result;
	}

	public static <T> T getRandom(T[] list) {
		T result;
		int randomIndex = new Random().nextInt(list.length);

		result = list[randomIndex];

		return result;
	}

	public static <T> T getRandom(Set<T> set) {
		T result;
		int randomIndex = new Random().nextInt(set.size());

		result = new ArrayList<T>(set).get(randomIndex);

		return result;
	}

	public static int getRandomNumber(int min, int max) {
		return new Random().nextInt(max - min + 1) + min;
	}

	public static String getRandomPhoneNumber() {
		int randomPhoneNumber = new Random().nextInt(9000000) + 1000000;
		return "088" + randomPhoneNumber;
	}

	// Kind is enum return random enum element

	// public static Kind getRandomKind() {
	// return values()[new Random().nextInt(values().length)];
	// }

	public static LocalDate getRandomDate() {

		return LocalDate.now().plusDays(getRandomNumber(2, 5));

	}

	public static String format(String s, int width) {
		StringBuilder result = new StringBuilder();

		result.append("| ");

		for (int i = 0; i < width - s.length(); i++) {
			result.append(" ");
		}

		result.append(s);

		result.append(" |");

		return result.toString();
	}

	public static String printFrame(String s, int width) {
		StringBuilder result = new StringBuilder();

		result.append("+");
		for (int i = 0; i < width - 2; i++) {
			result.append("-");
		}
		result.append("+\n");

		result.append("|");
		for (int i = 0; i < ( ( width - s.length() - 2 ) / 2 ); i++) {
			result.append(" ");
		}
		result.append(s);
		for (int i = 0; i < ( ( width - s.length() - 2 ) / 2 ); i++) {
			result.append(" ");
		}
		result.append("|\n");

		result.append("+");
		for (int i = 0; i < width - 2; i++) {
			result.append("-");
		}
		result.append("+");

		return result.toString();
	}

	public static <K, V> void printMap(Map<K, V> map) {
		int i = 0;
		for (Map.Entry<K, V> e : map.entrySet()) {
			System.out.println("" + ++i + " [" + e.getKey() + "] : [" + e.getValue() + "]");
		}
	}

	public static <K, V> String formatMap(Map<K, V> map) {
		StringBuilder result = new StringBuilder();
		int i = 0;

		for (Map.Entry<K, V> e : map.entrySet()) {
			result.append("").append(++i).append(" [").append(e.getKey()).append("] : [").append(e.getValue() + "]\n");
		}

		return result.toString();
	}

	public static <K, V> TreeMap<K, V> convertMapToSortedByCriteriaMap(Map<K, V> mapToSort, Comparator<K> comparator) {
		TreeMap<K, V> result = new TreeMap<K, V>(comparator);
		result.putAll(mapToSort);

		return result;
	}

	public static <K, V> List<Map.Entry<K, V>> convertMapToSortedByCriteriaList(Map<K, V> mapToSort,
	    Comparator<Map.Entry<K, V>> comparator) {
		List<Map.Entry<K, V>> result = new ArrayList<>(mapToSort.entrySet());

		Collections.sort(result, comparator);

		return result;
	}

	public static <K, V> K findKey(Map<K, V> map, K keyToSearch) {
		K result = null;

		for (K key : map.keySet()) {
			if (key.equals(keyToSearch)) {
				result = key;
				break;
			}
		}

		return result;
	}
}