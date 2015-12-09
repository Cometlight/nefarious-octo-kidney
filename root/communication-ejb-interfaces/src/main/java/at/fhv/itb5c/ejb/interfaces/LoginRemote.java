package at.fhv.itb5c.ejb.interfaces;

import javax.ejb.Remote;

@Remote
public interface LoginRemote {
	String loginLDAP(String username, String password);
}
