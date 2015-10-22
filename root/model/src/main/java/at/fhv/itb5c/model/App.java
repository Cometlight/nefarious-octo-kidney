package at.fhv.itb5c.model;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import at.fhv.itb5c.model.entity.Gender;
import at.fhv.itb5c.model.entity.User;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	User user = new User();
    	user.setId(4);
    	user.setFirstName("Daniel");
    	user.setLastName("Scheffknecht");
    	user.setGender(Gender.Male);
    	user.setDateOfBirth(LocalDate.of(1994, 6, 1));
    	user.setAddress("Lustenau");
    	
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence.odb");
    	EntityManager em = emf.createEntityManager();
    	em.getTransaction().begin();
    	em.persist(user);
    	em.getTransaction().commit();
    	
    	em.getTransaction().begin();
    	System.out.println(em.find(User.class, 4));
    	em.getTransaction().commit();
    	
        System.out.println( "Hello World!" );
    }
}