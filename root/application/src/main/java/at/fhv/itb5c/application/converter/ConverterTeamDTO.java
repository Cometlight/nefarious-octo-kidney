package at.fhv.itb5c.application.converter;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import at.fhv.itb5c.application.dto.TeamDTO;
import at.fhv.itb5c.model.entity.Team;

public class ConverterTeamDTO {
	public static TeamDTO toDTO(Team entity) {
		if(entity == null){
			return null;
		}
		
		TeamDTO dto = new TeamDTO();
		
		dto.setId(entity.getId());
		dto.setVersion(entity.getVersion());
		dto.setName(entity.getName());
		dto.setTypeOfSport(entity.getTypeOfSport());
		dto.setDepartmentId(entity.getDepartmentId());
		dto.setCoachId(entity.getCoachId());
		dto.setLeagueId(entity.getLeagueId());
		dto.setMemberIds(new HashSet<>(entity.getMemberIds()));
		
		return dto;
	}
	
	public static Collection<TeamDTO> toDTO(Collection<Team> entities) {
		if(entities == null){
			return null;
		}
		
		return entities.stream().map(ConverterTeamDTO::toDTO).collect(Collectors.toList());
	}
	
	public static Team toEntity(TeamDTO dto) {
		if(dto == null){
			return null;
		}
		
		Team entity = new Team();
		
		entity.setId(dto.getId());
		entity.setVersion(dto.getVersion());
		entity.setName(dto.getName());
		entity.setTypeOfSport(dto.getTypeOfSport());
		entity.setDepartmentId(dto.getDepartmentId());
		entity.setCoachId(dto.getCoachId());
		entity.setLeagueId(dto.getLeagueId());
		entity.setMemberIds(new HashSet<>(dto.getMemberIds()));
		
		return entity;
	}
}
