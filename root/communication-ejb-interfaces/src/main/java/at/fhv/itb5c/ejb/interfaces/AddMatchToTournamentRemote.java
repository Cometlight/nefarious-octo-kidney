package at.fhv.itb5c.ejb.interfaces;

import javax.ejb.Remote;

import at.fhv.itb5c.commons.dto.MatchDTO;
import at.fhv.itb5c.commons.dto.TournamentDTO;

@Remote
public interface AddMatchToTournamentRemote {
	TournamentDTO addMatchToTournament(String sessionId, TournamentDTO tournament, MatchDTO match);
}
