package at.fhv.itb5c.application.converter;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import at.fhv.itb5c.application.dto.TournamentDTO;
import at.fhv.itb5c.model.entity.Tournament;

public class ConverterTournamentDTO {
	public static TournamentDTO toDTO(Tournament tournament) {
		if(tournament == null){
			return null;
		}
		
		TournamentDTO tournamentDTO = new TournamentDTO();

		tournamentDTO.setId(tournament.getId());
		tournamentDTO.setVersion(tournament.getVersion());
		tournamentDTO.setFee(tournament.getFee());
		tournamentDTO.setGuestTeams(new HashSet<>(tournament.getGuestTeams()));
		tournamentDTO.setHomeTeamsIds(new HashSet<>(tournament.getHomeTeamsIds()));
		tournamentDTO.setMatchesIds(new HashSet<>(tournament.getMatchesIds()));
		tournamentDTO.setName(tournament.getName());
		tournamentDTO.setDate(tournament.getDate());
		tournamentDTO.setDepartmentId(tournament.getDepartmentId());
		tournamentDTO.setDone(tournament.getDone());

		return tournamentDTO;
	}

	public static Collection<TournamentDTO> toDTO(Collection<Tournament> tournaments) {
		if (tournaments == null) {
			return null;
		}

		return tournaments.stream().map(ConverterTournamentDTO::toDTO).collect(Collectors.toList());
	}

	public static Tournament toEntity(TournamentDTO tournamentDTO) {
		if(tournamentDTO == null){
			return null;
		}
		
		Tournament tournament = new Tournament();

		tournament.setId(tournamentDTO.getId());
		tournament.setVersion(tournamentDTO.getVersion());
		tournament.setFee(tournamentDTO.getFee());
		tournament.setGuestTeams(new HashSet<>(tournamentDTO.getGuestTeams()));
		tournament.setHomeTeamsIds(new HashSet<>(tournamentDTO.getHomeTeamsIds()));
		tournament.setMatchesIds(new HashSet<>(tournamentDTO.getMatchesIds()));
		tournament.setName(tournamentDTO.getName());
		tournament.setDate(tournamentDTO.getDate());
		tournament.setDepartmentId(tournamentDTO.getDepartmentId());
		tournament.setDone(tournamentDTO.getDone());

		return tournament;
	}
}
