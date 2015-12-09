package at.fhv.itb5c.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhv.itb5c.commons.dto.TeamDTO;
import at.fhv.itb5c.ejb.interfaces.CreateTeamRemote;

@Stateless
public class CreateTeamEJB implements CreateTeamRemote {

	@EJB private ApplicationFacadeEJBLocal applicationFacade;

	@Override
	public TeamDTO createTeam(String sessionId) {
		return applicationFacade.createTeam(sessionId);
	}

}
