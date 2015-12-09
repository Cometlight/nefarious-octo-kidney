package at.fhv.itb5c.ejb.interfaces;

import java.util.Collection;

import javax.ejb.Remote;

import at.fhv.itb5c.commons.dto.LeagueDTO;

@Remote
public interface GetAllLeaguesRemote {
	Collection<LeagueDTO> getAllLeagues(String sessionId);
}
