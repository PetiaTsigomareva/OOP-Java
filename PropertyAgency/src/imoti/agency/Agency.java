package imoti.agency;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import imoti.estate.Estate;
import imoti.estate.Estate.EstateType;
import imoti.person.Agent;
import imoti.person.Buyer;
import imoti.person.Seller;

public class Agency {
	private String name;
	private String phone;
	private String address;
	private List<Agent> agents;
	private Map<EstateType, Set<Estate>> catalog;

	private double income;

	public Agency(String name, String phone, String address) {
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.agents = new ArrayList<>();
		this.catalog = new HashMap<>();
	}

	/**
	 * @return the catalog
	 */
	public Map<EstateType, Set<Estate>> getCatalog() {
		return catalog;
	}

	/**
	 * @param catalog
	 *          the catalog to set
	 */
	public void setCatalog(Map<EstateType, Set<Estate>> catalog) {
		this.catalog = catalog;
	}

	/**
	 * @return the income
	 */
	public double getIncome() {
		return income;
	}

	/**
	 * @param income
	 *          the income to set
	 */
	public void setIncome(double income) {
		this.income = income;
	}

	public List<Agent> getAgents() {
		return agents;
	}

	public void setAgents(List<Agent> agents) {
		this.agents = agents;
	}

	public void registerSeller(Seller seller) {
		// get random agent
		Agent agent = getRandomAgent();

		// add Seller to agent list of Sellers
		agent.getSellers().add(seller);
		seller.setAgent(agent);

		EstateType estateTypeKey = seller.getEstate().getEstateType();
		Set<Estate> estatesFromThisTypeKey = catalog.get(estateTypeKey);

		if (estatesFromThisTypeKey == null) { // this estate type is added for the first time in the catalog
			// instantiate the Set
			estatesFromThisTypeKey = new TreeSet<Estate>();
		}

		// add the Estate to this EstateType's Set
		estatesFromThisTypeKey.add(seller.getEstate());
		catalog.put(estateTypeKey, estatesFromThisTypeKey);

		seller.setAgent(agent);
		seller.getEstate().setAgent(agent);
	}

	public void registerBuyer(Buyer buyer) {
		// get random agent
		Agent agent = getRandomAgent();

		// add Buyer to agent list of Buyers
		agent.getBuyers().add(buyer);
		buyer.setAgent(agent);
	}

	private Agent getRandomAgent() {
		return agents.get(new Random().nextInt(agents.size()));
	}

	// TODO BUG
	public void requestPropertyInspection(Estate estate, Buyer buyer) {
		// get estate from agency catalog
		Set<Estate> estatesSet = catalog.get(estate.getEstateType());

		if (estatesSet != null && estatesSet.contains(estate) && estate.getPriceEstate() <= buyer.getBudget()) {
			PropertyInspection propertyInspection = new PropertyInspection(estate, buyer.getAgent(), buyer, new Date());
			buyer.getPropertyInspections().add(propertyInspection);
			buyer.getAgent().getPropertyInspections().add(propertyInspection);
		} else {
			System.out
			    .println("The buget "
			             + buyer.getBudget()
			             + " of the buyer is lower than estate price "
			             + estate.getPriceEstate()
			             + ". Property inspection could't be performed!!! ");
		}
	}

	public void buyEstate(Estate estate, Buyer buyer) {
		// check if estate is content of the buyer property inspection list
		List<PropertyInspection> list = buyer.getPropertyInspections();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getEstate().equals(estate)) {
				double currentEstateCommission = 0.015 * estate.getPriceEstate();

				// buyer 3% commision
				buyer.getAgent().setCommission(buyer.getAgent().getCommission() + currentEstateCommission);
				buyer.getAgent().getAgency().setIncome(buyer.getAgent().getAgency().getIncome() + currentEstateCommission);

				// seller 3% commision
				estate.getSeller().getAgent()
				    .setCommission(estate.getSeller().getAgent().getCommission() + currentEstateCommission);
				estate.getSeller().getAgent().getAgency()
				    .setIncome(estate.getSeller().getAgent().getAgency().getIncome() + currentEstateCommission);

				// TODO: Mark the estate as sold or remove it from the catalog

				System.out.println("Buyer " + buyer.toString() + " bought estate " + estate.toString());
			}
		}
	}

	public void printAgents() {
		for (int i = 0; i < agents.size(); i++) {
			System.out.println(agents.get(i).toString());
		}
	}

	public void printCatalog() {

		Iterator<EstateType> keysIterator = catalog.keySet().iterator();

		while (keysIterator.hasNext()) {
			EstateType key = keysIterator.next();
			System.out.println(key);
			Set<Estate> estates = catalog.get(key);
			Iterator<Estate> valueIterator = estates.iterator();
			while (valueIterator.hasNext()) {
				Estate estate = valueIterator.next();
				System.out.println(estate);
			}

		}
	}
	// TODOOOOOOO

	// public List<Estate> getRondomThreeEstate() {
	// List<Estate> randomTreeEstates = new ArrayList<>();
	// Estate.getRandomEstate();
	// Iterator<Set<Estate>> keysIterator = catalog.values().iterator();
	//
	// return randomTreeEstates;
	// }

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *          the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *          the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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

}