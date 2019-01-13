package imoti.agency;

import java.util.Date;

import imoti.estate.Estate;
import imoti.person.Agent;
import imoti.person.Buyer;

public class PropertyInspection {

	private Estate estate;
	private Agent agent;
	private Buyer buyer;
	private Date dateView;

	public PropertyInspection(Estate estate, Agent agent, Buyer buyer, Date dateView) {
		super();
		this.estate = estate;
		this.agent = agent;
		this.buyer = buyer;
		this.dateView = dateView;
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

	/**
	 * @return the buyer
	 */
	public Buyer getBuyer() {
		return buyer;
	}

	/**
	 * @param buyer
	 *          the buyer to set
	 */
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	/**
	 * @return the dateView
	 */
	public Date getDateView() {
		return dateView;
	}

	/**
	 * @param dateView
	 *          the dateView to set
	 */
	public void setDateView(Date dateView) {
		this.dateView = dateView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PropertyInspection [estate="
		       + estate
		       + ", agent="
		       + agent
		       + ", buyer="
		       + buyer
		       + ", dateView="
		       + dateView
		       + "]";
	}

}
