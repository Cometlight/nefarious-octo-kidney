package at.fhv.itb5c.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhv.itb5c.commons.dto.TeamDTO;
import at.fhv.itb5c.ejb.interfaces.GetByIdTeamRemote;

@Stateless
public class GetByIdTeamEJB implements GetByIdTeamRemote {

	@EJB private ApplicationFacadeEJBLocal applicationFacade;

	@Override
	public TeamDTO getTeamById(String sessionId, Long id) {
		return applicationFacade.getTeamById(sessionId, id);
	}

}
