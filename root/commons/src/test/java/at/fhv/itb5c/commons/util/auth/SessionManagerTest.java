package at.fhv.itb5c.commons.util.auth;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import at.fhv.itb5c.commons.enums.UserRole;

public class SessionManagerTest {

	private String _sessionId;

	@Before
	public void setUp() throws Exception {
		_sessionId = SessionManager.getInstance().createNewSession(1l, new HashSet<>(Arrays.asList(UserRole.StandardUser)));
	}
	
	@Test
	public void hasRoleTest() {
		assertTrue(SessionManager.getInstance().hasRole(_sessionId, UserRole.StandardUser));
		assertFalse(SessionManager.getInstance().hasRole(_sessionId, UserRole.Admin));
	}
	
	@Test
	public void getUserIdTest(){
		assertEquals(SessionManager.getInstance().getUserId(_sessionId).longValue(), 1l);
	}

}
