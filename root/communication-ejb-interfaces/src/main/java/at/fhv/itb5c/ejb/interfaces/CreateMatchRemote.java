package at.fhv.itb5c.ejb.interfaces;

import javax.ejb.Remote;

import at.fhv.itb5c.commons.dto.MatchDTO;

@Remote
public interface CreateMatchRemote {
	MatchDTO createMatch(String sessionId);
}
