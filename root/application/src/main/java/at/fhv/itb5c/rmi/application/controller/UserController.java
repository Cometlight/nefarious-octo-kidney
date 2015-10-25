package at.fhv.itb5c.rmi.application.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.dto.IUserController;
import at.fhv.itb5c.model.entity.User;
import at.fhv.itb5c.rmi.application.dto.UserConverter;

public class UserController implements IUserController {
	
	@Override
	public IUser createUser(IUser user) {
		User userEntity = UserConverter.toEntity(user);
    	
		//TODO not nice!
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence.odb");
    	EntityManager em = emf.createEntityManager();
    	em.getTransaction().begin();
    	em.persist(userEntity);
    	em.getTransaction().commit();
    	
    	user = UserConverter.toDTO(userEntity);
		return user;
	}
	
}