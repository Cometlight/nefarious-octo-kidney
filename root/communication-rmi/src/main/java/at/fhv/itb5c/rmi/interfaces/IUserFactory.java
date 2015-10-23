package at.fhv.itb5c.rmi.interfaces;

import java.rmi.Remote;

public interface IUserFactory extends Remote {
	public IUser createUser(String firstName);
}