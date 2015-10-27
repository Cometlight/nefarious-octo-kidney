package at.fhv.itb5c.commons.enums;

public enum Gender {
	Female, Male;

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
