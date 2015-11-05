package at.fhv.itb5c.model;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.fhv.itb5c.model.entity.User;
import at.fhv.itb5c.model.DatabaseTestUtility;


public class FindUsersSimpleTest {
    private static final String DBFILE = "testdb.odb";

    @Before
    public void setUp() throws Exception {
        DatabaseTestUtility.deleteDatabaseFile(DBFILE);
        PersistenceFacade.setPersistenceUnitName(DBFILE);
        addUser("Daniel", "Griesser");
        addUser("Daniel", "Scheffknecht");
        addUser("Prachi", "Mishra");
        addUser("Simon", "Angerer");
        addUser("Florian", "Hämmerle");
        addUser("Angelina", "Schester");
    }

    @After
    public void tearDown() throws Exception {
        PersistenceFacade.shutdown();
        DatabaseTestUtility.deleteDatabaseFile(DBFILE);
    }

    private User addUser(String firstName, String lastName) throws Exception {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return PersistenceFacade.getInstance().saveOrUpdate(user);
    }


    @Test
    public void testFirstNameSearch() {
        List<User> result = PersistenceFacade.getInstance().findUsersSimple("Dan");
        assertEquals(2, result.size());
    }

    @Test
    public void testLastNameSearch() {
        List<User> result = PersistenceFacade.getInstance().findUsersSimple("Sche");
        assertEquals(2, result.size());
    }

    @Test
    public void testWholeNameSearch() {
        List<User> result = PersistenceFacade.getInstance().findUsersSimple("Ang");
        assertEquals(2, result.size());
    }

}
