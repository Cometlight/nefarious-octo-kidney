package at.fhv.itb5c.ejb.interfaces;

import javax.ejb.Remote;

import at.fhv.itb5c.commons.dto.TeamDTO;
import at.fhv.itb5c.commons.dto.TournamentDTO;
import at.fhv.itb5c.commons.dto.UserDTO;

@Remote
public interface InvitePlayerRemote {
	void invitePlayer(String sessionId, UserDTO player, TeamDTO team, TournamentDTO tournament);
}
