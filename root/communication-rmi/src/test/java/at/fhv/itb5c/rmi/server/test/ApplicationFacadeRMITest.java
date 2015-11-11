package at.fhv.itb5c.rmi.server.test;

import java.rmi.RemoteException;

import org.junit.Assert;
import org.junit.Test;

import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.rmi.server.ApplicationFacadeRMI;

public class ApplicationFacadeRMITest {
	
	@Test
	public void createUser() {
		try {
			ApplicationFacadeRMI app = new ApplicationFacadeRMI();
			IUserRMI user = app.createUser();
			
			if(user == null) {
				Assert.assertFalse(true);
				System.out.println("User is null!");
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
			Assert.assertFalse(true);
		}
		
	}
}
