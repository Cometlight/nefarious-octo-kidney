package at.fhv.itb5c.rmi.server.converter;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import at.fhv.itb5c.application.dto.TeamDTO;
import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.server.rmiclasses.TeamRMI;

public class ConverterTeamRMI implements ILogger {
	public static ITeamRMI toRMI(TeamDTO dto) {
		ITeamRMI rmi = null;
		try {
			rmi = new TeamRMI();
			
			rmi.setId(dto.getId());
			rmi.setVersion(dto.getVersion());
			rmi.setName(dto.getName());
			rmi.setTypeOfSport(dto.getTypeOfSport());
			rmi.setDepartmentId(dto.getDepartmentId());
			rmi.setCoachId(dto.getCoachId());
			rmi.setLeagueId(dto.getLeagueId());
			rmi.setMemberIds(new HashSet<>(dto.getMemberIds()));
		} catch (RemoteException e) {
			log.error(e.getMessage());
		}
		return rmi;
	}
	
	public static Collection<ITeamRMI> toRMI(Collection<TeamDTO> dtos) {
		return dtos.stream().map(ConverterTeamRMI::toRMI).collect(Collectors.toList());
	}
	
	public static TeamDTO toDTO(ITeamRMI rmi) {
		TeamDTO dto = new TeamDTO();
		try {
			dto.setId(rmi.getId());
			dto.setVersion(rmi.getVersion());
			dto.setName(rmi.getName());
			dto.setTypeOfSport(rmi.getTypeOfSport());
			dto.setDepartmentId(rmi.getDepartmentId());
			dto.setCoachId(rmi.getCoachId());
			dto.setLeagueId(rmi.getLeagueId());
			dto.setMemberIds(new HashSet<>(rmi.getMemberIds()));
		} catch (RemoteException e) {
			log.error(e.getMessage());
		}
		return dto;
	}
}
