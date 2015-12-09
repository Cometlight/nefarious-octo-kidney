package at.fhv.itb5c.ejb.interfaces;

import java.util.Collection;

import javax.ejb.Remote;

import at.fhv.itb5c.commons.dto.TournamentDTO;

@Remote
public interface FindTournamentsRemote {
	Collection<TournamentDTO> findTournaments(String sessionId, String name, Long departmentId);
}
