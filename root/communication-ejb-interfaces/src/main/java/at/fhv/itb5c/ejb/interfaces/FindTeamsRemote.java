package at.fhv.itb5c.ejb.interfaces;

import java.util.Collection;

import javax.ejb.Remote;

import at.fhv.itb5c.commons.dto.TeamDTO;
import at.fhv.itb5c.commons.enums.TypeOfSport;

@Remote
public interface FindTeamsRemote {
	Collection<TeamDTO> findTeams(String sessionId, String name, TypeOfSport typeOfSport, Long departmentId, Long leagueId, Long coachId);
}
