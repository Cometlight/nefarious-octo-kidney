package at.fhv.itb5c.viewjsf;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import at.fhv.itb5c.ejb.interfaces.LoginRemote;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{
	private static final long serialVersionUID = -7226234705816806338L;

	@EJB
	LoginRemote loginRemote;
	
	private String _userName;
	private String _password;
	private String _sessionId = "webservice_request_session";
	
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
	
	public String getSessionId() {
		return _sessionId;
	}

	public void setSessionId(String sessionId) {
		_sessionId = sessionId;
	}

	public void login() {
		_sessionId = loginRemote.loginLDAP(_userName, _password);
	}
}
