package at.fhv.itb5c.application;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertTrue;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.HashSet;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import at.fhv.itb5c.application.converter.ConverterDepartmentDTO;
//import at.fhv.itb5c.commons.enums.TypeOfSport;
//import at.fhv.itb5c.commons.enums.UserRole;
//import at.fhv.itb5c.commons.util.auth.SessionManager;
//import at.fhv.itb5c.model.entity.Department;

public class ApplicationFacadeTest {
//	ApplicationFacade _appFacade;
//	String _session;
//	private UserDTO _user;
//
//	@Before
//	public void beforeEach() {
//		_appFacade = new ApplicationFacade();
//		_user = new UserDTO();
//		_user.setId(1l);
//		_user.setRoles(new HashSet<>(Arrays.asList(UserRole.Admin)));
//		_session = SessionManager.getInstance().createNewSession(_user.getId(), _user.getRoles());
//	}
//
//	@Test
//	public void testCreateUser() {
//		UserDTO dto = _appFacade.createUser(_session);
//		assertNotNull(dto);
//		assertNotNull(dto.getRoles());
//		assertNotNull(dto.getTypeOfSports());
//	}
//
//	@Test
//	public void testFindUsersAllNull() {
//		Collection<UserDTO> dtos = _appFacade.findUsers(_session, null, null, null, null);
//		assertNotNull(dtos);
//		assertEquals(0, dtos.size());
//	}
//
//	@Test
//	public void testFindUsersSimple() {
//		Collection<UserDTO> dtos = _appFacade.findUsersSimple(_session, null);
//		assertNotNull(dtos);
//	}
//
//	@Test
//	public void testSaveUserNull() {
//		UserDTO dto = _appFacade.saveUser(_session, null);
//		assertNull(dto);
//	}
//
//	@Test
//	public void testSaveUser() {
//		UserDTO dto = _appFacade.createUser(_session);
//		assertNull(dto.getId());
//		UserDTO savedDto = _appFacade.saveUser(_session, dto);
//		assertNotNull(savedDto.getId());
//	}
//
//	@Test
//	public void testGetUserById() {
//		final Long id = 1337l;
//		UserDTO dto = _appFacade.createUser(_session);
//		dto.setId(id);
//
//		UserDTO savedDTO = _appFacade.saveUser(_session, dto);
//		assertNotNull(savedDTO);
//		assertEquals(id, savedDTO.getId());
//
//		UserDTO retrievedDTO = _appFacade.getUserById(_session, id);
//		assertNotNull(retrievedDTO);
//		assertEquals(id, retrievedDTO.getId());
//	}
//
//	@Test
//	public void testGetUserByIdNull() {
//		UserDTO dto = _appFacade.getUserById(_session, null);
//		assertNull(dto);
//	}
//
//	@Test
//	public void testSaveDepartment() {
//		final Long id = 1337l;
//		DepartmentDTO dto = ConverterDepartmentDTO.toDTO(new Department());
//		dto.setId(id);
//
//		DepartmentDTO savedDTO = _appFacade.saveDepartment(_session, dto);
//		assertNotNull(savedDTO);
//		assertEquals(id, savedDTO.getId());
//		assertFalse(dto == savedDTO);
//	}
//
//	@Test
//	public void testSaveDepartmentNull() {
//		DepartmentDTO dto = _appFacade.saveDepartment(_session, null);
//		assertNull(dto);
//	}
//
//	@Test
//	public void testGetDepartmentById() {
//		final Long id = 1337l;
//		DepartmentDTO dto = ConverterDepartmentDTO.toDTO(new Department());
//		dto.setId(id);
//
//		DepartmentDTO savedDTO = _appFacade.saveDepartment(_session, dto);
//		assertNotNull(savedDTO);
//		assertEquals(id, savedDTO.getId());
//
//		DepartmentDTO retrievedDTO = _appFacade.getDepartmentById(_session, id);
//		assertNotNull(retrievedDTO);
//		assertEquals(id, retrievedDTO.getId());
//	}
//
//	@Test
//	public void testGetDepartmentByIdNull() {
//		DepartmentDTO dto = _appFacade.getDepartmentById(_session, null);
//		assertNull(dto);
//	}
//
//	@Test
//	public void testGetAllDepartments() {
//		final Long id = 1337l;
//		DepartmentDTO dto = ConverterDepartmentDTO.toDTO(new Department());
//		dto.setId(id);
//
//		DepartmentDTO savedDTO = _appFacade.saveDepartment(_session, dto);
//		assertNotNull(savedDTO);
//		assertEquals(id, savedDTO.getId());
//
//		Collection<DepartmentDTO> dtos = _appFacade.getAllDepartments(_session);
//		assertNotNull(dtos);
//		assertTrue(dtos.size() > 0);
//	}
//
//	@Test
//	public void testCreateTeam() {
//		TeamDTO dto = _appFacade.createTeam(_session);
//		assertNotNull(dto);
//		assertNotNull(dto.getMemberIds());
//	}
//
//	@Test
//	public void testFindTeamsAllNull() {
//		Collection<TeamDTO> dtos = _appFacade.findTeams(_session, null, null, null, null);
//		assertNotNull(dtos);
//		assertEquals(0, dtos.size());
//	}
//
//	@Test
//	public void testSaveTeam() {
//		final Long id = 1337l;
//		TeamDTO dto = _appFacade.createTeam(_session);
//		dto.setId(id);
//
//		TeamDTO savedDTO = _appFacade.saveTeam(_session, dto);
//		assertNotNull(savedDTO);
//		assertEquals(id, savedDTO.getId());
//		assertFalse(dto == savedDTO);
//	}
//
//	@Test
//	public void testSaveTeamNull() {
//		TeamDTO dto = _appFacade.saveTeam(_session, null);
//		assertNull(dto);
//	}
//
//	@Test
//	public void testGetTeamById() {
//		final Long id = 1337l;
//		TeamDTO dto = _appFacade.createTeam(_session);
//		dto.setId(id);
//
//		TeamDTO savedDTO = _appFacade.saveTeam(_session, dto);
//		assertNotNull(savedDTO);
//		assertEquals(id, savedDTO.getId());
//		assertFalse(dto == savedDTO);
//
//		TeamDTO retrievedDTO = _appFacade.getTeamById(_session, id);
//		assertNotNull(retrievedDTO);
//		assertEquals(id, retrievedDTO.getId());
//		assertFalse(savedDTO == retrievedDTO);
//	}
//
//	@Test
//	public void testGetTeamByIdNull() {
//		TeamDTO dto = _appFacade.getTeamById(_session, null);
//		assertNull(dto);
//	}
//
//	@Test
//	public void getLeagueByIdNull() {
//		LeagueDTO dto = _appFacade.getLeagueById(_session, null);
//		assertNull(dto);
//	}
//
//	@Test
//	public void getAllLeagues() {
//		Collection<LeagueDTO> dtos = _appFacade.getAllLeagues(_session);
//		assertNotNull(dtos);
//	}
//
//	@Test
//	public void createNewTournament(){
//		DepartmentDTO dept = new DepartmentDTO();
//		dept.setHeadId(_user.getId());
//		TournamentDTO dto = _appFacade.createTournament(_session, dept);
//		assertNotNull(dto);
//		
//		UserDTO standardUser = new UserDTO();
//		standardUser.setId(3l);
//		standardUser.setRoles(new HashSet<>(Arrays.asList(UserRole.StandardUser)));
//		String session = SessionManager.getInstance().createNewSession(standardUser.getId(), standardUser.getRoles());
//		dept.setHeadId(standardUser.getId()+1);
//		dto = _appFacade.createTournament(session, dept);
//		assertNull(dto);
//	}
//	
//	@Test
//	public void getTournamentById() {
//		final Long id = 1338l;
//		DepartmentDTO dept = new DepartmentDTO();
//		dept.setHeadId(_user.getId());
//		TournamentDTO dto = _appFacade.createTournament(_session, dept);
//		dto.setId(id);
//
//		TournamentDTO savedDTO = _appFacade.saveTournament(_session, dto, dept);
//		assertNotNull(savedDTO);
//		assertEquals(id, savedDTO.getId());
//		assertFalse(dto == savedDTO);
//
//		TournamentDTO retrievedDTO = _appFacade.getTournamentById(_session, id);
//		assertNotNull(retrievedDTO);
//		assertEquals(id, retrievedDTO.getId());
//		assertFalse(savedDTO == retrievedDTO);
//	}
//	
//	@Test
//	public void getTournamentByIdNull() {
//		TournamentDTO dto = _appFacade.getTournamentById(_session, null);
//		assertNull(dto);
//	}
//
//	@Test
//	public void createUserDeptHead(){
//		DepartmentDTO dept = new DepartmentDTO();
//		dept.setHeadId(_user.getId());
//	}
//	
//	@Test
//	public void testSaveTournament() {
//		final Long id = 1338l;
//		DepartmentDTO dept = new DepartmentDTO();
//		dept.setHeadId(_user.getId());
//		dept.setId(id);
//		TournamentDTO tournament = _appFacade.createTournament(_session, dept);
//		
//		TournamentDTO savedTournament = _appFacade.saveTournament(_session, tournament, dept);
//		assertNotNull(savedTournament);
//		assertFalse(savedTournament == tournament);
//	}
	
//	@Test
//	TODO fix test
//	public void testUpdateTournamentAddTeams() {
//		final Long id = 1338l;
//		DepartmentDTO dept = new DepartmentDTO();
//		dept.setHeadId(_user.getId());
//		dept.setId(id);
//		TournamentDTO tournament = _appFacade.createTournament(_session, dept);
//		
//		TournamentDTO savedTournament = _appFacade.saveTournament(_session, tournament, dept);
//		
//		TeamDTO team = _appFacade.createTeam(_session);
//		team.setName("Team A");
//		team.setTypeOfSport(TypeOfSport.Soccer);
//		
//		TeamDTO savedTeam = _appFacade.saveTeam(_session, team);
//		
//		savedTournament.getHomeTeamsIds().add(savedTeam.getId());
//		TournamentDTO savedTournamentWithTeams = _appFacade.saveTournament(_session, savedTournament, dept);
//		
//		assertNotNull(savedTournamentWithTeams);
//		assertFalse(savedTournamentWithTeams == savedTournament);
//		
//	}
}
