package at.fhv.itb5c.rmi.server;

import java.util.Collection;

import at.fhv.itb5c.commons.dto.rmi.IUserRMI;

public interface IApplicationFacadeRMI {
	IUserRMI createUser();
	Collection<IUserRMI> findUsers(String firstName, String lastName, Long departmentId, Boolean membershipFeePaid);
	Collection<IUserRMI> findUsersSimple(String name);
	IUserRMI saveUser(IUserRMI user);
}
