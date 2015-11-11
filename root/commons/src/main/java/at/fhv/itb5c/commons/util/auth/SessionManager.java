package at.fhv.itb5c.commons.util.auth;

import java.util.Hashtable;
import java.util.Set;

import at.fhv.itb5c.commons.enums.UserRole;

public class SessionManager {
	// singleton
	private static SessionManager _instance;
	private Hashtable<String, Session> _sessionTable;
	
	private SessionManager(){
		_sessionTable = new Hashtable<String, Session>();
	}

	public static SessionManager getInstance(){
		if(_instance == null){
			_instance = new SessionManager();
		}
		
		return _instance;
	}
	
	public String createNewSeesion(String userName, Set<UserRole> userRoles){
		Session session = new Session(userName, userRoles);
		_sessionTable.put(session.getSession(), session);
		return session.getSession();
	}
	
	public boolean hasRole(String sessionId, UserRole role){
		// get all roles of the session from the hash table
		Set<UserRole> roles = _sessionTable.get(sessionId).getRoles();
		
		if(roles!=null){
			return roles.contains(role);
		}
		
		return false;
	}
	
	public String getUserId(String sessionId){
		return _sessionTable.get(sessionId).getUserId();
	}
}