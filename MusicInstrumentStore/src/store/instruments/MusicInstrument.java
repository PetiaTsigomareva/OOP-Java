package store.instruments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import tools.Tools;

public class MusicInstrument implements Comparable<MusicInstrument> {
	public enum Name {
		SYNTHESIZER, BASS_GUITARE, ELECTRONIC_VIOLIN, ORGAN, PIANO, ACCORDION, DRUM, TARANBUKA, KETTLE_DRUM, TAMBOURINE, VIOLIN, VIOLA, CONTRABASS, HARP, GUITAR, REBEC, TRUMPET, TROMBONE, TUBA, FLUTE, CLARINET
	}

	public enum Type {
		ELECTRONIC, KEYBOARD, PERCUSSION, STRINGED, WIND
	}

	public static Comparator<MusicInstrument> typeComparator = new Comparator<MusicInstrument>() {
		@Override
		public int compare(MusicInstrument o1, MusicInstrument o2) {
			int result;

			result = o1.type.toString().compareTo(o2.type.toString());

			if (result == 0) {
				result = o1.name.toString().compareTo(o2.name.toString());
			}

			if (result == 0) {
				result = Double.compare(o1.price, o2.price);
			}

			return result;
		}
	};

	public static Comparator<MusicInstrument> nameByAlphabetComparator = new Comparator<MusicInstrument>() {
		@Override
		public int compare(MusicInstrument o1, MusicInstrument o2) {
			int result;

			result = o1.name.toString().compareTo(o2.name.toString());

			if (result == 0) {
				result = o1.type.toString().compareTo(o2.type.toString());
			}

			if (result == 0) {
				result = Double.compare(o1.price, o2.price);
			}

			return result;
		}
	};

	public static Comparator<MusicInstrument> priceAscendingComparator = new Comparator<MusicInstrument>() {
		@Override
		public int compare(MusicInstrument o1, MusicInstrument o2) {
			int result;

			result = Double.compare(o1.price, o2.price);

			if (result == 0) {
				result = o1.name.toString().compareTo(o2.name.toString());
			}

			if (result == 0) {
				result = o1.type.toString().compareTo(o2.type.toString());
			}

			return result;
		}
	};
	public static Comparator<MusicInstrument> priceDescendingComparator = new Comparator<MusicInstrument>() {
		@Override
		public int compare(MusicInstrument o1, MusicInstrument o2) {
			int result;

			result = Double.compare(o1.price, o2.price);

			if (result == 0) {
				result = o1.name.toString().compareTo(o2.name.toString());
			}

			if (result == 0) {
				result = o1.type.toString().compareTo(o2.type.toString());
			}

			return result;
		}
	};

	private static Map<Name, Type> instrumentTypesMap = new TreeMap<>();

	static {
		instrumentTypesMap.put(Name.SYNTHESIZER, Type.ELECTRONIC);
		instrumentTypesMap.put(Name.BASS_GUITARE, Type.ELECTRONIC);
		instrumentTypesMap.put(Name.ELECTRONIC_VIOLIN, Type.ELECTRONIC);

		instrumentTypesMap.put(Name.ORGAN, Type.KEYBOARD);
		instrumentTypesMap.put(Name.PIANO, Type.KEYBOARD);
		instrumentTypesMap.put(Name.ACCORDION, Type.KEYBOARD);

		instrumentTypesMap.put(Name.DRUM, Type.PERCUSSION);
		instrumentTypesMap.put(Name.TARANBUKA, Type.PERCUSSION);
		instrumentTypesMap.put(Name.KETTLE_DRUM, Type.PERCUSSION);
		instrumentTypesMap.put(Name.TAMBOURINE, Type.PERCUSSION);

		instrumentTypesMap.put(Name.VIOLIN, Type.STRINGED);
		instrumentTypesMap.put(Name.VIOLA, Type.STRINGED);
		instrumentTypesMap.put(Name.CONTRABASS, Type.STRINGED);
		instrumentTypesMap.put(Name.HARP, Type.STRINGED);
		instrumentTypesMap.put(Name.GUITAR, Type.STRINGED);
		instrumentTypesMap.put(Name.REBEC, Type.STRINGED);

		instrumentTypesMap.put(Name.TRUMPET, Type.WIND);
		instrumentTypesMap.put(Name.TROMBONE, Type.WIND);
		instrumentTypesMap.put(Name.TUBA, Type.WIND);
		instrumentTypesMap.put(Name.FLUTE, Type.WIND);
		instrumentTypesMap.put(Name.CLARINET, Type.WIND);
	}

	private Type type;
	private Name name;
	private double price;

	public MusicInstrument(Name name, double price) {
		super();

		if (instrumentTypesMap.keySet().contains(name)) {
			this.name = name;
		} else {
			throw new RuntimeException("Instrument " + name + " is not among the known: " + instrumentTypesMap.keySet());
		}

		this.type = instrumentTypesMap.get(name);

		this.price = price;
	}

	public static void printInstrumentsTypeToNameMapping() {
		List<Map.Entry<Name, Type>> list = new ArrayList<>(instrumentTypesMap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Name, Type>>() {

			@Override
			public int compare(Entry<Name, Type> o1, Entry<Name, Type> o2) {
				return o1.getKey().toString().compareTo(o2.getKey().toString());
			}
		});

		for (Map.Entry<Name, Type> e : list) {
			System.out.println(e.getKey() + ": " + e.getValue());
		}
	}

	public static Set<Name> getAllNames() {
		return instrumentTypesMap.keySet();
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *          the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
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
		MusicInstrument other = (MusicInstrument) obj;
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
		return Tools.format(name.toString(), 18) + Tools.format(type.toString(), 12) + Tools.format(price + " BGN", 12);
	}

	@Override
	public int compareTo(MusicInstrument o) {
		return this.name.toString().compareTo(o.name.toString());
	}

}
