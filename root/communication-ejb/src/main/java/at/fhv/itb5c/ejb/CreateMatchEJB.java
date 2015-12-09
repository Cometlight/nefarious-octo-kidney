package at.fhv.itb5c.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhv.itb5c.commons.dto.MatchDTO;
import at.fhv.itb5c.ejb.interfaces.CreateMatchRemote;

@Stateless
public class CreateMatchEJB implements CreateMatchRemote {

	@EJB private ApplicationFacadeEJBLocal applicationFacade;

	@Override
	public MatchDTO createMatch(String sessionId) {
		return applicationFacade.createMatch(sessionId);
	}

}
