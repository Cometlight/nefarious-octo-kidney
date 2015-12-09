package at.fhv.itb5c.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhv.itb5c.commons.dto.MatchDTO;
import at.fhv.itb5c.ejb.interfaces.GetByIdMatchRemote;

@Stateless
public class GetByIdMatchEJB implements GetByIdMatchRemote {

	@EJB private ApplicationFacadeEJBLocal applicationFacade;

	@Override
	public MatchDTO getMatchById(String sessionId, Long matchId) {
		return applicationFacade.getMatchById(sessionId, matchId);
	}

}
