package at.fhv.itb5c.ejb.interfaces;

import javax.ejb.Remote;

import at.fhv.itb5c.commons.dto.TeamDTO;

@Remote
public interface GetByIdTeamRemote {
	TeamDTO getTeamById(String sessionId, Long id);
}
