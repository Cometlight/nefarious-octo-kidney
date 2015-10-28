package at.fhv.itb5c.commons.dto;

public interface IUserFactory {
	public IUser createUser() throws Exception;
	public void save(IUser user) throws Exception;
}