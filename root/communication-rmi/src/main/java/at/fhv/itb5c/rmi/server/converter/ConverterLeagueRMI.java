package at.fhv.itb5c.rmi.server.converter;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.stream.Collectors;

import at.fhv.itb5c.application.dto.LeagueDTO;
import at.fhv.itb5c.commons.dto.rmi.ILeagueRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.server.rmiclasses.LeagueRMI;

public class ConverterLeagueRMI implements ILogger {
	public static ILeagueRMI toRMI(LeagueDTO league) {
		if(league == null){
			return null;
		}
		
		ILeagueRMI rmi = null;
		try {
			rmi = new LeagueRMI();
			rmi.setId(league.getId());
			rmi.setVersion(league.getVersion());
			rmi.setName(league.getName());
			rmi.setTypeOfSport(league.getTypeOfSport());
		} catch (RemoteException e) {
			log.error(e.getMessage());
		}
		return rmi;
	}
	
	public static Collection<ILeagueRMI> toRMI(Collection<LeagueDTO> leagues) {
		if(leagues == null){
			return null;
		}
		
		return leagues.stream().map(ConverterLeagueRMI::toRMI).collect(Collectors.toList());
	}
	
	public static LeagueDTO toDTO(ILeagueRMI leauge) {
		if(leauge == null){
			return null;
		}
		
		LeagueDTO dto = null;
		try {
			dto = new LeagueDTO();
			dto.setId(leauge.getId());
			dto.setVersion(leauge.getVersion());
			dto.setName(leauge.getName());
			dto.setTypeOfSport(dto.getTypeOfSport());
		} catch (RemoteException e) {
			log.error(e.getMessage());
		}
		return dto;
	}
	
	public static Collection<LeagueDTO> toDTO(Collection<ILeagueRMI> leagues) {
		if(leagues == null){
			return null;
		}
		
		return leagues.stream().map(ConverterLeagueRMI::toDTO).collect(Collectors.toList());
	}
}
