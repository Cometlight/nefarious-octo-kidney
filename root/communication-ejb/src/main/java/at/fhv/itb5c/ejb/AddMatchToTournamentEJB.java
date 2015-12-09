package at.fhv.itb5c.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhv.itb5c.commons.dto.MatchDTO;
import at.fhv.itb5c.commons.dto.TournamentDTO;
import at.fhv.itb5c.ejb.interfaces.AddMatchToTournamentRemote;

@Stateless
public class AddMatchToTournamentEJB implements AddMatchToTournamentRemote {

	@EJB private ApplicationFacadeEJBLocal applicationFacade;

	@Override
	public TournamentDTO addMatchToTournament(String sessionId, TournamentDTO tournament, MatchDTO match) {
		return applicationFacade.addMatchToTournament(sessionId, tournament, match);
	}

}
