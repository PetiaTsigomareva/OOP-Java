package sweethouse.cake;

import java.util.Random;

public class ChildrenCake extends Cake {

	public enum Kind {
		BIRTH_DAY, KRYSHTENE, PROSHTYPULNIC;

		public static Kind getRandomKind() {
			return values()[new Random().nextInt(values().length)];
		}
	}

	private String childrenName;

	private Kind kind;

	public ChildrenCake(String name, String description, double price, int pieces, Kind kind,
	    String childrenName) {
		super(Type.CHILDREN, name, description, price, pieces);
		this.childrenName = childrenName;
		this.kind = kind;
	}

	public String getChildrenName() {
		return childrenName;
	}

	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}

	public void setChildrenName(String childrenName) {
		this.childrenName = childrenName;
	}

	@Override
	public String toString() {
		return Type.CHILDREN + ", " + kind + ", " + super.toString() + ", " + childrenName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ( ( childrenName == null ) ? 0 : childrenName.hashCode() );
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
		ChildrenCake other = (ChildrenCake) obj;
		if (childrenName == null) {
			if (other.childrenName != null) {
				return false;
			}
		} else if (!childrenName.equals(other.childrenName)) {
			return false;
		}
		if (kind != other.kind) {
			return false;
		}
		return true;
	}

}
