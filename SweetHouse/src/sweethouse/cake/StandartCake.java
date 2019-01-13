package sweethouse.cake;

import java.util.Random;

public class StandartCake extends Cake {

	public enum Kind {
		BISCUITS, ECLER, FRUIT, CHOCOLATE;

		public static Kind getRandomKind() {
			return values()[new Random().nextInt(values().length)];
		}

	}

	private Kind kind;

	private boolean isSyrupe;

	public StandartCake(String name, String description, double price, int pieces, Kind kind, boolean isSyrupe) {
		super(Type.STANDART, name, description, price, pieces);
		this.kind = kind;
		this.isSyrupe = isSyrupe;
	}

	public boolean isSyrupe() {
		return isSyrupe;
	}

	public void setSyrupe(boolean isSyrupe) {
		this.isSyrupe = isSyrupe;
	}

	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( isSyrupe ? 1231 : 1237 );
		result = prime * result + ( ( kind == null ) ? 0 : kind.hashCode() );
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		StandartCake other = (StandartCake) obj;
		if (isSyrupe != other.isSyrupe) {
			return false;
		}
		if (kind != other.kind) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return Type.STANDART + ", " + kind + ", " + super.toString() + ", " + isSyrupe;
	}

}
