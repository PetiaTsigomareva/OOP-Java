package imoti.person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import imoti.agency.Agency;
import imoti.agency.PropertyInspection;

public class Agent extends Person {
	Agency agency;
	private Set<Seller> sellers;
	private Set<Buyer> buyers;
	private List<PropertyInspection> propertyInspections;
	private double commission;

	public Agent(String personName, String phoneNumber, Agency agency) {
		super(personName, phoneNumber);

		this.agency = agency;
		this.sellers = new HashSet<>();
		this.buyers = new HashSet<>();
		this.propertyInspections = new ArrayList<>();
	}

	public static Agent createRandomAgent(Agency agency) {
		return new Agent(getRandomName("Agent"), getRandomPhoneNumber(), agency);
	}

	public Set<Seller> getSellers() {
		return sellers;
	}

	public void setSellers(Set<Seller> sellers) {
		this.sellers = sellers;
	}

	public Set<Buyer> getBuyers() {
		return buyers;
	}

	public void setBuyers(Set<Buyer> buyers) {
		this.buyers = buyers;
	}

	/**
	 * @return the propertyInspection
	 */
	public List<PropertyInspection> getPropertyInspections() {
		return propertyInspections;
	}

	/**
	 * @param propertyInspections
	 *          the propertyInspection to set
	 */
	public void setPropertyInspections(List<PropertyInspection> propertyInspections) {
		this.propertyInspections = propertyInspections;
	}

	/**
	 * @return the agency
	 */
	public Agency getAgency() {
		return agency;
	}

	/**
	 * @param agency
	 *          the agency to set
	 */
	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	/**
	 * @return the commission
	 */
	public double getCommission() {
		return commission;
	}

	/**
	 * @param commission
	 *          the commission to set
	 */
	public void setCommission(double commission) {
		this.commission = commission;
	}

	@Override
	public String handleToString() {
		return "Agent [agency="
		       + agency.getName()
		       + ", name="
		       + getPersonName()
		       + ", phone="
		       + getPhoneNumber()
						// + ", sellers="
						 // + sellers
						// + ", buyers="
						 // + buyers
		       + ", propertyInspections="
		       + propertyInspections
		       + ", commission="
		       + commission
		       + "]";
	}
}