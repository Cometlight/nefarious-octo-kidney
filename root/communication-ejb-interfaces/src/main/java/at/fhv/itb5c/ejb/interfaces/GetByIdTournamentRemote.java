package at.fhv.itb5c.ejb.interfaces;

import javax.ejb.Remote;

import at.fhv.itb5c.commons.dto.TournamentDTO;

@Remote
public interface GetByIdTournamentRemote {
	TournamentDTO getTournamentById(String sessionId, Long id);
}
