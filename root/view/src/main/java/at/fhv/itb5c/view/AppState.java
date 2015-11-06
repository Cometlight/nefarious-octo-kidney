package at.fhv.itb5c.view;

import java.util.Observable;

import at.fhv.itb5c.commons.dto.rmi.IUserRMI;

public class AppState extends Observable {
	public IUserRMI _user;
	
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
	
	public void setLoggedInUser(IUserRMI user) {
		_user = user;
		setChanged();
		notifyObservers();
	}
}
