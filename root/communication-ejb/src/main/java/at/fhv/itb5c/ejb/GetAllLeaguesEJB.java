package at.fhv.itb5c.ejb;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhv.itb5c.commons.dto.LeagueDTO;
import at.fhv.itb5c.ejb.interfaces.GetAllLeaguesRemote;

@Stateless
public class GetAllLeaguesEJB implements GetAllLeaguesRemote {

	@EJB private ApplicationFacadeEJBLocal applicationFacade;

	@Override
	public Collection<LeagueDTO> getAllLeagues(String sessionId) {
		return applicationFacade.getAllLeagues(sessionId);
	}

}
