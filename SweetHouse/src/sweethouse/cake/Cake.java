package sweethouse.cake;

public class Cake {
	public enum Type {
		STANDART, SPECIAL, WEDDING, CHILDREN
	}

	private Type type;
	private String name;
	private String description;
	private double price;
	private int pieces;

	public Cake(Type type, String name, String description, double price, int pieces) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.pieces = pieces;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getPieces() {
		return pieces;
	}

	public void setPieces(int pieces) {
		this.pieces = pieces;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( description == null ) ? 0 : description.hashCode() );
		result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
		result = prime * result + pieces;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) ( temp ^ ( temp >>> 32 ) );
		result = prime * result + ( ( type == null ) ? 0 : type.hashCode() );
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Cake other = (Cake) obj;
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (pieces != other.pieces) {
			return false;
		}
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price)) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return name + ", " + description + ", " + price + ", " + pieces;
	}
}
