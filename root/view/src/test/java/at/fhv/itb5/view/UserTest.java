package at.fhv.itb5.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import at.fhv.itb5c.rmi.client.RMIClient;

public class UserTest {
	
	@Before
	public void before() {
		RMIClient.getRMIClient().startUp();
	}
	
	@After
	public void after() {
		//TODO(san7985): replace when close is implemented
		//RMIClient.getRMIClient().close();
	}
	
	@Test
	public void createUser() {
		/*try {
			IUserRMI user = RMIClient.getRMIClient().getApplicationFacade().createUser();
			
			if(user == null) {
				System.out.println("Cant create User!");
				Assert.assertTrue(false);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}*/
	}
	
	@Test
	public void readUserTypesOfSport() {
		/*try {
			IUserRMI user = RMIClient.getRMIClient().getApplicationFacade().createUser();
			
			Set<TypeOfSport> sports = user.getTypeOfSports();
			if(sports == null) {
				System.out.println("No sports set!");
				Assert.assertTrue(false);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}*/
	}
}
