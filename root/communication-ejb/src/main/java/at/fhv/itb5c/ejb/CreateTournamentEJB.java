package at.fhv.itb5c.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhv.itb5c.commons.dto.DepartmentDTO;
import at.fhv.itb5c.commons.dto.TournamentDTO;
import at.fhv.itb5c.ejb.interfaces.CreateTournamentRemote;

@Stateless
public class CreateTournamentEJB implements CreateTournamentRemote {

	@EJB private ApplicationFacadeEJBLocal applicationFacade;

	@Override
	public TournamentDTO createTournament(String sessionId, DepartmentDTO dept) {
		return applicationFacade.createTournament(sessionId, dept);
	}

}
