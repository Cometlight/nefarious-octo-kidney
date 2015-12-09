package at.fhv.itb5c.ejb;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhv.itb5c.commons.dto.TeamDTO;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.ejb.interfaces.FindTeamsRemote;

@Stateless
public class FindTeamsEJB implements FindTeamsRemote {

	@EJB private ApplicationFacadeEJBLocal applicationFacade;

	@Override
	public Collection<TeamDTO> findTeams(String sessionId, String name, TypeOfSport typeOfSport, Long departmentId, Long leagueId,
			Long coachId) {
		return applicationFacade.findTeams(sessionId, name, typeOfSport, departmentId, leagueId, coachId);
	}

}
