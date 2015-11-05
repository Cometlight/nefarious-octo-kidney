package at.fhv.itb5c.rmi.server;

import java.util.Set;
import java.util.stream.Collectors;

import at.fhv.itb5c.application.dto.TeamDTO;
import at.fhv.itb5c.commons.dto.ITeam;
import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.logging.ILogger;

public class TeamConverterRMI implements ILogger {
	@SuppressWarnings("unchecked")
	public static ITeamRMI toRMI(ITeam iteam) {
		if (iteam == null) {
			return null;
		}

		ITeamRMI teamRMI = null;
		try {
			teamRMI = new TeamRMI();
			teamRMI.setId(iteam.getId());
			teamRMI.setVersion(iteam.getId());
			teamRMI.setName(iteam.getName());
			teamRMI.setTypeOfSport(iteam.getTypeOfSport());
			teamRMI.setDepartment(iteam.getDepartment());
			teamRMI.setCoach(iteam.getCoach());
			teamRMI.setLeague(iteam.getLeague());
			teamRMI.setMembers((Set<IUser>) iteam.getMembers());
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return teamRMI;
	}

	public static ITeam toDTO(ITeamRMI teamRMI) {
		ITeam iteam = new TeamDTO();

		if (teamRMI != null) {
			try {
				iteam.setId(teamRMI.getId());
				iteam.setVersion(teamRMI.getId());
				iteam.setName(teamRMI.getName());
				iteam.setTypeOfSport(teamRMI.getTypeOfSport());
				iteam.setDepartment(teamRMI.getDepartment());
				iteam.setCoach(teamRMI.getCoach());
				iteam.setLeague(teamRMI.getLeague());
				Set<IUser> user = teamRMI.getMembers().stream().map(UserConverterRMI::toDTO).collect(Collectors.toSet());
				iteam.setMembers(user);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		}

		return iteam;
	}
}
