package at.fhv.itb5c.commons.dto;

import java.util.List;

import at.fhv.itb5c.commons.enums.TypeOfSport;

public interface ITeamFactory {
	public ITeam createTeam() throws Exception;
	public ITeam saveOrUpdate(ITeam team) throws Exception;
	public List<? extends ITeam> findTeams(String name, TypeOfSport typeOfSport, Long departmentId, Long leagueId) throws Exception;
}
