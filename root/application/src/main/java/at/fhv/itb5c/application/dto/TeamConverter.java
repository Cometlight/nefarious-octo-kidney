package at.fhv.itb5c.application.dto;

import java.util.Set;
import java.util.stream.Collectors;

import at.fhv.itb5c.commons.dto.ITeam;
import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.model.entity.Team;
import at.fhv.itb5c.model.entity.User;

public class TeamConverter implements ILogger {
	public static ITeam toDTO(Team team) {
		if (team == null) {
			return null;
		}

		ITeam teamDTO = new TeamDTO();

		try {
			teamDTO.setId(team.getId());
			teamDTO.setVersion(team.getVersion());
			teamDTO.setName(team.getName());
			teamDTO.setTypeOfSport(team.getTypeOfSport());
			teamDTO.setDepartment(DepartmentConverter.toDTO(team.getDepartment()));
			teamDTO.setCoach(UserConverter.toDTO(team.getCoach()));
			teamDTO.setLeague(LeagueConverter.toDTO(team.getLeague()));

			// Converts each user in the set to an IUser by utilizing the
			// UserConverter
			Set<IUser> members = team.getMembers().stream().map(UserConverter::toDTO).collect(Collectors.toSet());
			teamDTO.setMembers(members);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return teamDTO;
	}

	public static Team toEntity(ITeam iteam) {
		if (iteam == null) {
			return null;
		}

		Team team = new Team();

		try {
			team.setId(iteam.getId());
			team.setVersion(iteam.getId());
			team.setName(iteam.getName());
			team.setTypeOfSport(iteam.getTypeOfSport());
			team.setDepartment(DepartmentConverter.toEntity(iteam.getDepartment()));
			team.setCoach(UserConverter.toEntity(iteam.getCoach()));
			team.setLeague(LeagueConverter.toEntity(iteam.getLeague()));

			Set<User> members = iteam.getMembers().stream().map(UserConverter::toEntity).collect(Collectors.toSet());
			team.setMembers(members);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return team;
	}
}
