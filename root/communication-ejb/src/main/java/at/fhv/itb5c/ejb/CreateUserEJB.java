package at.fhv.itb5c.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhv.itb5c.commons.dto.UserDTO;
import at.fhv.itb5c.ejb.interfaces.CreateUserRemote;

@Stateless
public class CreateUserEJB implements CreateUserRemote {

	@EJB private ApplicationFacadeEJBLocal applicationFacade;

	@Override
	public UserDTO createUser(String sessionId) {
		return applicationFacade.createUser(sessionId);
	}

}
