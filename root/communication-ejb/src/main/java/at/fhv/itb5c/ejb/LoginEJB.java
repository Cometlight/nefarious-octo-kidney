package at.fhv.itb5c.ejb;

import javax.ejb.EJB;

import at.fhv.itb5c.ejb.interfaces.LoginRemote;

public class LoginEJB implements LoginRemote {

	@EJB
	private ApplicationFacadeEJBLocal applicationFacade;
	
	@Override
	public String loginLDAP(String username, String password) {
		return applicationFacade.loginLDAP(username, password);
	}

}
