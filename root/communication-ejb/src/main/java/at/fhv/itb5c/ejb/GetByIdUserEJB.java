package at.fhv.itb5c.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhv.itb5c.commons.dto.UserDTO;
import at.fhv.itb5c.ejb.interfaces.GetByIdUserRemote;

@Stateless
public class GetByIdUserEJB implements GetByIdUserRemote {

	@EJB private ApplicationFacadeEJBLocal applicationFacade;

	@Override
	public UserDTO getUserById(String sessionId, Long id) {
		return applicationFacade.getUserById(sessionId, id);
	}

}
