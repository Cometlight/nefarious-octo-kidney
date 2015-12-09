package at.fhv.itb5c.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhv.itb5c.commons.dto.TeamDTO;
import at.fhv.itb5c.ejb.interfaces.SaveTeamRemote;

@Stateless
public class SaveTeamEJB implements SaveTeamRemote {

	@EJB private ApplicationFacadeEJBLocal applicationFacade;

	@Override
	public TeamDTO saveTeam(String sessionId, TeamDTO team) {
		return applicationFacade.saveTeam(sessionId, team);
	}

}
