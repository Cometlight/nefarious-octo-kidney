package at.fhv.itb5c.rmi.client.test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

import at.fhv.itb5c.commons.dto.rmi.IUserFactoryRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;

public class TestRMI {
	// ec2-52-10-208-136.us-west-2.compute.amazonaws.com
	// 1337

	public static void main(String[] args) {
		String host;
		int port;
		System.out.println("RMI Integration Test");
		System.out.println("Hostname:");
		Scanner sc = new Scanner(System.in);
		host = sc.next();
		System.out.println("Port:");
		port = sc.nextInt();
		sc.close();
		
		// Testing UserFactory
		//--------------------
		System.out.println("lookup UserFactory on " + host + " at port " + port + " ...");
		Object userFactoryObj = null;
		try {
			userFactoryObj = Naming.lookup("rmi://" + host + ":" + port + "/UserFactory");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		IUserFactoryRMI userFactory = (IUserFactoryRMI) userFactoryObj;
		System.out.println("... got UserFactory");
		
		// new user method
		System.out.println("Request new user dto ...");
		Object userObj = null;
		try {
			userObj = userFactory.createUser();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IUserRMI newUser = (IUserRMI) userObj;
		System.out.println("... received new user dto from UserFactory");
		
		// save user method
		System.out.println("Save new user dto ...");
		newUser.setFirstName("Daniel");
		newUser.setLastName("Integration");
		newUser.setAddress("Teststraße 7a, 6800 Feldkirch");
		newUser.setDateOfBirth(LocalDate.now());
		newUser.setEmail("test@case.com");
		newUser.setGender(Gender.Male);
		newUser.setMembershipFee(15.9);
		Set<UserRole> roles = new LinkedHashSet<UserRole>();
		roles.add(UserRole.Admin);
		newUser.setRoles(roles);
		newUser.setTelephoneNumber("+43 664 874379");
		Set<TypeOfSport> sports = new LinkedHashSet<TypeOfSport>();
		sports.add(TypeOfSport.Soccer);
		newUser.setTypeOfSports(sports);
		try {
			userFactory.save(newUser);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("... new user dto saved");
	}
}