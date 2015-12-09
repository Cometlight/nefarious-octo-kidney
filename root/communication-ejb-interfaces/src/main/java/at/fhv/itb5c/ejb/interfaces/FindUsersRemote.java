package at.fhv.itb5c.ejb.interfaces;

import java.util.Collection;

import javax.ejb.Remote;

import at.fhv.itb5c.commons.dto.UserDTO;

@Remote
public interface FindUsersRemote {
	Collection<UserDTO> findUsers(String sessionId, String firstName, String lastName, Long departmentId, Boolean membershipFeePaid);
	Collection<UserDTO> findUsersSimple(String sessionId, String name);
}
