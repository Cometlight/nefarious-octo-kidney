package at.fhv.itb5c.ejb.interfaces;

import javax.ejb.Remote;

import at.fhv.itb5c.commons.dto.DepartmentDTO;
import at.fhv.itb5c.commons.dto.MessageDTO;
import at.fhv.itb5c.commons.dto.TeamDTO;
import at.fhv.itb5c.commons.dto.UserDTO;
import at.fhv.itb5c.commons.enums.UserRole;

@Remote
public interface SessionHelperRemote {
	UserDTO getCurrentUser(String sessionId);
	Boolean hasRole(String sessionId, UserRole... roles);
	boolean isCoach(String sessionId, DepartmentDTO dept);
	Boolean isCoach(String sessionId, TeamDTO team);
	Boolean isDepartmentHead(String sessionId, DepartmentDTO dept);
	MessageDTO getMessage(String sessionId);
}
