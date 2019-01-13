package imoti.person;

import java.util.List;

import imoti.agency.PropertyInspection;
import imoti.estate.Estate;

public class Buyer extends Person {
	private double budget;
	private List<PropertyInspection> propertyInspections;
	private Agent agent;

	public Buyer(String personName, String phoneNumber, double budget) {
		super(personName, phoneNumber);
		this.budget = budget;

	}

	public static Buyer createRandomBuyer() {
		return new Buyer(getRandomName("Buyer"), getRandomPhoneNumber(), getBoundBudget(30000, 150000));
	}

	public static double getBoundBudget(int minBudget, int maxBudget) {
		double randomBudget = Estate.getRandomPrice(minBudget, maxBudget);
		return randomBudget;

	}

	/**
	 * @return the budget
	 */
	public double getBudget() {
		return budget;
	}

	/**
	 * @param budget
	 *          the budget to set
	 */
	public void setBudget(double budget) {
		this.budget = budget;
	}

	/**
	 * @return the propertyInspections
	 */
	public List<PropertyInspection> getPropertyInspections() {
		return propertyInspections;
	}

	/**
	 * @param propertyInspections
	 *          the propertyInspections to set
	 */
	public void setPropertyInspections(List<PropertyInspection> propertyInspections) {
		this.propertyInspections = propertyInspections;
	}

	/**
	 * @return the agent
	 */
	public Agent getAgent() {
		return agent;
	}

	/**
	 * @param agent
	 *          the agent to set
	 */
	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	@Override
	public String handleToString() {
		return "Buyer [budget=" + budget + ", propertyInspections=" + propertyInspections + ", agent=" + agent + "]";
	}
}