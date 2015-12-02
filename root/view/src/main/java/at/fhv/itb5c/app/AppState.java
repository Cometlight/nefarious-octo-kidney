package at.fhv.itb5c.app;

import java.rmi.RemoteException;
import java.util.Observable;

import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.rmi.client.RMIClient;

public class AppState extends Observable {
	private IUserRMI _user;
	private String _sessionID;
	
	private static AppState _instance;
	public static AppState getInstance() {
		if(_instance == null) {
			_instance = new AppState();
		}
		
		return _instance;
	}
	
	public IUserRMI getLoggedInUser() {
		return _user;
	}
	
	public void setSessionID(String sessionID) throws RemoteException {
		if(sessionID != null) {
			_sessionID = sessionID;
			setLoggedInUser(RMIClient.getRMIClient().getApplicationFacade().getCurrentUser(sessionID));
		}
	}
	
	public String getSessionID() {
		return _sessionID;
	}
	
	public void setLoggedInUser(IUserRMI user) {
		_user = user;
		setChanged();
		notifyObservers();
	}
}
