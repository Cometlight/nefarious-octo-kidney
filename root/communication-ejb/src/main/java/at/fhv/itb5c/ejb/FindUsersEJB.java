package at.fhv.itb5c.ejb;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhv.itb5c.commons.dto.UserDTO;
import at.fhv.itb5c.ejb.interfaces.FindUsersRemote;

@Stateless
public class FindUsersEJB implements FindUsersRemote {

	@EJB private ApplicationFacadeEJBLocal applicationFacade;

	@Override
	public Collection<UserDTO> findUsers(String sessionId, String firstName, String lastName, Long departmentId,
			Boolean membershipFeePaid) {
		return applicationFacade.findUsers(sessionId, firstName, lastName, departmentId, membershipFeePaid);
	}

	@Override
	public Collection<UserDTO> findUsersSimple(String sessionId, String name) {
		return applicationFacade.findUsersSimple(sessionId, name);
	}

}
