package at.fhv.itb5c.viewjsf;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import at.fhv.itb5c.ejb.interfaces.LoginRemote;

@ManagedBean
public class LoginBean implements Serializable{
	private static final long serialVersionUID = -7226234705816806338L;

	@EJB
	LoginRemote loginRemote;
	
	private String _userName;
	private String _password;
	
	public String getUserName() {
		return _userName;
	}
	
	public void setUserName(String userName) {
		_userName = userName;
	}
	
	public String getPassword() {
		return _password;
	}
	
	public void setPassword(String password) {
		_password = password;
	}
	
	public void login() {
		
	}
}
