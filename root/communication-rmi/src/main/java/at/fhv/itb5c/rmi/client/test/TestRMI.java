package at.fhv.itb5c.rmi.client.test;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

import at.fhv.itb5c.commons.dto.rmi.IDepartmentFactoryRMI;
import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserFactoryRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;
import at.fhv.itb5c.rmi.client.RMIClient;

public class TestRMI {
	// ec2-52-10-208-136.us-west-2.compute.amazonaws.com
	// 1337
	private static RMIClient _client = RMIClient.getRMIClient();

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
		// --------------------
		System.out.println("lookup UserFactory on " + host + " at port " + port + " ...");
		IUserFactoryRMI userFactory = _client.getUserFactory();;
		System.out.println("... got UserFactory");

		// new user method
		System.out.println("Request new user dto ...");
		Object userObj = null;
		try {
			userObj = userFactory.createUser();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		IUserRMI newUser = (IUserRMI) userObj;
		System.out.println("... received new user dto from UserFactory");

		// save user method
		System.out.println("Save new user dto ...");
		try {
			System.out.println("ID must be null: " + newUser.getId());
			newUser.setFirstName("Daniel");
			newUser.setLastName("Integration");
			newUser.setAddress("Teststraße 7a, 6800 Feldkirch");
			newUser.setDateOfBirth(LocalDate.now());
			newUser.setEmail("test@case.com");
			newUser.setGender(Gender.Male);
			newUser.setMembershipFee(15.9);
			Set<UserRole> roles = new HashSet<UserRole>();
			roles.add(UserRole.Admin);
			newUser.setRoles(roles);
			newUser.setTelephoneNumber("+43 664 874379");
			Set<TypeOfSport> sports = new HashSet<TypeOfSport>();
			sports.add(TypeOfSport.Soccer);
			newUser.setTypeOfSports(sports);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

		try {
			newUser = userFactory.save(newUser);
			System.out.println("ID must not be null: " + newUser.getId());
			System.out.println("Birthdate must not be null: " + newUser.getDateOfBirth());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.out.println("... new user dto saved");

		// search user
		System.out.println("Searching for user ...");
		LinkedList<IUserRMI> result = null;
		try {
			result = (LinkedList<IUserRMI>) userFactory.findUsers("Daniel", "Integration", null, null);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.out.println("... " + result.size() + " results found");

		// Testing DepartmentFactory
		// --------------------
		System.out.println("lookup DepartmentFactory on " + host + " at port " + port + " ...");
		IDepartmentFactoryRMI departmentFactory = _client.getDepartmentFactory();
		System.out.println("... got DepartmentFactory");
		
		// get all departments
		System.out.println("Retrieving all departments ...");
		LinkedList<IDepartmentRMI> depts = null;
		try {
			depts = (LinkedList<IDepartmentRMI>) departmentFactory.getAllDepartments();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.out.println(depts.size() + " departments received");
		
	}
}