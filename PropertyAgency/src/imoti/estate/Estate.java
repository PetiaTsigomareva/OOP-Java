package imoti.estate;

import java.util.Random;

import imoti.person.Agent;
import imoti.person.Seller;

public abstract class Estate implements Comparable<Estate> {
	public enum EstateType {
		APARTMENT, HOUSE, PLOT
	}

	private EstateType estateType;
	private String estateSubType;

	private String description;
	private String address;
	private double priceEstate;
	private double area;

	private Agent agent;
	private Seller seller;

	public Estate(EstateType estateType, String estateSubType, String description, String address, double priceEstate,
	    double area) {
		super();
		this.estateType = estateType;
		this.estateSubType = estateSubType;
		this.description = description;
		this.address = address;
		this.priceEstate = priceEstate;
		this.area = area;

	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *          the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *          the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the priceEstate
	 */
	public double getPriceEstate() {
		return priceEstate;
	}

	/**
	 * @param priceEstate
	 *          the priceEstate to set
	 */
	public void setPriceEstate(double priceEstate) {
		this.priceEstate = priceEstate;
	}

	/**
	 * @return the area
	 */
	public double getArea() {
		return area;
	}

	/**
	 * @param area
	 *          the area to set
	 */
	public void setArea(double area) {
		this.area = area;
	}

	/**
	 * @return the seller
	 */
	public Seller getSeller() {
		return seller;
	}

	/**
	 * @param seller
	 *          the seller to set
	 */
	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	/**
	 * @return the estateType
	 */
	public EstateType getEstateType() {
		return estateType;
	}

	/**
	 * @param estateType
	 *          the estateType to set
	 */
	public void setEstateType(EstateType estateType) {
		this.estateType = estateType;
	}

	/**
	 * @return the estateSubType
	 */
	public String getEstateSubType() {
		return estateSubType;
	}

	/**
	 * @param estateSubType
	 *          the estateSubType to set
	 */
	public void setEstateSubType(String estateSubType) {
		this.estateSubType = estateSubType;
	}

	@Override
	public int compareTo(Estate o) {
		return (int) ( o.priceEstate - this.priceEstate );
	}

	public static String getRandomAddress() {
		return "Address " + new Random().nextInt(1000);
	}

	public static int getRandomPrice(int minPrice, int maxPrice) {
		return new Random().nextInt(maxPrice - minPrice + 1) + minPrice;
	}

	public static int getRandomArea() {
		return new Random().nextInt(1000);
	}

	public static String getRandomDescription() {
		return "Description for " + new Random().nextInt(1000);
	}

	public static Estate getRandomEstate() {
		Estate estate;

		int typeIndex = new Random().nextInt(3);

		switch (typeIndex) {
		case 0:
			estate = Apartment.getRandomApartment();
			break;

		case 1:
			estate = House.getRandomHouse();
			break;

		case 2:
			estate = Plot.getRandomPlot();
			break;

		default:
			System.out.println("WRONG ESTATE TYPE INDEX PROVIDED!!!!!");
			estate = null;
			break;
		}

		return estate;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */

	@Override
	public String toString() {
		return "Estate [estateType="
		       + estateType
		       + ", estateSubType="
		       + estateSubType
		       + ", description="
		       + description
		       + ", address="
		       + address
		       + ", priceEstate="
		       + priceEstate
		       + ", area="
		       + area
		       + ", agent="
		       + agent
		       + ", seller="
		       + seller
		       + handleToString()
		       + "]";
	}

	protected abstract String handleToString();

}
