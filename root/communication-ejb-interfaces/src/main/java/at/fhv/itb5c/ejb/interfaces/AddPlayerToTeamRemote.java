package at.fhv.itb5c.ejb.interfaces;

import javax.ejb.Remote;

import at.fhv.itb5c.commons.dto.TeamDTO;
import at.fhv.itb5c.commons.dto.UserDTO;

@Remote
public interface AddPlayerToTeamRemote {
	TeamDTO addPlayerToTeam(String sessionId, TeamDTO team, UserDTO player);
}
