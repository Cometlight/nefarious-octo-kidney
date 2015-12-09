package at.fhv.itb5c.ejb;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhv.itb5c.commons.dto.TournamentDTO;
import at.fhv.itb5c.ejb.interfaces.FindTournamentsRemote;

@Stateless
public class FindTournamentsEJB implements FindTournamentsRemote {

	@EJB private ApplicationFacadeEJBLocal applicationFacade;

	@Override
	public Collection<TournamentDTO> findTournaments(String sessionId, String name, Long departmentId) {
		return applicationFacade.findTournaments(sessionId, name, departmentId);
	}

}
