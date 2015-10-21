package at.fhv.itb5c.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
    	user.setId(1);
    	user.setName("Hellou");
    	
    	EntityManagerFactory 	 emf =
    	    Persistence.createEntityManagerFactory("persistence.odb");
    	EntityManager em = emf.createEntityManager();
    	em.getTransaction().begin();
    	em.persist(user);
    	em.getTransaction().commit();
    	
    	em.getTransaction().begin();
    	System.out.println(em.find(User.class, 1));
    	em.getTransaction().commit();
    	
        System.out.println( "Hello World!" );
    }
}