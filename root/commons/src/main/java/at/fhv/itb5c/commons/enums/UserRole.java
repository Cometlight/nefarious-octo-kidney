package at.fhv.itb5c.commons.enums;

public enum UserRole {
	Admin, StandardUser;
	
	public static UserRole fromString(String role){
		switch (role) {
		case "Admin":
			return Admin;
		case "StandardUser":
			return StandardUser;
		default:
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public String toString() {
		switch (this) {
		case Admin:
			return "Admin";
		case StandardUser:
			return "StandardUser";
		default:
			throw new IllegalArgumentException();
		}
	}
}
