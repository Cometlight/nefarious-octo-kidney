package at.fhv.itb5c.application.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import at.fhv.itb5c.application.dto.UserConverter;
import at.fhv.itb5c.application.dto.UserDTO;
import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.dto.IUserFactory;
import at.fhv.itb5c.model.entity.User;

public class UserFactoryImpl implements IUserFactory {

	@Override
	public IUser createUser() {
		IUser user = new UserDTO();
		return user;
	}

	public void save(IUser user) {
		if (user != null) {
			User userEntity = UserConverter.toEntity(user);

			// TODO not nice!
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence.odb");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(userEntity);
			em.getTransaction().commit();
		}
	}
}