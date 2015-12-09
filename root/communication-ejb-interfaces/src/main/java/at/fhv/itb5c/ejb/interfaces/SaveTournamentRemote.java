package at.fhv.itb5c.ejb.interfaces;

import javax.ejb.Remote;

import at.fhv.itb5c.commons.dto.DepartmentDTO;
import at.fhv.itb5c.commons.dto.TournamentDTO;

@Remote
public interface SaveTournamentRemote {
	TournamentDTO saveTournament(String sessionId, TournamentDTO tournament, DepartmentDTO dept);
}
