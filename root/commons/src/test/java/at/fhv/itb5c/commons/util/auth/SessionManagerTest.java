package at.fhv.itb5c.commons.util.auth;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import at.fhv.itb5c.commons.enums.UserRole;

public class SessionManagerTest {

	@Before
	public void setUp() throws Exception {
		SessionManager.getInstance().createNewSession(1l, new HashSet<>(Arrays.asList(UserRole.StandardUser)));
	}
	
	@Test
	public void hasRoleTest() {
		//assertTrue(SessionManager.getInstance().hasRole(1l, UserRole.StandardUser));
		//assertFalse(SessionManager.getInstance().hasRole(1l, UserRole.Admin));
	}

}
