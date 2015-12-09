package at.fhv.itb5c.ejb.interfaces;

import javax.ejb.Remote;

import at.fhv.itb5c.commons.dto.TeamDTO;
import at.fhv.itb5c.commons.enums.TeamInvitationStatus;

@Remote
public interface RSVPRemote {
	Boolean rsvp(String sessionId, TeamDTO team, TeamInvitationStatus answer);
}
