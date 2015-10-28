package at.fhv.itb5c.model;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.model.entity.User;

public class App 
{
    public static void main( String[] args )
    {
    	User user = new User();
    	user.setId((long) 1);
    	user.setFirstName("Daniel");
    	user.setLastName("Scheffknecht");
    	user.setGender(Gender.Male);
    	user.setDateOfBirth(LocalDate.of(1994, 6, 1));
    	user.setAddress("Lustenau");
    	
    	PersistenceFacade.getInstance().saveOrUpdate(user);
    	
    	User userRetrieved = PersistenceFacade.getInstance().getById(User.class, 1);
    	System.out.println(userRetrieved.getVersion());
    	User userRetrieved2 = PersistenceFacade.getInstance().getById(User.class, 1);
    	System.out.println(userRetrieved2.getVersion());
    	
    	userRetrieved.getDateOfBirth();
    	
    	PersistenceFacade.getInstance().delete(user);
    	
    	userRetrieved = PersistenceFacade.getInstance().getById(User.class, 1);
    	System.out.println(userRetrieved);
    	
    	PersistenceFacade.shutdown();
    	
//    	System.out.println(user.getId());
//    	
//    	System.out.println("versionA - prepersist: " + user.getVersion());
//    	
//    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence.odb");
//    	EntityManager em = emf.createEntityManager();
//    	em.getTransaction().begin();
//    	em.persist(user);
//    	em.getTransaction().commit();
//    	
//    	System.out.println("versionB - postpersist: " + user.getVersion());
//    	
//    	user.setFirstName("Daniel2");
//    	
//    	System.out.println("versionC - prepersist 2: " + user.getVersion());
//    	EntityManager em2 = emf.createEntityManager();
//    	em2.getTransaction().begin();
//    	user = em2.merge(user);
//    	em2.getTransaction().commit();
//    	
//    	System.out.println("versionD - postpersist 2: " + user.getVersion());
//    	System.out.println(user.getFirstName());
    	
    	
//    	em.getTransaction().begin();
//    	System.out.println(em.find(User.class, 9));
//    	em.getTransaction().commit();
//    	
//        System.out.println( "Hello World!" );
    }
}