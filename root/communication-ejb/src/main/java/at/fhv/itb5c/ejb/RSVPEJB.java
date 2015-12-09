package at.fhv.itb5c.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhv.itb5c.commons.dto.TeamDTO;
import at.fhv.itb5c.commons.enums.TeamInvitationStatus;
import at.fhv.itb5c.ejb.interfaces.RSVPRemote;

@Stateless
public class RSVPEJB implements RSVPRemote {

	@EJB private ApplicationFacadeEJBLocal applicationFacade;

	@Override
	public Boolean rsvp(String sessionId, TeamDTO team, TeamInvitationStatus answer) {
		return applicationFacade.rsvp(sessionId, team, answer);
	}

}
