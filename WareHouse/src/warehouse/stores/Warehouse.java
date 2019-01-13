package warehouse.stores;

public class Warehouse extends Store {
	private WarehouseSupplyer supplyer;

	public Warehouse(String name) {
		super(name);

	}

	/**
	 * @return the supplyer
	 */
	public WarehouseSupplyer getSupplyer() {
		return supplyer;
	}

	/**
	 * @param supplyer
	 *          the supplyer to set
	 */
	public void setSupplyer(WarehouseSupplyer supplyer) {
		this.supplyer = supplyer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString();
	}

}
