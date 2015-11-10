package at.fhv.itb5c.application;

import java.util.Collection;

import at.fhv.itb5c.application.converter.ConverterUserDTO;
import at.fhv.itb5c.application.dto.UserDTO;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.model.PersistenceFacade;

public class ApplicationFacade implements ILogger {
	public UserDTO createUser() {
		return new UserDTO();
	}

	public Collection<UserDTO> findUsers(String firstName, String lastName, Long departmentId, Boolean membershipFeePaid) {
		return ConverterUserDTO.toDTO(PersistenceFacade.getInstance().findUsers(firstName, lastName, departmentId, membershipFeePaid));
	}

	public Collection<UserDTO> findUsersSimple(String name) {
		return ConverterUserDTO.toDTO(PersistenceFacade.getInstance().findUsersSimple(name));
	}

	public UserDTO saveUser(UserDTO user) {
		try {
			return ConverterUserDTO.toDTO(PersistenceFacade.getInstance().saveOrUpdate(ConverterUserDTO.toEntity(user)));
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}
}
