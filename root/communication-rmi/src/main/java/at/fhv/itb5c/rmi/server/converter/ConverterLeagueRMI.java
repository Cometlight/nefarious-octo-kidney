package at.fhv.itb5c.rmi.server.converter;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.stream.Collectors;

import at.fhv.itb5c.application.dto.LeagueDTO;
import at.fhv.itb5c.commons.dto.rmi.ILeagueRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.server.rmiclasses.LeagueRMI;

public class ConverterLeagueRMI implements ILogger {
	public static ILeagueRMI toRMI(LeagueDTO dto) {
		ILeagueRMI rmi = null;
		try {
			rmi = new LeagueRMI();
			rmi.setId(dto.getId());
			rmi.setVersion(dto.getVersion());
			rmi.setName(dto.getName());
			rmi.setTypeOfSport(dto.getTypeOfSport());
		} catch (RemoteException e) {
			log.error(e.getMessage());
		}
		return rmi;
	}
	
	public static Collection<ILeagueRMI> toRMI(Collection<LeagueDTO> dtos) {
		return dtos.stream().map(ConverterLeagueRMI::toRMI).collect(Collectors.toList());
	}
	
	public static LeagueDTO toDTO(ILeagueRMI rmi) {
		LeagueDTO dto = null;
		try {
			dto = new LeagueDTO();
			dto.setId(rmi.getId());
			dto.setVersion(rmi.getVersion());
			dto.setName(rmi.getName());
			dto.setTypeOfSport(dto.getTypeOfSport());
		} catch (RemoteException e) {
			log.error(e.getMessage());
		}
		return dto;
	}
}
