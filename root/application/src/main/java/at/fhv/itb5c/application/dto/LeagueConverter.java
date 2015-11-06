package at.fhv.itb5c.application.dto;

import at.fhv.itb5c.commons.dto.ILeague;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.model.entity.League;

public class LeagueConverter implements ILogger {
	public static ILeague toDTO(League league) {
		if (league == null) {
			return null;
		}

		ILeague leagueDTO = new LeagueDTO();

		try {
			leagueDTO.setId(league.getId());
			leagueDTO.setVersion(league.getVersion());
			leagueDTO.setName(league.getName());
			leagueDTO.setTypeOfSport(league.getTypeOfSport());
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return leagueDTO;
	}

	public static League toEntity(ILeague leagueDTO) {
		if (leagueDTO == null) {
			return null;
		}

		League league = new League();

		try {
			league.setId(leagueDTO.getId());
			league.setVersion(league.getVersion());
			league.setName(leagueDTO.getName());
			league.setTypeOfSport(leagueDTO.getTypeOfSport());
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return league;
	}
}