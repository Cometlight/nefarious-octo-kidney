package at.fhv.itb5c.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.fhv.itb5c.model.entity.User;

public class FindUserByLDAPTest {
	private static final String DBFILE = "testdb.odb";

	@Before
	public void setUp() throws Exception {
		DatabaseTestUtility.deleteDatabaseFile(DBFILE);
		PersistenceFacade.setPersistenceUnitName(DBFILE);
		addUser("Daniel", "Griesser", "dgr7348");
		addUser("Simon", "Angerer", "san7353");
	}

	@After
	public void tearDown() throws Exception {
        PersistenceFacade.shutdown();
        DatabaseTestUtility.deleteDatabaseFile(DBFILE);
	}

	@Test
	public void test() {
		assertNotNull(PersistenceFacade.getInstance().findUserByLDAP("dgr7348"));
	}

	private User addUser(String firstName, String lastName, String ldap) throws Exception {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setLdapUID(ldap);
		return PersistenceFacade.getInstance().saveOrUpdate(user);
	}

}
