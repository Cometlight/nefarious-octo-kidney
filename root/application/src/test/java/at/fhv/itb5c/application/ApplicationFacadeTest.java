package at.fhv.itb5c.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import at.fhv.itb5c.application.converter.ConverterDepartmentDTO;
import at.fhv.itb5c.application.dto.DepartmentDTO;
import at.fhv.itb5c.application.dto.LeagueDTO;
import at.fhv.itb5c.application.dto.TeamDTO;
import at.fhv.itb5c.application.dto.UserDTO;
import at.fhv.itb5c.model.entity.Department;

public class ApplicationFacadeTest {
	ApplicationFacade _appFacade;

	@Before
	public void beforeEach() {
		_appFacade = new ApplicationFacade();
	}

	@Test
	public void testCreateUser() {
		UserDTO dto = _appFacade.createUser();
		assertNotNull(dto);
		assertNotNull(dto.getRoles());
		assertNotNull(dto.getTypeOfSports());
	}

	@Test
	public void testFindUsersAllNull() {
		Collection<UserDTO> dtos = _appFacade.findUsers(null, null, null, null);
		assertNotNull(dtos);
		assertEquals(0, dtos.size());
	}

	@Test
	public void testFindUsersSimple() {
		Collection<UserDTO> dtos = _appFacade.findUsersSimple(null);
		assertNotNull(dtos);
	}

	@Test
	public void testSaveUserNull() {
		UserDTO dto = _appFacade.saveUser(null);
		assertNull(dto);
	}

	@Test
	public void testSaveUser() {
		UserDTO dto = _appFacade.createUser();
		assertNull(dto.getId());
		UserDTO savedDto = _appFacade.saveUser(dto);
		assertNotNull(savedDto.getId());
	}

	@Test
	public void testGetUserById() {
		final Long id = 1337l;
		UserDTO dto = _appFacade.createUser();
		dto.setId(id);

		UserDTO savedDTO = _appFacade.saveUser(dto);
		assertNotNull(savedDTO);
		assertEquals(id, savedDTO.getId());

		UserDTO retrievedDTO = _appFacade.getUserById(id);
		assertNotNull(retrievedDTO);
		assertEquals(id, retrievedDTO.getId());
	}

	@Test
	public void testGetUserByIdNull() {
		UserDTO dto = _appFacade.getUserById(null);
		assertNull(dto);
	}

	@Test
	public void testSaveDepartment() {
		final Long id = 1337l;
		DepartmentDTO dto = ConverterDepartmentDTO.toDTO(new Department());
		dto.setId(id);

		DepartmentDTO savedDTO = _appFacade.saveDepartment(dto);
		assertNotNull(savedDTO);
		assertEquals(id, savedDTO.getId());
		assertFalse(dto == savedDTO);
	}

	@Test
	public void testSaveDepartmentNull() {
		DepartmentDTO dto = _appFacade.saveDepartment(null);
		assertNull(dto);
	}

	@Test
	public void testGetDepartmentById() {
		final Long id = 1337l;
		DepartmentDTO dto = ConverterDepartmentDTO.toDTO(new Department());
		dto.setId(id);

		DepartmentDTO savedDTO = _appFacade.saveDepartment(dto);
		assertNotNull(savedDTO);
		assertEquals(id, savedDTO.getId());

		DepartmentDTO retrievedDTO = _appFacade.getDepartmentById(id);
		assertNotNull(retrievedDTO);
		assertEquals(id, retrievedDTO.getId());
	}

	@Test
	public void testGetDepartmentByIdNull() {
		DepartmentDTO dto = _appFacade.getDepartmentById(null);
		assertNull(dto);
	}

	@Test
	public void testGetAllDepartments() {
		final Long id = 1337l;
		DepartmentDTO dto = ConverterDepartmentDTO.toDTO(new Department());
		dto.setId(id);

		DepartmentDTO savedDTO = _appFacade.saveDepartment(dto);
		assertNotNull(savedDTO);
		assertEquals(id, savedDTO.getId());

		Collection<DepartmentDTO> dtos = _appFacade.getAllDepartments();
		assertNotNull(dtos);
		assertTrue(dtos.size() > 0);
	}

	@Test
	public void testCreateTeam() {
		TeamDTO dto = _appFacade.createTeam();
		assertNotNull(dto);
		assertNotNull(dto.getMemberIds());
	}

	@Test
	public void testFindTeamsAllNull() {
		Collection<TeamDTO> dtos = _appFacade.findTeams(null, null, null, null);
		assertNotNull(dtos);
		assertEquals(0, dtos.size());
	}

	@Test
	public void testSaveTeam() {
		final Long id = 1337l;
		TeamDTO dto = _appFacade.createTeam();
		dto.setId(id);

		TeamDTO savedDTO = _appFacade.saveTeam(dto);
		assertNotNull(savedDTO);
		assertEquals(id, savedDTO.getId());
		assertFalse(dto == savedDTO);
	}

	@Test
	public void testSaveTeamNull() {
		TeamDTO dto = _appFacade.saveTeam(null);
		assertNull(dto);
	}

	@Test
	public void testGetTeamById() {
		final Long id = 1337l;
		TeamDTO dto = _appFacade.createTeam();
		dto.setId(id);

		TeamDTO savedDTO = _appFacade.saveTeam(dto);
		assertNotNull(savedDTO);
		assertEquals(id, savedDTO.getId());
		assertFalse(dto == savedDTO);

		TeamDTO retrievedDTO = _appFacade.getTeamById(id);
		assertNotNull(retrievedDTO);
		assertEquals(id, retrievedDTO.getId());
		assertFalse(savedDTO == retrievedDTO);
	}

	@Test
	public void testGetTeamByIdNull() {
		TeamDTO dto = _appFacade.getTeamById(null);
		assertNull(dto);
	}

	@Test
	public void getLeagueByIdNull() {
		LeagueDTO dto = _appFacade.getLeagueById(null);
		assertNull(dto);
	}

	@Test
	public void getAllLeagues() {
		Collection<LeagueDTO> dtos = _appFacade.getAllLeagues();
		assertNotNull(dtos);
	}

}
