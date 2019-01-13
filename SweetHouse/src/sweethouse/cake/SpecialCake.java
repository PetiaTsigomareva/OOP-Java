package sweethouse.cake;

import java.util.Random;

public class SpecialCake extends Cake {

	public enum Kind {
		ANNIVERSARY, ADVERTISING, COMPANY;

		public static Kind getRandomKind() {
			return values()[new Random().nextInt(values().length)];
		}
	}

	private String eventName;
	private Kind kind;

	public SpecialCake(String name, String description, double price, int pieces, Kind kind, String event) {
		super(Type.SPECIAL, name, description, price, pieces);
		this.eventName = event;
		this.kind = kind;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
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
		result = prime * result + ( ( eventName == null ) ? 0 : eventName.hashCode() );
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
		SpecialCake other = (SpecialCake) obj;
		if (eventName == null) {
			if (other.eventName != null) {
				return false;
			}
		} else if (!eventName.equals(other.eventName)) {
			return false;
		}
		if (kind != other.kind) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return Type.SPECIAL + ", " + kind + ", " + super.toString() + ", " + eventName;
	}

}
