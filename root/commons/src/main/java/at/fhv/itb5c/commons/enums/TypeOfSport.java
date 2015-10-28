package at.fhv.itb5c.commons.enums;

public enum TypeOfSport {
	Soccer, Tennis;

	public static TypeOfSport fromString(String sport){
		switch (sport) {
		case "Soccer":
			return Soccer;
		case "Tennis":
			return Tennis;
		default:
			throw new IllegalArgumentException();
		}
	}
	
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
