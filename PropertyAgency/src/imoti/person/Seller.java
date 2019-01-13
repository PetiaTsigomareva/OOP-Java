package imoti.person;

import imoti.estate.Estate;

public class Seller extends Person {
	private Estate estate;
	private Agent agent;

	public Seller(String personName, String phone, Estate estate) {
		super(personName, phone);
		this.estate = estate;
	}

	public static Seller createRandomSeller() {
		return new Seller(getRandomName("Seller"), getRandomPhoneNumber(), Estate.getRandomEstate());
	}

	/**
	 * @return the estate
	 */
	public Estate getEstate() {
		return estate;
	}

	/**
	 * @param estate
	 *          the estate to set
	 */
	public void setEstate(Estate estate) {
		this.estate = estate;
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
		return "Seller [estate=" + estate + ", agent=" + agent + "]";
	}

}