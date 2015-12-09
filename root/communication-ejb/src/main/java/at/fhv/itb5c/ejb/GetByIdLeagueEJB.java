package at.fhv.itb5c.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhv.itb5c.commons.dto.LeagueDTO;
import at.fhv.itb5c.ejb.interfaces.GetByIdLeagueRemote;

@Stateless
public class GetByIdLeagueEJB implements GetByIdLeagueRemote {

	@EJB private ApplicationFacadeEJBLocal applicationFacade;

	@Override
	public LeagueDTO getLeagueById(String sessionId, Long id) {
		return applicationFacade.getLeagueById(sessionId, id);
	}

}
