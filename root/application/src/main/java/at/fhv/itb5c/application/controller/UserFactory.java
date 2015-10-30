package at.fhv.itb5c.application.controller;

import java.util.LinkedList;
import java.util.List;

import at.fhv.itb5c.application.dto.UserConverter;
import at.fhv.itb5c.application.dto.UserDTO;
import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.dto.IUserFactory;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.model.PersistenceFacade;
import at.fhv.itb5c.model.entity.User;

public class UserFactory implements IUserFactory, ILogger {

	@Override
	public IUser createUser() {
		IUser user = new UserDTO();
		return user;
	}

	public IUser save(IUser user) {
		if (user != null) {
			User userEntity = UserConverter.toEntity(user);

			PersistenceFacade.getInstance().saveOrUpdate(userEntity);

			user = UserConverter.toDTO(userEntity);

			log.debug("user with id " + userEntity.getId() + " saved.");
			log.debug(user);

			return user;
		}

		return null;
	}

	public List<IUser> findUsers(String firstName, String lastName, Long departmentId, Boolean membershipFeePaid) {
		// receive results
		List<User> userEntities = PersistenceFacade.getInstance().findUsers(firstName, lastName, departmentId,
				membershipFeePaid);
		List<IUser> userDTO = new LinkedList<IUser>();

		log.debug(userEntities.size() + " results found for search: firstName = " + firstName + ", lastName = "
				+ lastName + ", departmentId = " + departmentId + ", membershipFeePaid = " + membershipFeePaid);

		// convert to DTOs
		for (User userEntity : userEntities) {
			userDTO.add(UserConverter.toDTO(userEntity));
		}

		return userDTO;
	}
}