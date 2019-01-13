package store.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import store.MusicInstrumentStore;
import store.instruments.MusicInstrument;
import store.instruments.MusicInstrument.Name;
import tools.Tools;

public class MusicInstrumentStoreDemo {
	private static final double CASH_DESK_INITIAL_AVAILABILITY = 100;

	private static Map<Name, Double> instrumentPrices = new TreeMap<>();

	static {
		instrumentPrices.put(Name.SYNTHESIZER, 300D);
		instrumentPrices.put(Name.BASS_GUITARE, 200D);
		instrumentPrices.put(Name.ELECTRONIC_VIOLIN, 400D);

		instrumentPrices.put(Name.ORGAN, 10000D);
		instrumentPrices.put(Name.PIANO, 700D);
		instrumentPrices.put(Name.ACCORDION, 150D);

		instrumentPrices.put(Name.DRUM, 250D);
		instrumentPrices.put(Name.TARANBUKA, 250D);
		instrumentPrices.put(Name.KETTLE_DRUM, 250D);
		instrumentPrices.put(Name.TAMBOURINE, 59D);

		instrumentPrices.put(Name.VIOLIN, 223D);
		instrumentPrices.put(Name.VIOLA, 220D);
		instrumentPrices.put(Name.CONTRABASS, 298D);
		instrumentPrices.put(Name.HARP, 234D);
		instrumentPrices.put(Name.GUITAR, 180D);
		instrumentPrices.put(Name.REBEC, 120D);

		instrumentPrices.put(Name.TRUMPET, 248D);
		instrumentPrices.put(Name.TROMBONE, 123D);
		instrumentPrices.put(Name.TUBA, 345D);
		instrumentPrices.put(Name.FLUTE, 234D);
		instrumentPrices.put(Name.CLARINET, 124D);
	}

	public static void main(String[] args) {
		MusicInstrument.printInstrumentsTypeToNameMapping();
		Map<MusicInstrument, Integer> loadingInstruments = new HashMap<>();
		MusicInstrumentStore store = new MusicInstrumentStore("House of the Instruments", CASH_DESK_INITIAL_AVAILABILITY);

		for (int i = 0; i < 15; i++) {
			MusicInstrument instrument = createRandomInstrument();
			store.addInstrument(instrument, Tools.getRandomNumber(1, 5));
		}

		loadingInstruments.put(createRandomInstrument(), Tools.getRandomNumber(1, 5));
		loadingInstruments.put(createRandomInstrument(), Tools.getRandomNumber(1, 5));
		loadingInstruments.put(createRandomInstrument(), Tools.getRandomNumber(1, 5));
		loadingInstruments.put(createRandomInstrument(), Tools.getRandomNumber(1, 5));
		store.addInstruments(loadingInstruments);

		store.printStoreStatus(MusicInstrument.typeComparator);

		store.printAllIncomes();
		store.sellInstrument(Name.SYNTHESIZER, 3);
		store.sellInstrument(Name.BASS_GUITARE, 3);
		store.sellInstrument(Name.ELECTRONIC_VIOLIN, 2);

		store.sellInstrument(Name.ORGAN, 1);
		store.sellInstrument(Name.PIANO, 4);
		store.sellInstrument(Name.ACCORDION, 5);

		store.sellInstrument(Name.DRUM, 3);
		store.sellInstrument(Name.TARANBUKA, 3);
		store.sellInstrument(Name.KETTLE_DRUM, 3);
		store.sellInstrument(Name.TAMBOURINE, 3);

		store.sellInstrument(Name.VIOLIN, 3);
		store.sellInstrument(Name.VIOLA, 3);
		store.sellInstrument(Name.CONTRABASS, 3);
		store.sellInstrument(Name.HARP, 3);
		store.sellInstrument(Name.GUITAR, 3);
		store.sellInstrument(Name.REBEC, 3);

		store.sellInstrument(Name.TRUMPET, 3);
		store.sellInstrument(Name.TROMBONE, 3);
		store.sellInstrument(Name.TUBA, 3);
		store.sellInstrument(Name.FLUTE, 3);
		store.sellInstrument(Name.CLARINET, 3);

		store.printStoreStatus(MusicInstrument.typeComparator);
		store.printStoreStatus(MusicInstrument.nameByAlphabetComparator);
		store.printStoreStatus(MusicInstrument.priceAscendingComparator);
		store.printStoreStatus(MusicInstrument.priceDescendingComparator);

		store.printStoreSoldByQuantity(MusicInstrumentStore.soldByQuantityComparator);

		store.printAllIncomes();

		store.printBestSoldInstruments();

		store.printWorsedSoldInstruments();

		store.printBestSoldByType();

		store.printBestIncomeByType();
	}

	private static MusicInstrument createRandomInstrument() {
		MusicInstrument.Name name = Tools.getRandom(MusicInstrument.getAllNames());

		MusicInstrument result = new MusicInstrument(name, instrumentPrices.get(name));

		return result;
	}
}