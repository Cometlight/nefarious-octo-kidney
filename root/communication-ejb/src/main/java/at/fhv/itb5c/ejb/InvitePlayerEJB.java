package at.fhv.itb5c.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhv.itb5c.commons.dto.TeamDTO;
import at.fhv.itb5c.commons.dto.TournamentDTO;
import at.fhv.itb5c.commons.dto.UserDTO;
import at.fhv.itb5c.ejb.interfaces.InvitePlayerRemote;

@Stateless
public class InvitePlayerEJB implements InvitePlayerRemote {

	@EJB private ApplicationFacadeEJBLocal applicationFacade;

	@Override
	public void invitePlayer(String sessionId, UserDTO player, TeamDTO team, TournamentDTO tournament) {
		applicationFacade.invitePlayer(sessionId, player, team, tournament);
	}

}
