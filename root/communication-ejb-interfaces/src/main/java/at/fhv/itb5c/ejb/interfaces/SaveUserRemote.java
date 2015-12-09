package at.fhv.itb5c.ejb.interfaces;

import javax.ejb.Remote;

import at.fhv.itb5c.commons.dto.UserDTO;

@Remote
public interface SaveUserRemote {
	UserDTO saveUser(String sessionId, UserDTO user);
}
