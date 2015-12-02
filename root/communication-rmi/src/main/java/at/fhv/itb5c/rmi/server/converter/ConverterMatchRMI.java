package at.fhv.itb5c.rmi.server.converter;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.stream.Collectors;

import at.fhv.itb5c.commons.dto.MatchDTO;
import at.fhv.itb5c.commons.dto.rmi.IMatchRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.server.rmiclasses.MatchRMI;

public class ConverterMatchRMI implements ILogger {
	public static MatchDTO toDTO(IMatchRMI matchrmi) {
		if(matchrmi == null){
			return null;
		}
		
		MatchDTO matchdto = null;

		try {
			matchdto = new MatchDTO();
			matchdto.setId(matchrmi.getId());
			matchdto.setVersion(matchrmi.getVersion());
			matchdto.setStartDate(matchrmi.getStartDate());
			matchdto.setTeamOne(matchrmi.getTeamOne());
			matchdto.setTeamTwo(matchrmi.getTeamTwo());
			matchdto.setResultTeamOne(matchrmi.getResultTeamOne());
			matchdto.setResultTeamTwo(matchrmi.getResultTeamTwo());
		} catch (RemoteException e) {
			log.error(e.getMessage());
		}

		return matchdto;
	}

	public static Collection<IMatchRMI> toRMI(Collection<MatchDTO> entities) {
		if(entities == null){
			return null;
		}
		
		return entities.stream().map(ConverterMatchRMI::toRMI).collect(Collectors.toList());
	}

	public static IMatchRMI toRMI(MatchDTO matchdto) {
		if(matchdto == null){
			return null;
		}
		
		IMatchRMI matchrmi = null;

		try {
			matchrmi = new MatchRMI();
			matchrmi.setId(matchdto.getId());
			matchrmi.setVersion(matchdto.getVersion());
			matchrmi.setStartDate(matchdto.getStartDate());
			matchrmi.setTeamOne(matchdto.getTeamOne());
			matchrmi.setTeamTwo(matchdto.getTeamTwo());
			matchrmi.setResultTeamOne(matchdto.getResultTeamOne());
			matchrmi.setResultTeamTwo(matchdto.getResultTeamTwo());
		} catch (RemoteException e) {
			log.error(e.getMessage());
		}

		return matchrmi;
	}
}
