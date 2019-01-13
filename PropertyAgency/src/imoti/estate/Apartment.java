package imoti.estate;

import java.util.Random;

public class Apartment extends Building {
	public enum ApartmentType {
		STUDIO, GARSONIERA, DRUSTAEN, TRISTAEN, MEZONET
	}

	public Apartment(EstateType estateType, String estateSubType, String description, String address, double priceEstate,
	    double area,
	    ConstructionType constructionType) {
		super(estateType, estateSubType, description, address, priceEstate, area, constructionType);
	}

	public static Apartment getRandomApartment() {
		Apartment apartment;

		int apartmentTypeIndex = new Random().nextInt(5);
		ApartmentType apartmentType;

		switch (apartmentTypeIndex) {
		case 0:
			apartmentType = ApartmentType.GARSONIERA;
			break;
		case 1:
			apartmentType = ApartmentType.DRUSTAEN;
			break;
		case 2:
			apartmentType = ApartmentType.TRISTAEN;
			break;
		case 3:
			apartmentType = ApartmentType.MEZONET;
			break;
		case 4:
			apartmentType = ApartmentType.STUDIO;
			break;

		default:
			System.out.println("WRONG APARTMENT TYPE INDEX PROVIDED!!!!!");
			apartmentType = null;
			break;
		}

		apartment = new Apartment(EstateType.APARTMENT, apartmentType.toString(), getRandomDescription(),
		    getRandomAddress(), getRandomPrice(70000, 150000),
		    getRandomArea(), Building.getRandomConstructionType());

		return apartment;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String handleToString() {
		return "Apartment []";
	}

}