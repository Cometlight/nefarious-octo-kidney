package at.fhv.itb5c.view.login;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginModel {
	
	private StringProperty _username;
	private StringProperty _password;
	
	public LoginModel() {
		_username = new SimpleStringProperty();
		_password = new SimpleStringProperty();
	}
	
	public StringProperty getUserName() {
		return _username;
	}
	
	public StringProperty getPassword() {
		return _password;
	}
}
