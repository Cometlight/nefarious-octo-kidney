package at.fhv.itb5c.app;

import java.util.Observable;
import at.fhv.itb5c.application.dto.UserDTO;
import at.fhv.itb5c.communication.CommunicationErrorException;
import at.fhv.itb5c.communication.CommunicationFacadeProvider;

public class AppState extends Observable {
	private UserDTO _user;
	private String _sessionID;
	
	private static AppState _instance;
	public static AppState getInstance() {
		if(_instance == null) {
			_instance = new AppState();
		}
		
		return _instance;
	}
	
	public UserDTO getLoggedInUser() {
		return _user;
	}
	
	public void setSessionID(String sessionID) throws CommunicationErrorException {
		if(sessionID != null) {
			_sessionID = sessionID;
			setLoggedInUser(CommunicationFacadeProvider.getInstance().getCurrentFacade().getCurrentUser(sessionID));
		}
	}
	
	public String getSessionID() {
		return _sessionID;
	}
	
	public void setLoggedInUser(UserDTO user) {
		_user = user;
		setChanged();
		notifyObservers();
	}
}
