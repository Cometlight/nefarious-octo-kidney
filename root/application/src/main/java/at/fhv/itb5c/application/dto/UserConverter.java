package at.fhv.itb5c.application.dto;

import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.model.entity.User;

public class UserConverter implements ILogger {
	public static IUser toDTO(User user){
		// null in - null out
		if(user==null){
			return null;
		}
		
		// convert
		UserDTO userdto = new UserDTO();
		userdto.setId(user.getId()); // id is needed for jpa to identify the object
		userdto.setVersion(user.getVersion());
		userdto.setFirstName(user.getFirstName());
		userdto.setLastName(user.getLastName());
		userdto.setEmail(user.getEmail());
		userdto.setTelephoneNumber(user.getTelephoneNumber());
		userdto.setGender(user.getGender());
		userdto.setAddress(user.getAddress());
		userdto.setDateOfBirth(user.getDateOfBirth());
		userdto.setMembershipFee(user.getMembershipFee());
		userdto.setRoles(user.getRoles());
		userdto.setTypeOfSports(user.getTypeOfSports());
		userdto.setMembershipFeePaid(user.getMembershipFeePaid());
		userdto.setDepartment(DepartmentConverter.toDTO(user.getDepartment()));
		
		log.debug("Converting User Object Model2DTO: " + userdto);
		
		return userdto;
	}
	
	public static User toEntity(IUser userdto){
		// null in - null out
		if(userdto==null){
			return null;
		}
		
		// convert
		User user = new User();
		try {
			// id is needed for jpa to identify the object
			user.setId(userdto.getId());
			user.setVersion(userdto.getVersion());
			user.setFirstName(userdto.getFirstName());
			user.setLastName(userdto.getLastName());
			user.setEmail(userdto.getEmail());
			user.setTelephoneNumber(userdto.getTelephoneNumber());
			user.setGender(userdto.getGender());
			user.setAddress(userdto.getAddress());
			user.setDateOfBirth(userdto.getDateOfBirth());
			user.setMembershipFee(userdto.getMembershipFee());
			user.setRoles(userdto.getRoles());
			user.setTypeOfSports(userdto.getTypeOfSports());
			user.setMembershipFeePaid(userdto.getMembershipFeePaid());
			user.setDepartment(DepartmentConverter.toEntity(userdto.getDepartment()));
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		} 
		
		log.debug("Converting User Object DTO2Model: " + user);
		
		return user;
	}
}