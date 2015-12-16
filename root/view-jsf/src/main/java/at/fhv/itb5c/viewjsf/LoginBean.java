package at.fhv.itb5c.viewjsf;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import at.fhv.itb5c.commons.dto.DepartmentDTO;
import at.fhv.itb5c.commons.enums.UserRole;
import at.fhv.itb5c.ejb.interfaces.GetByIdDepartmentRemote;
import at.fhv.itb5c.ejb.interfaces.GetByIdUserRemote;
import at.fhv.itb5c.ejb.interfaces.LoginRemote;
import at.fhv.itb5c.ejb.interfaces.SessionHelperRemote;

@ManagedBean(name = "loginBean", eager = true)
@SessionScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = -7226234705816806338L;

	@EJB
	LoginRemote loginRemote;

	@EJB
	GetByIdDepartmentRemote getDepartmentById;

	@EJB
	GetByIdUserRemote getUserById;

	@EJB
	SessionHelperRemote sessionHelper;

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

	public void redirectToLogin(Long departmentId) {
		DepartmentDTO department = getDepartmentById.getDepartmentById(_sessionId, departmentId);
		if (!(sessionHelper.isCoach(_sessionId, department) || sessionHelper.isDepartmentHead(_sessionId, department)
				|| sessionHelper.hasRole(_sessionId, UserRole.Admin))
				|| _sessionId.equals("webservice_request_session")) {
			FacesContext fc = FacesContext.getCurrentInstance();

			ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication()
					.getNavigationHandler();

			nav.performNavigation("login");
		}
	}

	public String login() {
		String sessionId = loginRemote.loginLDAP(_userName, _password);
		if (sessionId != null) {
			_sessionId = sessionId;
		}
		return "tournaments";
	}
}
