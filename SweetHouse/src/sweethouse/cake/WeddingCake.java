package sweethouse.cake;

import java.util.Random;

public class WeddingCake extends Cake {

	public enum Kind {
		BIG, LITTLE, MEDIUM;

		public static Kind getRandomKind() {
			return values()[new Random().nextInt(values().length)];
		}
	}

	private int levelCount;
	private Kind kind;

	public WeddingCake(String name, String description, double price, int pieces, Kind kind, int level) {
		super(Type.WEDDING, name, description, price, pieces);
		this.levelCount = level;
		this.kind = kind;
	}

	public int getLevelCount() {
		return levelCount;
	}

	public void setLevelCount(int levelCount) {
		this.levelCount = levelCount;
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
		result = prime * result + ( ( kind == null ) ? 0 : kind.hashCode() );
		result = prime * result + levelCount;
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
		WeddingCake other = (WeddingCake) obj;
		if (kind != other.kind) {
			return false;
		}
		if (levelCount != other.levelCount) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return Type.WEDDING + ",  " + kind + ", " + super.toString() + ", " + levelCount;
	}

}
