package at.fhv.itb5c.commons.util.auth;

import java.util.Set;
import java.util.UUID;

import at.fhv.itb5c.commons.enums.UserRole;

public class Session {
	private String _session;
	private Long _userId;
	private Set<UserRole> _roles;

	public Session(Long userId, Set<UserRole> roles) {
		super();
		_userId = userId;
		_roles = roles;
		_session = UUID.randomUUID().toString();
	}

	public Long getUserId() {
		return _userId;
	}

	public void setUserId(Long userId) {
		_userId = userId;
	}

	public Set<UserRole> getRoles() {
		return _roles;
	}

	public void setRoles(Set<UserRole> roles) {
		_roles = roles;
	}

	public String getSession() {
		return _session;
	}

	public void setSession(String session) {
		_session = session;
	}

}
