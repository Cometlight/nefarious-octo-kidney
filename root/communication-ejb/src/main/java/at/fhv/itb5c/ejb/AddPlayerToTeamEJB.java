package at.fhv.itb5c.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhv.itb5c.commons.dto.TeamDTO;
import at.fhv.itb5c.commons.dto.UserDTO;
import at.fhv.itb5c.ejb.interfaces.AddPlayerToTeamRemote;

@Stateless
public class AddPlayerToTeamEJB implements AddPlayerToTeamRemote {

	@EJB private ApplicationFacadeEJBLocal applicationFacade;

	@Override
	public TeamDTO addPlayerToTeam(String sessionId, TeamDTO team, UserDTO player) {
		return applicationFacade.addPlayerToTeam(sessionId, team, player);
	}

}
