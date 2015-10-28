package at.fhv.itb5c.commons.dto;

public interface IUserFactory {
	public IUser createUser();
	public void save(IUser user);
}