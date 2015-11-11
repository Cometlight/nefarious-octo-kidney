package at.fhv.itb5c.rmi.server.converter;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.stream.Collectors;

import at.fhv.itb5c.application.dto.UserDTO;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.server.rmiclasses.UserRMI;

public class ConverterUserRMI implements ILogger {
	public static IUserRMI toRMI(UserDTO dto) {	
		IUserRMI rmi = null;
		try {
			rmi = new UserRMI();
			
			rmi.setId(dto.getId());
			rmi.setVersion(dto.getVersion());
			rmi.setAddress(dto.getAddress());
			rmi.setDateOfBirth(dto.getDateOfBirth());
			rmi.setEmail(dto.getEmail());
			rmi.setFirstName(dto.getFirstName());
			rmi.setLastName(dto.getLastName());
			rmi.setGender(dto.getGender());
			rmi.setMembershipFee(dto.getMembershipFee());
			rmi.setRoles(dto.getRoles());
			rmi.setTelephoneNumber(dto.getTelephoneNumber());
			rmi.setTypeOfSports(dto.getTypeOfSports());
			rmi.setMembershipFeePaid(dto.getMembershipFeePaid());
			rmi.setDepartmentId(dto.getDepartmentId());
			rmi.setLdapUID(dto.getLdapUID());
		} catch (RemoteException e) {
			log.error(e.getMessage());
		}
		return rmi;
	}
	
	public static Collection<IUserRMI> toRMI(Collection<UserDTO> users) {
		return users.stream().map(ConverterUserRMI::toRMI).collect(Collectors.toList());
	}

	public static UserDTO toDTO(IUserRMI rmi) {
		UserDTO dto = new UserDTO();

		try {
			dto.setId(rmi.getId());
			dto.setVersion(rmi.getVersion());
			dto.setFirstName(rmi.getFirstName());
			dto.setLastName(rmi.getLastName());
			dto.setEmail(rmi.getEmail());
			dto.setTelephoneNumber(rmi.getTelephoneNumber());
			dto.setGender(rmi.getGender());
			dto.setAddress(rmi.getAddress());
			dto.setDateOfBirth(rmi.getDateOfBirth());
			dto.setMembershipFee(rmi.getMembershipFee());
			dto.setRoles(rmi.getRoles());
			dto.setTypeOfSports(rmi.getTypeOfSports());
			dto.setMembershipFeePaid(rmi.getMembershipFeePaid());
			dto.setDepartmentId(rmi.getDepartmentId());
			dto.setLdapUID(rmi.getLdapUID());
		} catch (RemoteException e) {
			log.error(e.getMessage());
		}
		return dto;
	}
}
