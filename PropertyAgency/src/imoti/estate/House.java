package imoti.estate;

import java.util.Random;

public class House extends Building {
	public enum HouseType {
		PART_OF_HOUSE, HOUSE
	}

	private double yardArea;
	private int parkingLots;

	public House(EstateType estateType, String estateSubType, String description, String address, double priceEstate,
	    double area,
	    ConstructionType constructionType, double yardArea, int parkingLots) {
		super(estateType, estateSubType, description, address, priceEstate, yardArea, constructionType);

		this.yardArea = yardArea;
		this.parkingLots = parkingLots;
	}

	/**
	 * @return the yardArea
	 */
	public double getYardArea() {
		return yardArea;
	}

	/**
	 * @param yardArea
	 *          the yardArea to set
	 */
	public void setYardArea(double yardArea) {
		this.yardArea = yardArea;
	}

	/**
	 * @return the parkingLots
	 */
	public int getParkingLots() {
		return parkingLots;
	}

	/**
	 * @param parkingLots
	 *          the parkingLots to set
	 */
	public void setParkingLots(int parkingLots) {
		this.parkingLots = parkingLots;
	}

	public static House getRandomHouse() {
		House house;

		int houseTypeIndex = new Random().nextInt(2);
		HouseType houseType;

		switch (houseTypeIndex) {
		case 0:
			houseType = HouseType.PART_OF_HOUSE;
			break;

		case 1:
			houseType = HouseType.HOUSE;
			break;

		default:
			System.out.println("WRONG HOUSE TYPE INDEX PROVIDED!!!!!");
			houseType = null;
			break;
		}

		int yardArea = new Random().nextInt(1000) + 1000;
		int parkingLots = new Random().nextInt(10);

		house = new House(EstateType.HOUSE, houseType.toString(), getRandomDescription(), getRandomAddress(),
		    getRandomPrice(50000, 80000),
		    getRandomArea(), Building.getRandomConstructionType(), yardArea, parkingLots);

		return house;
	}

	@Override
	public String handleToString() {
		return "House [yardArea=" + yardArea + ", parkingLots=" + parkingLots + "]";
	}

}
