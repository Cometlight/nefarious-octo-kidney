package at.fhv.itb5c.commons.enums;

public enum Gender {
	Female, Male;

	public static Gender fromString(String gender){
		switch (gender) {
		case "Female":
			return Female;
		case "Male":
			return Male;
		default:
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public String toString() {
		switch (this) {
		case Female:
			return "Female";
		case Male:
			return "Male";
		default:
			throw new IllegalArgumentException();
		}
	}
}
