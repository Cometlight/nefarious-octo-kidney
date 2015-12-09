package at.fhv.itb5c.ejb.interfaces;

import javax.ejb.Remote;

import at.fhv.itb5c.commons.dto.DepartmentDTO;
import at.fhv.itb5c.commons.dto.MatchDTO;

@Remote
public interface SaveMatchRemote {
	MatchDTO saveMatch(String sessionId, MatchDTO match, DepartmentDTO dept);
}
