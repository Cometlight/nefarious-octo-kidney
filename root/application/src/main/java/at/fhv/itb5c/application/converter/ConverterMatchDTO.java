package at.fhv.itb5c.application.converter;

import java.util.Collection;
import java.util.stream.Collectors;

import at.fhv.itb5c.commons.dto.MatchDTO;
import at.fhv.itb5c.model.entity.Match;

public class ConverterMatchDTO {
	public static MatchDTO toDTO(Match match) {
		if (match == null) {
			return null;
		}

		MatchDTO matchdto = new MatchDTO();

		matchdto.setId(match.getId());
		matchdto.setVersion(match.getVersion());
		matchdto.setStartDate(match.getStartDate());
		matchdto.setTeamOne(match.getTeamOne());
		matchdto.setTeamTwo(match.getTeamTwo());
		matchdto.setResultTeamOne(match.getResultTeamOne());
		matchdto.setResultTeamTwo(match.getResultTeamTwo());

		return matchdto;
	}

	public static Collection<MatchDTO> toDTO(Collection<Match> entities) {
		if (entities == null) {
			return null;
		}

		return entities.stream().map(ConverterMatchDTO::toDTO).collect(Collectors.toList());
	}

	public static Match toEntity(MatchDTO matchdto) {
		if (matchdto == null) {
			return null;
		}

		Match match = new Match();

		match.setId(matchdto.getId());
		match.setVersion(matchdto.getVersion());
		match.setStartDate(matchdto.getStartDate());
		match.setTeamOne(matchdto.getTeamOne());
		match.setTeamTwo(matchdto.getTeamTwo());
		match.setResultTeamOne(matchdto.getResultTeamOne());
		match.setResultTeamTwo(matchdto.getResultTeamTwo());

		return match;
	}
}
