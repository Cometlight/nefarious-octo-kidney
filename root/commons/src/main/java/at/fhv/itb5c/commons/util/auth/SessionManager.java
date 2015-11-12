package at.fhv.itb5c.commons.util.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import at.fhv.itb5c.commons.enums.UserRole;

public class SessionManager {
	// singleton
	private static SessionManager _instance;
	private Map<String, Session> _sessionTable;

	private SessionManager() {
		_sessionTable = new HashMap<>();
	}

	public static SessionManager getInstance() {
		if (_instance == null) {
			_instance = new SessionManager();
		}

		return _instance;
	}

	public String createNewSession(Long userId, Set<UserRole> userRoles) {
		Session session = new Session(userId, userRoles);
		_sessionTable.put(session.getSessionId(), session);
		return session.getSessionId();
	}

	public boolean hasRole(String sessionId, UserRole role) {
		// get all roles of the session from the hash table
		Session session = _sessionTable.get(sessionId);
		if (session != null) {
			Set<UserRole> roles = session.getRoles();

			if (roles != null) {
				return roles.contains(role);
			}
		}

		return false;
	}

	public Long getUserId(String sessionId) {
		Session session = _sessionTable.get(sessionId);
		if (session != null) {
			return session.getUserId();
		}
		return null;
	}
}