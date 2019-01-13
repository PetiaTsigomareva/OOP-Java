package imoti.estate;

import java.util.Random;

public class Plot extends Estate {
	public enum PlotType {
		LAND, FIELD, FOREST
	}

	private boolean isRegulated;

	public Plot(EstateType estateType, String estateSubType, String description, String address, double priceEstate,
	    double area,
	    boolean isRegulated) {
		super(estateType, estateSubType, description, address, priceEstate, area);

		this.isRegulated = isRegulated;
	}

	/**
	 * @return the isRegulated
	 */
	public boolean isRegulated() {
		return isRegulated;
	}

	/**
	 * @param isRegulated
	 *          the isRegulated to set
	 */
	public void setRegulated(boolean isRegulated) {
		this.isRegulated = isRegulated;
	}

	public static Plot getRandomPlot() {
		Plot plot;

		int plotTypeIndex = new Random().nextInt(3);
		PlotType plotType;

		switch (plotTypeIndex) {
		case 0:
			plotType = PlotType.FIELD;
			break;

		case 1:
			plotType = PlotType.LAND;
			break;

		case 2:
			plotType = PlotType.FOREST;
			break;

		default:
			System.out.println("WRONG PLOT TYPE INDEX PROVIDED!!!!!");
			plotType = null;
			break;
		}

		boolean isInRegulation = new Random().nextBoolean();

		plot = new Plot(EstateType.PLOT, plotType.toString(), getRandomDescription(), getRandomAddress(),
		    getRandomPrice(30000, 85000),
		    getRandomArea(), isInRegulation);

		return plot;
	}

	@Override
	public String handleToString() {
		return "Plot [isRegulated=" + isRegulated + "]";
	}

}
