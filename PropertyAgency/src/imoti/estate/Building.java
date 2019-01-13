package imoti.estate;

import java.util.Random;

public abstract class Building extends Estate {
	public enum ConstructionType {
		EPK, BUILDING_BLOK, PANEL, KIRPICH
	}

	private ConstructionType constructionType;

	public Building(EstateType estateType, String estateSubType, String description, String address, double priceEstate,
	    double area,
	    ConstructionType constructionType) {
		super(estateType, estateSubType, description, address, priceEstate, area);

		this.constructionType = constructionType;
	}

	/**
	 * @return the constructionType
	 */
	public ConstructionType getConstructionType() {
		return constructionType;
	}

	/**
	 * @param constructionType
	 *          the constructionType to set
	 */
	public void setConstructionType(ConstructionType constructionType) {
		this.constructionType = constructionType;
	}

	public static ConstructionType getRandomConstructionType() {
		int constructionTypeIndex = new Random().nextInt(4);
		ConstructionType constructionType;

		switch (constructionTypeIndex) {
		case 0:
			constructionType = ConstructionType.BUILDING_BLOK;
			break;
		case 1:
			constructionType = ConstructionType.EPK;
			break;
		case 2:
			constructionType = ConstructionType.KIRPICH;
			break;
		case 3:
			constructionType = ConstructionType.PANEL;
			break;

		default:
			System.out.println("WRONG CONSTRUCTION TYPE INDEX PROVIDED!!!!!");
			constructionType = null;
			break;
		}

		return constructionType;
	}
}
