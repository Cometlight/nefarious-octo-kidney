package at.fhv.itb5c.rmi.server.converter;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

import at.fhv.itb5c.application.dto.TeamDTO;
import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.server.rmiclasses.TeamRMI;

public class ConverterTeamRMI implements ILogger {
	public static ITeamRMI toRMI(TeamDTO dto) {
		if(dto == null){
			return null;
		}
		
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
			rmi.setMemberStatus(new HashMap<>(dto.getMemberStatus()));
		} catch (RemoteException e) {
			log.error(e.getMessage());
		}
		return rmi;
	}
	
	public static Collection<ITeamRMI> toRMI(Collection<TeamDTO> dtos) {
		if(dtos == null){
			return null;
		}
		
		return dtos.stream().map(ConverterTeamRMI::toRMI).collect(Collectors.toList());
	}
	
	public static TeamDTO toDTO(ITeamRMI rmi) {
		if(rmi == null){
			return null;
		}
		
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
			dto.setMemberStatus(new HashMap<>(rmi.getMemberStatus()));
		} catch (RemoteException e) {
			log.error(e.getMessage());
		}
		return dto;
	}
}
