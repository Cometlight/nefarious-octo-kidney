package at.fhv.itb5c.application.converter;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import at.fhv.itb5c.application.dto.TournamentDTO;
import at.fhv.itb5c.model.entity.Tournament;

public class ConverterTounamentDTO {
	public static TournamentDTO toDTO(Tournament tournament) {
		TournamentDTO tournamentDTO = new TournamentDTO();

		tournamentDTO.setId(tournament.getId());
		tournamentDTO.setVersion(tournament.getVersion());
		tournamentDTO.setFee(tournament.getFee());
		tournamentDTO.setGuestTeams(new HashSet<>(tournament.getGuestTeams()));
		tournamentDTO.setHomeTeamsIds(new HashSet<>(tournament.getHomeTeamsIds()));
		tournamentDTO.setMatchesIds(new HashSet<>(tournament.getMatchesIds()));
		tournamentDTO.setName(tournament.getName());
		tournamentDTO.setDate(tournament.getDate());

		return tournamentDTO;
	}

	public static Collection<TournamentDTO> toDTO(Collection<Tournament> tournaments) {
		if (tournaments == null) {
			return null;
		}

		return tournaments.stream().map(ConverterTounamentDTO::toDTO).collect(Collectors.toList());
	}

	public static Tournament toEntity(TournamentDTO tournamentDTO) {
		Tournament tournament = new Tournament();

		tournament.setId(tournamentDTO.getId());
		tournament.setVersion(tournamentDTO.getVersion());
		tournament.setFee(tournamentDTO.getFee());
		tournament.setGuestTeams(new HashSet<>(tournamentDTO.getGuestTeams()));
		tournament.setHomeTeamsIds(new HashSet<>(tournamentDTO.getHomeTeamsIds()));
		tournament.setMatchesIds(new HashSet<>(tournamentDTO.getMatchesIds()));
		tournament.setName(tournamentDTO.getName());
		tournament.setDate(tournamentDTO.getDate());

		return tournament;
	}
}
