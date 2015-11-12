package at.fhv.itb5c.application.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import at.fhv.itb5c.commons.enums.TypeOfSport;

public class DepartmentDTOTest {

	@Test
	public void testDefaultHeadId() {
		DepartmentDTO department = new DepartmentDTO();
		assertNull(department.getHeadId());
	}

	@Test
	public void testDefaultName() {
		DepartmentDTO department = new DepartmentDTO();
		assertNull(department.getName());
	}

	@Test
	public void testDefaultTypeOfSport() {
		DepartmentDTO department = new DepartmentDTO();
		assertNull(department.getTypeOfSport());
	}

	@Test
	public void testHeadId() {
		final Long headId = 4711l;
		DepartmentDTO department = new DepartmentDTO();
		department.setHeadId(headId);
		assertEquals(headId, department.getHeadId());
	}

	@Test
	public void testName() {
		final String name = "Department Name";
		DepartmentDTO department = new DepartmentDTO();
		department.setName(name);
		assertEquals(name, department.getName());
	}

	@Test
	public void testTypeOfSport() {
		final TypeOfSport typeOfSport = TypeOfSport.Tennis;
		DepartmentDTO department = new DepartmentDTO();
		department.setTypeOfSport(typeOfSport);
		assertEquals(typeOfSport, department.getTypeOfSport());
	}

}
