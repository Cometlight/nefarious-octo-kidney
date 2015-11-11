package at.fhv.itb5c.application;

import java.util.Collection;
import java.util.List;

import at.fhv.itb5c.application.converter.ConverterDepartmentDTO;
import at.fhv.itb5c.application.converter.ConverterLeagueDTO;
import at.fhv.itb5c.application.converter.ConverterTeamDTO;
import at.fhv.itb5c.application.converter.ConverterTournamentDTO;
import at.fhv.itb5c.application.converter.ConverterUserDTO;
import at.fhv.itb5c.application.dto.DepartmentDTO;
import at.fhv.itb5c.application.dto.LeagueDTO;
import at.fhv.itb5c.application.dto.TeamDTO;
import at.fhv.itb5c.application.dto.TournamentDTO;
import at.fhv.itb5c.application.dto.UserDTO;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.model.PersistenceFacade;
import at.fhv.itb5c.model.entity.Department;
import at.fhv.itb5c.model.entity.League;
import at.fhv.itb5c.model.entity.Team;
import at.fhv.itb5c.model.entity.Tournament;
import at.fhv.itb5c.model.entity.User;

public class ApplicationFacade implements ILogger {
	public UserDTO createUser() {
		return UserDTO.create();
	}
	
	public UserDTO getUserById(Long id) {
		User user = PersistenceFacade.getInstance().getById(User.class, id);
		return ConverterUserDTO.toDTO(user);
	}

	/**
	 * If a parameter is null, it is ignored.
	 */
	public Collection<UserDTO> findUsers(String firstName, String lastName, Long departmentId, Boolean membershipFeePaid) {
		return ConverterUserDTO.toDTO(PersistenceFacade.getInstance().findUsers(firstName, lastName, departmentId, membershipFeePaid));
	}

	/**
	 * If a parameter is null, it is ignored.
	 */
	public Collection<UserDTO> findUsersSimple(String name) {
		return ConverterUserDTO.toDTO(PersistenceFacade.getInstance().findUsersSimple(name));
	}

	public UserDTO saveUser(UserDTO user) {
		try {
			return ConverterUserDTO.toDTO(PersistenceFacade.getInstance().saveOrUpdate(ConverterUserDTO.toEntity(user)));
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}
	
	public DepartmentDTO getDepartmentById(Long id) {
		Department entity = PersistenceFacade.getInstance().getById(Department.class, id);
		return ConverterDepartmentDTO.toDTO(entity);
	}
	
	public Collection<DepartmentDTO> getAllDepartments() {
		List<Department> departments = PersistenceFacade.getInstance().getAll(Department.class);
		return ConverterDepartmentDTO.toDTO(departments);
	}
	
	public DepartmentDTO saveDepartment(DepartmentDTO department) {
		Department entity = ConverterDepartmentDTO.toEntity(department);
		try {
			entity = PersistenceFacade.getInstance().saveOrUpdate(entity);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		return ConverterDepartmentDTO.toDTO(entity);
	}

	public TeamDTO createTeam() {
		return TeamDTO.create();
	}
	
	public TeamDTO getTeamById(Long id) {
		Team entity = PersistenceFacade.getInstance().getById(Team.class, id);
		return ConverterTeamDTO.toDTO(entity);
	}
	
	/**
	 * If a parameter is null, it is ignored.
	 */
	public Collection<TeamDTO> findTeams(String name, TypeOfSport typeOfSport, Long departmentId, Long leagueId) {
		List<Team> entities = PersistenceFacade.getInstance().findTeams(name, typeOfSport, departmentId, leagueId);
		return ConverterTeamDTO.toDTO(entities);
	}
	
	public TeamDTO saveTeam(TeamDTO team) {
		Team entity = ConverterTeamDTO.toEntity(team);
		try {
			entity = PersistenceFacade.getInstance().saveOrUpdate(entity);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
		return ConverterTeamDTO.toDTO(entity);
	}
	
	public LeagueDTO getLeagueById(Long id) {
		return ConverterLeagueDTO.toDTO(PersistenceFacade.getInstance().getById(League.class, id));
	}
	
	public Collection<LeagueDTO> getAllLeagues() {
		List<League> entities = PersistenceFacade.getInstance().getAll(League.class);
		return ConverterLeagueDTO.toDTO(entities);
	}
	
	public Collection<TournamentDTO> findTournaments(String name, Long departmentId){
		List<Tournament> entities = PersistenceFacade.getInstance().findTournaments(name, departmentId);
		return ConverterTournamentDTO.toDTO(entities);
	}
}
