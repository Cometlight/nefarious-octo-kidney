package at.fhv.itb5c.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhv.itb5c.commons.dto.TournamentDTO;
import at.fhv.itb5c.ejb.interfaces.GetByIdTournamentRemote;

@Stateless
public class GetByIdTournamentEJB implements GetByIdTournamentRemote {

	@EJB private ApplicationFacadeEJBLocal applicationFacade;

	@Override
	public TournamentDTO getTournamentById(String sessionId, Long id) {
		return applicationFacade.getTournamentById(sessionId, id);
	}

}
