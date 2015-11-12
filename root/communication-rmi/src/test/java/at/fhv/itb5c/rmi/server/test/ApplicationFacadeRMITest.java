package at.fhv.itb5c.rmi.server.test;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;
import at.fhv.itb5c.commons.util.auth.SessionManager;
import at.fhv.itb5c.rmi.server.ApplicationFacadeRMI;

public class ApplicationFacadeRMITest {
	String _sessionId;
	
	@Before
	public void setUp(){
		_sessionId = SessionManager.getInstance().createNewSession(1l, new HashSet<>(Arrays.asList(UserRole.Admin)));
	}
	
	@Test
	public void createUser() {
		try {
			ApplicationFacadeRMI app = new ApplicationFacadeRMI();
			IUserRMI user = app.createUser(_sessionId);
			
			if(user == null) {
				System.out.println("User is null!");
				Assert.assertFalse(true);
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
			Assert.assertFalse(true);
		}
		
	}
	
	@Test 
	public void getTypesOfSportFromUser() {
		try {
			ApplicationFacadeRMI app = new ApplicationFacadeRMI();
			IUserRMI user = app.createUser(_sessionId);
			
			Set<TypeOfSport> sports = user.getTypeOfSports();
			
			if(sports == null) {
				System.out.println("No Types of Sport!");
				Assert.assertFalse(true);
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
			Assert.assertFalse(true);
		}
		
	}
}