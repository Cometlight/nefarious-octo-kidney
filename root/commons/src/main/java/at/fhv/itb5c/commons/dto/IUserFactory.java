package at.fhv.itb5c.commons.dto;

import java.rmi.Remote;

public interface IUserFactory extends Remote {
	public IUser createUser();
	public void save(IUser user);
}