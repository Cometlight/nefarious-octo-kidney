package at.fhv.itb5c.ejb.interfaces;

import javax.ejb.Remote;

import at.fhv.itb5c.commons.dto.MatchDTO;

@Remote
public interface GetByIdMatchRemote {
	MatchDTO getMatchById(String sessionId, Long matchId);
}
