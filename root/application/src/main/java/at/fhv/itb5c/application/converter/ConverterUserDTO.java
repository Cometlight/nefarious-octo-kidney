package at.fhv.itb5c.application.converter;

import java.util.Collection;
import java.util.stream.Collectors;

import at.fhv.itb5c.commons.dto.UserDTO;
import at.fhv.itb5c.model.entity.User;

public class ConverterUserDTO {
	public static UserDTO toDTO(User user) {
		UserDTO dto;
		if(user != null) {
			dto = new UserDTO();
			dto.setId(user.getId());
			dto.setVersion(user.getVersion());
			dto.setFirstName(user.getFirstName());
			dto.setLastName(user.getLastName());
			dto.setEmail(user.getEmail());
			dto.setTelephoneNumber(user.getTelephoneNumber());
			dto.setGender(user.getGender());
			dto.setAddress(user.getAddress());
			dto.setDateOfBirth(user.getDateOfBirth());
			dto.setMembershipFee(user.getMembershipFee());
			dto.setRoles(user.getRoles());
			dto.setTypeOfSports(user.getTypeOfSports());
			dto.setMembershipFeePaid(user.getMembershipFeePaid());
			dto.setDepartmentId(user.getDepartmentId());
			dto.setLdapUID(user.getLdapUID());
		}
		else {
			dto = null;
		}
		
		return dto;
	}
	
	public static Collection<UserDTO> toDTO(Collection<User> users) {
		if(users == null) {
			return null;
		}
		
		return users.stream().map(ConverterUserDTO::toDTO).collect(Collectors.toList());
	}
	
	public static User toEntity(UserDTO userDTO) {
		User entity = null;
		
		if (userDTO != null) {
			entity = new User();
			entity.setId(userDTO.getId());
			entity.setVersion(userDTO.getVersion());
			entity.setFirstName(userDTO.getFirstName());
			entity.setLastName(userDTO.getLastName());
			entity.setEmail(userDTO.getEmail());
			entity.setTelephoneNumber(userDTO.getTelephoneNumber());
			entity.setGender(userDTO.getGender());
			entity.setAddress(userDTO.getAddress());
			entity.setDateOfBirth(userDTO.getDateOfBirth());
			entity.setMembershipFee(userDTO.getMembershipFee());
			entity.setRoles(userDTO.getRoles());
			entity.setTypeOfSports(userDTO.getTypeOfSports());
			entity.setMembershipFeePaid(userDTO.getMembershipFeePaid());
			entity.setDepartmentId(userDTO.getDepartmentId());
			entity.setLdapUID(userDTO.getLdapUID());
		}
		
		return entity;
	}
}
