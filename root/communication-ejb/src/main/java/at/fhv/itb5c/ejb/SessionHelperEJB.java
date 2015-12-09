package at.fhv.itb5c.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhv.itb5c.commons.dto.DepartmentDTO;
import at.fhv.itb5c.commons.dto.MessageDTO;
import at.fhv.itb5c.commons.dto.TeamDTO;
import at.fhv.itb5c.commons.dto.UserDTO;
import at.fhv.itb5c.commons.enums.UserRole;
import at.fhv.itb5c.ejb.interfaces.SessionHelperRemote;

@Stateless
public class SessionHelperEJB implements SessionHelperRemote {

	@EJB private ApplicationFacadeEJBLocal applicationFacade;

	@Override
	public Boolean hasRole(String sessionId, UserRole... roles) {
		return applicationFacade.hasRole(sessionId, roles);
	}

	@Override
	public boolean isCoach(String sessionId, DepartmentDTO dept) {
		return applicationFacade.isCoach(sessionId, dept);
	}

	@Override
	public Boolean isCoach(String sessionId, TeamDTO team) {
		return applicationFacade.isCoach(sessionId, team);
	}

	@Override
	public Boolean isDepartmentHead(String sessionId, DepartmentDTO dept) {
		return applicationFacade.isDepartmentHead(sessionId, dept);
	}

	@Override
	public MessageDTO getMessage(String sessionId) {
		return applicationFacade.getMessage(sessionId);
	}

	@Override
	public UserDTO getCurrentUser(String sessionId) {
		return applicationFacade.getCurrentUser(sessionId);
	}

}
