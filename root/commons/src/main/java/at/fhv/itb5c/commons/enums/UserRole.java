package at.fhv.itb5c.commons.enums;

public enum UserRole {
	Admin;
	
	@Override
	public String toString() {
		switch (this) {
		case Admin:
			return "Admin";
		default:
			throw new IllegalArgumentException();
		}
	}
}
