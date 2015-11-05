package at.fhv.itb5c.model.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import at.fhv.itb5c.commons.enums.TypeOfSport;

public class DepartmentTest {
	@Test
	public void testName() {
		Department department = new Department();
		final String name = "DepartmentTest Name";
		
		department.setName(name);
		
		assertEquals(name, department.getName());
	}

	@Test
	public void testTypeOfSport() {
		Department department = new Department();
		final TypeOfSport typeOfSport = TypeOfSport.Soccer;
		
		department.setTypeOfSport(typeOfSport);
		
		assertEquals(typeOfSport, department.getTypeOfSport());
	}

	@Test
	public void testHead() {
		Department department = new Department();
		User head = new User();
		head.setFirstName("DepartmentTest Head");
		
		department.setHead(head);
		
		assertEquals(head, department.getHead());
		assertEquals(head.getFirstName(), department.getHead().getFirstName());
	}

}
