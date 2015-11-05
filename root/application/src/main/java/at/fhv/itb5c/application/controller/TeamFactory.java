package at.fhv.itb5c.application.controller;

import java.util.List;
import java.util.stream.Collectors;

import at.fhv.itb5c.application.dto.TeamConverter;
import at.fhv.itb5c.application.dto.TeamDTO;
import at.fhv.itb5c.commons.dto.ITeam;
import at.fhv.itb5c.commons.dto.ITeamFactory;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.model.PersistenceFacade;
import at.fhv.itb5c.model.entity.Team;

public class TeamFactory implements ITeamFactory, ILogger {

	@Override
	public ITeam createTeam() {
		return new TeamDTO();
	}

	@Override
	public ITeam saveOrUpdate(ITeam team) {
		Team teamEntity = TeamConverter.toEntity(team);

		if (teamEntity != null) {
			try {
				teamEntity = PersistenceFacade.getInstance().saveOrUpdate(teamEntity);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}

			team = TeamConverter.toDTO(teamEntity);

			log.debug("team saved: " + team);

			return team;
		} else {
			return null;
		}
	}

	@Override
	public List<ITeam> findTeams(String name, TypeOfSport typeOfSport, Long departmentId, Long leagueId) {
		List<Team> teamEntities = PersistenceFacade.getInstance().findTeams(name, typeOfSport, departmentId, leagueId);

		log.debug(teamEntities.size() + " results found for search: name=" + name + ", typeOfSport=" + typeOfSport
				+ ", deparmentId=" + departmentId + ", leagueId=" + leagueId);

		List<ITeam> teams = teamEntities.stream().map(TeamConverter::toDTO).collect(Collectors.toList());

		return teams;
	}

}
