package at.fhv.itb5c.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhv.itb5c.commons.dto.DepartmentDTO;
import at.fhv.itb5c.commons.dto.TournamentDTO;
import at.fhv.itb5c.ejb.interfaces.SaveTournamentRemote;

@Stateless
public class SaveTournamentEJB implements SaveTournamentRemote {

	@EJB private ApplicationFacadeEJBLocal applicationFacade;

	@Override
	public TournamentDTO saveTournament(String sessionId, TournamentDTO tournament, DepartmentDTO dept) {
		return applicationFacade.saveTournament(sessionId, tournament, dept);
	}

}
