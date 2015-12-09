package at.fhv.itb5c.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhv.itb5c.commons.dto.DepartmentDTO;
import at.fhv.itb5c.commons.dto.MatchDTO;
import at.fhv.itb5c.ejb.interfaces.SaveMatchRemote;

@Stateless
public class SaveMatchEJB implements SaveMatchRemote {

	@EJB private ApplicationFacadeEJBLocal applicationFacade;

	@Override
	public MatchDTO saveMatch(String sessionId, MatchDTO match, DepartmentDTO dept) {
		return applicationFacade.saveMatch(sessionId, match, dept);
	}

}
