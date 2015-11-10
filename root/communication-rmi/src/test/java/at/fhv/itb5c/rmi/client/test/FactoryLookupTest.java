package at.fhv.itb5c.rmi.client.test;

import java.rmi.RemoteException;

import org.junit.Assert;
import org.junit.Test;

import at.fhv.itb5c.rmi.client.RMIClient;



public class FactoryLookupTest {
	
	@Test
	public void requestFactoriesTest() {
		try {
			//just calling a method of each factory stub to make sure that they are not null  
			RMIClient.getRMIClient().getDepartmentFactory().getAllDepartments();
			RMIClient.getRMIClient().getTeamFactory().createTeam();
			RMIClient.getRMIClient().getUserFactory().createUser();
		} catch (RemoteException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		
	}
}
