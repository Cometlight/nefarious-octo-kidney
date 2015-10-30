package at.fhv.itb5c.commons.dto;

import java.util.List;

//Remote interface for UserFactory
public interface IUserFactory {
	public IUser createUser() throws Exception;
	public IUser save(IUser user) throws Exception;
	public List<? extends IUser> findUsers(String firstName, String lastName, Long departmentId, Boolean membershipFeePaid) throws Exception;
}