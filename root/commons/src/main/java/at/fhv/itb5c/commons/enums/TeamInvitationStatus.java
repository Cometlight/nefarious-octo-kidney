package at.fhv.itb5c.commons.enums;

public enum TeamInvitationStatus {
	Invited("Invited"),
	Accepted("Accepted"),
	Declined("Declined");
	
	private String _name;
	
	private TeamInvitationStatus(String name) {
		_name = name;
	}
	
	public static TeamInvitationStatus fromString(String name) {
		if(name != null) {
			for(TeamInvitationStatus status : TeamInvitationStatus.values()) {
				if(name.equalsIgnoreCase(status.toString())) {
					return status;
				}
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return _name;
	}
}
