package at.fhv.itb5c.ejb.interfaces;

import javax.ejb.Remote;

import at.fhv.itb5c.commons.dto.LeagueDTO;

@Remote
public interface GetByIdLeagueRemote {
	LeagueDTO getLeagueById(String sessionId, Long id);
}
