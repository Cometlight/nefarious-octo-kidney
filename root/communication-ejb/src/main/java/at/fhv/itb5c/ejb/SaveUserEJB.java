package at.fhv.itb5c.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhv.itb5c.commons.dto.UserDTO;
import at.fhv.itb5c.ejb.interfaces.SaveUserRemote;

@Stateless
public class SaveUserEJB implements SaveUserRemote {

	@EJB private ApplicationFacadeEJBLocal applicationFacade;

	@Override
	public UserDTO saveUser(String sessionId, UserDTO user) {
		return applicationFacade.saveUser(sessionId, user);
	}

}
