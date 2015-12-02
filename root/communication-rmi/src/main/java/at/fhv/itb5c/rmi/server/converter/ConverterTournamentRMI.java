package at.fhv.itb5c.rmi.server.converter;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import at.fhv.itb5c.application.dto.TournamentDTO;
import at.fhv.itb5c.commons.dto.rmi.ITournamentRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.server.rmiclasses.TournamentRMI;

public class ConverterTournamentRMI implements ILogger {
	public static TournamentDTO toDTO(ITournamentRMI tournamentrmi) {
		if (tournamentrmi == null) {
			return null;
		}

		TournamentDTO tournamentDTO = null;

		try {
			tournamentDTO = new TournamentDTO();
			tournamentDTO.setId(tournamentrmi.getId());
			tournamentDTO.setVersion(tournamentrmi.getVersion());
			tournamentDTO.setFee(tournamentrmi.getFee());
			tournamentDTO.setGuestTeams(new HashSet<>(tournamentrmi.getGuestTeams()));
			tournamentDTO.setHomeTeamsIds(new HashSet<>(tournamentrmi.getHomeTeamsIds()));
			tournamentDTO.setMatchesIds(new HashSet<>(tournamentrmi.getMatchesIds()));
			tournamentDTO.setName(tournamentrmi.getName());
			tournamentDTO.setDate(tournamentrmi.getDate());
			tournamentDTO.setDepartmentId(tournamentrmi.getDepartmentId());
		} catch (RemoteException e) {
			log.error(e.getMessage());
		}

		return tournamentDTO;
	}
	
	public static Collection<TournamentDTO> toDTO(Collection<ITournamentRMI> tournaments) {
		if (tournaments == null) {
			return null;
		}

		return tournaments.stream().map(ConverterTournamentRMI::toDTO).collect(Collectors.toList());
	}


	public static Collection<ITournamentRMI> toRMI(Collection<TournamentDTO> tournaments) {
		if (tournaments == null) {
			return null;
		}

		return tournaments.stream().map(ConverterTournamentRMI::toRMI).collect(Collectors.toList());
	}

	public static ITournamentRMI toRMI(TournamentDTO tournamentDTO) {
		if (tournamentDTO == null) {
			return null;
		}

		ITournamentRMI tournamentRMI = null;

		try {
			tournamentRMI = new TournamentRMI();
			tournamentRMI.setId(tournamentDTO.getId());
			tournamentRMI.setVersion(tournamentDTO.getVersion());
			tournamentRMI.setFee(tournamentDTO.getFee());
			tournamentRMI.setGuestTeams(new HashSet<>(tournamentDTO.getGuestTeams()));
			tournamentRMI.setHomeTeamsIds(new HashSet<>(tournamentDTO.getHomeTeamsIds()));
			tournamentRMI.setMatchesIds(new HashSet<>(tournamentDTO.getMatchesIds()));
			tournamentRMI.setName(tournamentDTO.getName());
			tournamentRMI.setDate(tournamentDTO.getDate());
			tournamentRMI.setDepartmentId(tournamentDTO.getDepartmentId());
		} catch (RemoteException e) {
			log.error(e.getMessage());
		}

		return tournamentRMI;
	}
}
