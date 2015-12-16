package at.fhv.itb5c.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhv.itb5c.ejb.interfaces.LoginRemote;
import at.fhv.itb5c.logging.ILogger;

@Stateless
public class LoginEJB implements LoginRemote, ILogger {

	@EJB
	private ApplicationFacadeEJBLocal applicationFacade;
	
	@Override
	public String loginLDAP(String username, String password) {
		log.debug("login request for " + username);
		return applicationFacade.loginLDAP(username, password);
	}

}
