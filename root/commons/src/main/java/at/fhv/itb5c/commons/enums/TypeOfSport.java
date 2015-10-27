package at.fhv.itb5c.commons.enums;

public enum TypeOfSport {
	Soccer, Tennis;

	@Override
	public String toString() {
		switch (this) {
		case Soccer:
			return "Soccer";
		case Tennis:
			return "Tennis";
		default:
			throw new IllegalArgumentException();
		}
	}
}
